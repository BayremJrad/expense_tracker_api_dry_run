from flask import Blueprint, request, g, jsonify
from auth import require_auth
import storage
from storage import now_iso, get_expense_receipts
from datetime import datetime, timezone, date, timedelta

bp = Blueprint("claim_actions", __name__)

MAX_EXPENSE_AGE_DAYS = 90


def _parse_date(date_str: str):
    try:
        return datetime.strptime(date_str, "%Y-%m-%d").date()
    except (ValueError, TypeError):
        return None


def _expense_total_eur(exp: dict) -> float:
    amount = exp["amount"]
    currency = exp.get("currency", "EUR")
    if currency == "EUR":
        return amount
    return round(amount * 1.18, 2)


def _claim_view(claim: dict) -> dict:
    expense_ids = claim.get("expenseIds", [])
    total_eur = sum(
        _expense_total_eur(storage.expenses[eid])
        for eid in expense_ids
        if eid in storage.expenses
    )
    return {
        "id": claim["id"],
        "title": claim["title"],
        "status": claim["status"],
        "totalEur": round(total_eur, 2),
        "expenseCount": len(expense_ids),
        "ownerId": claim["ownerId"],
        "createdAt": claim["createdAt"],
        "submittedAt": claim.get("submittedAt"),
        "decision": claim.get("decision"),
        "statusHistory": claim.get("statusHistory", []),
    }


def _update_expense_statuses(claim: dict, new_status: str):
    """Sync all expenses in the claim to the given status."""
    for eid in claim.get("expenseIds", []):
        if eid in storage.expenses:
            storage.expenses[eid]["status"] = new_status


# ---------------------------------------------------------------------------
# POST /claims/<id>/submit
# ---------------------------------------------------------------------------

@bp.route("/claims/<claim_id>/submit", methods=["POST"])
@require_auth()
def submit_claim(claim_id):
    claim = storage.claims.get(claim_id)
    if not claim:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Claim '{claim_id}' not found"}}), 404
    if claim["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this claim"}}), 403
    # BR-23: must be draft
    if claim["status"] != "draft":
        return jsonify({"error": {"code": "CONFLICT", "message": "Only draft claims can be submitted (BR-23)"}}), 409

    expense_ids = claim.get("expenseIds", [])
    # BR-21: at least one expense
    if not expense_ids:
        return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "Claim must have at least one expense (BR-21)"}}), 422

    today = date.today()
    cutoff = today - timedelta(days=MAX_EXPENSE_AGE_DAYS)

    for eid in expense_ids:
        exp = storage.expenses.get(eid)
        if not exp:
            return jsonify({"error": {"code": "NOT_FOUND", "message": f"Expense '{eid}' not found"}}), 404

        # BR-18: not older than 90 days
        incurred = _parse_date(exp["dateIncurred"])
        if incurred and incurred < cutoff:
            return jsonify({"error": {"code": "VALIDATION_ERROR", "message": f"Expense '{eid}' is older than 90 days (BR-18)"}}), 422

        # BR-19: receipt required if amount > threshold or requiresReceiptAlways
        cat = storage.categories.get(exp["categoryId"], {})
        receipt_count = len(get_expense_receipts(eid))
        needs_receipt = (
            cat.get("requiresReceiptAlways", False)
            or exp["amount"] > cat.get("receiptThreshold", 0)
        )
        if needs_receipt and receipt_count == 0:
            return jsonify({
                "error": {
                    "code": "RECEIPT_REQUIRED",
                    "message": f"Expense '{eid}' requires at least one receipt (BR-19)"
                }
            }), 422

    ts = now_iso()
    claim["status"] = "submitted"
    claim["submittedAt"] = ts
    claim.setdefault("statusHistory", []).append({
        "status": "submitted",
        "actor": g.user_id,
        "at": ts,
        "note": "Claim submitted for approval",
    })
    _update_expense_statuses(claim, "submitted")

    return jsonify(_claim_view(claim)), 200


# ---------------------------------------------------------------------------
# POST /claims/<id>/approve
# ---------------------------------------------------------------------------

@bp.route("/claims/<claim_id>/approve", methods=["POST"])
@require_auth("approver")
def approve_claim(claim_id):
    claim = storage.claims.get(claim_id)
    if not claim:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Claim '{claim_id}' not found"}}), 404
    # BR-23: must be submitted
    if claim["status"] != "submitted":
        return jsonify({"error": {"code": "CONFLICT", "message": "Only submitted claims can be approved (BR-23)"}}), 409
    # BR-26: approver cannot approve their own claim
    if claim["ownerId"] == g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "Approver cannot approve their own claim (BR-26)"}}), 403

    ts = now_iso()
    claim["status"] = "approved"
    claim["approvedBy"] = g.user_id
    claim["approvedAt"] = ts
    claim.setdefault("decision", {})
    claim["decision"] = {"outcome": "approved", "actor": g.user_id, "at": ts}
    claim.setdefault("statusHistory", []).append({
        "status": "approved",
        "actor": g.user_id,
        "at": ts,
        "note": "Claim approved",
    })
    _update_expense_statuses(claim, "approved")

    return jsonify(_claim_view(claim)), 200


# ---------------------------------------------------------------------------
# POST /claims/<id>/reject
# ---------------------------------------------------------------------------

@bp.route("/claims/<claim_id>/reject", methods=["POST"])
@require_auth("approver")
def reject_claim(claim_id):
    claim = storage.claims.get(claim_id)
    if not claim:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Claim '{claim_id}' not found"}}), 404
    # BR-23: must be submitted
    if claim["status"] != "submitted":
        return jsonify({"error": {"code": "CONFLICT", "message": "Only submitted claims can be rejected (BR-23)"}}), 409

    data = request.get_json(force=True) or {}
    reason = data.get("reason", "")

    ts = now_iso()
    # BR-24: rejected claim returns to draft
    claim["status"] = "draft"
    claim["decision"] = {"outcome": "rejected", "actor": g.user_id, "at": ts, "reason": reason}
    claim.setdefault("statusHistory", []).append({
        "status": "draft",
        "actor": g.user_id,
        "at": ts,
        "note": f"Claim rejected: {reason}",
    })
    _update_expense_statuses(claim, "draft")

    return jsonify(_claim_view(claim)), 200


# ---------------------------------------------------------------------------
# POST /claims/<id>/reimburse
# ---------------------------------------------------------------------------

@bp.route("/claims/<claim_id>/reimburse", methods=["POST"])
@require_auth("finance")
def reimburse_claim(claim_id):
    claim = storage.claims.get(claim_id)
    if not claim:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Claim '{claim_id}' not found"}}), 404
    # BR-23: must be approved
    if claim["status"] != "approved":
        return jsonify({"error": {"code": "CONFLICT", "message": "Only approved claims can be reimbursed (BR-23)"}}), 409

    ts = now_iso()
    claim["status"] = "reimbursed"
    claim["reimbursedBy"] = g.user_id
    claim["reimbursedAt"] = ts
    claim.setdefault("statusHistory", []).append({
        "status": "reimbursed",
        "actor": g.user_id,
        "at": ts,
        "note": "Claim reimbursed",
    })
    _update_expense_statuses(claim, "reimbursed")

    return jsonify(_claim_view(claim)), 200

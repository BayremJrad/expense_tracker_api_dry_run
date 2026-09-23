from flask import Blueprint, request, g, jsonify
from auth import require_auth
import storage
from storage import now_iso, get_expense_receipts, get_claim_for_expense
from datetime import datetime, timezone, date, timedelta
import uuid

bp = Blueprint("claims", __name__, url_prefix="/claims")

MAX_EXPENSE_AGE_DAYS = 90


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


def _parse_date(date_str: str):
    try:
        return datetime.strptime(date_str, "%Y-%m-%d").date()
    except (ValueError, TypeError):
        return None


@bp.route("", methods=["GET"])
@require_auth()
def list_claims():
    result = []
    for claim in storage.claims.values():
        if g.role == "employee" and claim["ownerId"] != g.user_id:
            continue
        result.append(_claim_view(claim))
    return jsonify(result), 200


@bp.route("", methods=["POST"])
@require_auth("employee")
def create_claim():
    data = request.get_json(force=True) or {}
    title = data.get("title", "").strip()
    if not title:
        return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "'title' is required"}}), 422

    expense_ids = data.get("expenseIds", [])
    # BR-21: at least one expense
    if not expense_ids:
        return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "At least one expense is required (BR-21)"}}), 422

    today = date.today()
    cutoff = today - timedelta(days=MAX_EXPENSE_AGE_DAYS)

    for eid in expense_ids:
        exp = storage.expenses.get(eid)
        if not exp:
            return jsonify({"error": {"code": "NOT_FOUND", "message": f"Expense '{eid}' not found"}}), 404
        # Must belong to caller
        if exp["ownerId"] != g.user_id:
            return jsonify({"error": {"code": "FORBIDDEN", "message": f"Expense '{eid}' does not belong to you"}}), 403
        # BR-22: expense can only belong to one claim
        existing_claim = get_claim_for_expense(eid)
        if existing_claim:
            return jsonify({"error": {"code": "CONFLICT", "message": f"Expense '{eid}' is already part of claim '{existing_claim['id']}' (BR-22)"}}), 409
        # BR-18: not older than 90 days
        incurred = _parse_date(exp["dateIncurred"])
        if incurred and incurred < cutoff:
            return jsonify({"error": {"code": "VALIDATION_ERROR", "message": f"Expense '{eid}' is older than 90 days (BR-18)"}}), 422

    claim_id = "clm_" + uuid.uuid4().hex[:8]
    claim = {
        "id": claim_id,
        "title": title,
        "status": "draft",
        "ownerId": g.user_id,
        "expenseIds": list(expense_ids),
        "createdAt": now_iso(),
        "submittedAt": None,
        "decision": None,
        "statusHistory": [
            {"status": "draft", "actor": g.user_id, "at": now_iso(), "note": "Claim created"}
        ],
    }
    storage.claims[claim_id] = claim

    # Link expenses to this claim
    for eid in expense_ids:
        storage.expenses[eid]["claimId"] = claim_id
        storage.expenses[eid]["status"] = "draft"

    return jsonify(_claim_view(claim)), 201


@bp.route("/<claim_id>", methods=["GET"])
@require_auth()
def get_claim(claim_id):
    claim = storage.claims.get(claim_id)
    if not claim:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Claim '{claim_id}' not found"}}), 404
    if g.role == "employee" and claim["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this claim"}}), 403
    return jsonify(_claim_view(claim)), 200


@bp.route("/<claim_id>", methods=["PATCH"])
@require_auth()
def update_claim(claim_id):
    claim = storage.claims.get(claim_id)
    if not claim:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Claim '{claim_id}' not found"}}), 404
    if claim["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this claim"}}), 403
    if claim["status"] != "draft":
        return jsonify({"error": {"code": "CONFLICT", "message": "Only draft claims can be edited"}}), 409

    data = request.get_json(force=True) or {}
    if "title" in data:
        claim["title"] = data["title"]

    # Allow updating expenseIds in draft
    if "expenseIds" in data:
        new_ids = data["expenseIds"]
        if not new_ids:
            return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "At least one expense is required (BR-21)"}}), 422

        today = date.today()
        cutoff = today - timedelta(days=MAX_EXPENSE_AGE_DAYS)

        # Unlink old expenses that are being removed
        old_ids = set(claim["expenseIds"])
        new_ids_set = set(new_ids)
        for eid in old_ids - new_ids_set:
            if eid in storage.expenses:
                storage.expenses[eid]["claimId"] = None
                storage.expenses[eid]["status"] = "unclaimed"

        for eid in new_ids:
            exp = storage.expenses.get(eid)
            if not exp:
                return jsonify({"error": {"code": "NOT_FOUND", "message": f"Expense '{eid}' not found"}}), 404
            if exp["ownerId"] != g.user_id:
                return jsonify({"error": {"code": "FORBIDDEN", "message": f"Expense '{eid}' does not belong to you"}}), 403
            existing_claim = get_claim_for_expense(eid)
            if existing_claim and existing_claim["id"] != claim_id:
                return jsonify({"error": {"code": "CONFLICT", "message": f"Expense '{eid}' is already in another claim (BR-22)"}}), 409
            incurred = _parse_date(exp["dateIncurred"])
            if incurred and incurred < cutoff:
                return jsonify({"error": {"code": "VALIDATION_ERROR", "message": f"Expense '{eid}' is older than 90 days (BR-18)"}}), 422

        claim["expenseIds"] = list(new_ids)
        for eid in new_ids:
            storage.expenses[eid]["claimId"] = claim_id
            storage.expenses[eid]["status"] = "draft"

    return jsonify(_claim_view(claim)), 200


@bp.route("/<claim_id>", methods=["DELETE"])
@require_auth()
def delete_claim(claim_id):
    claim = storage.claims.get(claim_id)
    if not claim:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Claim '{claim_id}' not found"}}), 404
    if claim["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this claim"}}), 403
    if claim["status"] != "draft":
        return jsonify({"error": {"code": "CONFLICT", "message": "Only draft claims can be deleted"}}), 409

    # Unlink expenses
    for eid in claim.get("expenseIds", []):
        if eid in storage.expenses:
            storage.expenses[eid]["claimId"] = None
            storage.expenses[eid]["status"] = "unclaimed"

    del storage.claims[claim_id]
    return "", 204

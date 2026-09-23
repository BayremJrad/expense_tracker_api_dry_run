from flask import Blueprint, request, g, jsonify
from auth import require_auth
import storage
from storage import now_iso, get_expense_receipts, get_claim_for_expense, FROZEN_STATUSES
from datetime import datetime, timezone, date
import uuid

bp = Blueprint("expenses", __name__, url_prefix="/expenses")

EDITABLE_STATUSES = {"unclaimed", "draft"}


def _to_eur(amount: float, currency: str) -> float:
    if currency == "EUR":
        return round(amount, 2)
    return round(amount * 1.18, 2)


def _expense_view(exp: dict) -> dict:
    cat = storage.categories.get(exp["categoryId"], {})
    receipt_count = len(get_expense_receipts(exp["id"]))
    return {
        "id": exp["id"],
        "amount": exp["amount"],
        "currency": exp["currency"],
        "amountEur": _to_eur(exp["amount"], exp["currency"]),
        "dateIncurred": exp["dateIncurred"],
        "category": {"id": cat.get("id", exp["categoryId"]), "name": cat.get("name", "")},
        "description": exp.get("description", ""),
        "paymentMethod": exp.get("paymentMethod", ""),
        "clientReference": exp.get("clientReference", ""),
        "receiptCount": receipt_count,
        "claimId": exp.get("claimId"),
        "ownerId": exp["ownerId"],
        "recordedAt": exp["recordedAt"],
        "status": exp["status"],
    }


def _parse_date(date_str: str):
    try:
        return datetime.strptime(date_str, "%Y-%m-%d").date()
    except (ValueError, TypeError):
        return None


@bp.route("", methods=["GET"])
@require_auth()
def list_expenses():
    status_filter = request.args.get("status")
    result = []
    for exp in storage.expenses.values():
        # Employees see only their own; approver/finance see all
        if g.role == "employee" and exp["ownerId"] != g.user_id:
            continue
        if status_filter and exp["status"] != status_filter:
            continue
        result.append(_expense_view(exp))
    return jsonify(result), 200


@bp.route("", methods=["POST"])
@require_auth("employee")
def create_expense():
    data = request.get_json(force=True) or {}

    # BR-16: amount > 0
    amount = data.get("amount")
    if amount is None or float(amount) <= 0:
        return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "amount must be greater than 0 (BR-16)"}}), 422

    # BR-17: dateIncurred not in future
    date_str = data.get("dateIncurred")
    incurred = _parse_date(date_str)
    if not incurred:
        return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "dateIncurred must be a valid date (YYYY-MM-DD)"}}), 422
    if incurred > date.today():
        return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "dateIncurred cannot be in the future (BR-17)"}}), 422

    # Category must be active
    category_id = data.get("categoryId")
    cat = storage.categories.get(category_id)
    if not cat or cat["status"] != "active":
        return jsonify({"error": {"code": "VALIDATION_ERROR", "message": f"Category '{category_id}' is not active or does not exist"}}), 422

    # BR-20: clientReference required for Client Entertainment
    client_reference = data.get("clientReference", "")
    if cat.get("requiresClientReference") and not client_reference:
        return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "clientReference is required for this category (BR-20)"}}), 422

    exp_id = "exp_" + uuid.uuid4().hex[:8]
    exp = {
        "id": exp_id,
        "amount": float(amount),
        "currency": data.get("currency", "EUR"),
        "dateIncurred": date_str,
        "categoryId": category_id,
        "description": data.get("description", ""),
        "paymentMethod": data.get("paymentMethod", ""),
        "clientReference": client_reference,
        "ownerId": g.user_id,
        "recordedAt": now_iso(),
        "status": "unclaimed",
        "claimId": None,
    }
    storage.expenses[exp_id] = exp
    return jsonify(_expense_view(exp)), 201


@bp.route("/<exp_id>", methods=["GET"])
@require_auth()
def get_expense(exp_id):
    exp = storage.expenses.get(exp_id)
    if not exp:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Expense '{exp_id}' not found"}}), 404
    if g.role == "employee" and exp["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this expense"}}), 403
    return jsonify(_expense_view(exp)), 200


@bp.route("/<exp_id>", methods=["PATCH"])
@require_auth()
def update_expense(exp_id):
    exp = storage.expenses.get(exp_id)
    if not exp:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Expense '{exp_id}' not found"}}), 404
    if exp["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this expense"}}), 403

    # Cannot edit if in a frozen claim
    claim = get_claim_for_expense(exp_id)
    if claim and claim["status"] in FROZEN_STATUSES:
        return jsonify({"error": {"code": "CONFLICT", "message": "Expense is part of a submitted/approved/reimbursed claim and cannot be edited"}}), 409

    data = request.get_json(force=True) or {}

    if "amount" in data:
        if float(data["amount"]) <= 0:
            return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "amount must be greater than 0 (BR-16)"}}), 422
        exp["amount"] = float(data["amount"])

    if "dateIncurred" in data:
        incurred = _parse_date(data["dateIncurred"])
        if not incurred:
            return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "dateIncurred must be a valid date"}}), 422
        if incurred > date.today():
            return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "dateIncurred cannot be in the future (BR-17)"}}), 422
        exp["dateIncurred"] = data["dateIncurred"]

    if "categoryId" in data:
        cat = storage.categories.get(data["categoryId"])
        if not cat or cat["status"] != "active":
            return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "Category is not active or does not exist"}}), 422
        exp["categoryId"] = data["categoryId"]

    for field in ("description", "paymentMethod", "clientReference", "currency"):
        if field in data:
            exp[field] = data[field]

    return jsonify(_expense_view(exp)), 200


@bp.route("/<exp_id>", methods=["DELETE"])
@require_auth()
def delete_expense(exp_id):
    exp = storage.expenses.get(exp_id)
    if not exp:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Expense '{exp_id}' not found"}}), 404
    if exp["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this expense"}}), 403
    if exp["status"] != "unclaimed":
        return jsonify({"error": {"code": "CONFLICT", "message": "Only unclaimed expenses can be deleted"}}), 409

    del storage.expenses[exp_id]
    return "", 204

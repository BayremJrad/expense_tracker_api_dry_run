from flask import Blueprint, request, g, jsonify
from auth import require_auth
import storage
from storage import now_iso, get_expense_receipts, get_claim_for_expense, FROZEN_STATUSES
import uuid

bp = Blueprint("receipts", __name__)


def _receipt_view(r: dict) -> dict:
    return {
        "id": r["id"],
        "expenseId": r["expenseId"],
        "filename": r["filename"],
        "size": r["size"],
        "mimeType": r["mimeType"],
        "uploadedAt": r["uploadedAt"],
        "uploadedBy": r["uploadedBy"],
    }


@bp.route("/expenses/<exp_id>/receipts", methods=["GET"])
@require_auth()
def list_receipts(exp_id):
    exp = storage.expenses.get(exp_id)
    if not exp:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Expense '{exp_id}' not found"}}), 404
    if g.role == "employee" and exp["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this expense"}}), 403

    return jsonify([_receipt_view(r) for r in get_expense_receipts(exp_id)]), 200


@bp.route("/expenses/<exp_id>/receipts", methods=["POST"])
@require_auth()
def upload_receipt(exp_id):
    exp = storage.expenses.get(exp_id)
    if not exp:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Expense '{exp_id}' not found"}}), 404
    if exp["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this expense"}}), 403

    # Expense must not be frozen
    claim = get_claim_for_expense(exp_id)
    if claim and claim["status"] in FROZEN_STATUSES:
        return jsonify({"error": {"code": "CONFLICT", "message": "Cannot upload receipt — expense is part of a frozen claim"}}), 409

    # Accept multipart or JSON (for test convenience)
    if request.content_type and "multipart/form-data" in request.content_type:
        file = request.files.get("file")
        if not file:
            return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "No file provided"}}), 422
        filename = file.filename or "upload"
        content = file.read()
        size = len(content)
        mime_type = file.content_type or "application/octet-stream"
    else:
        # JSON fallback (useful for testing without real files)
        data = request.get_json(force=True) or {}
        filename = data.get("filename", "upload")
        size = int(data.get("size", 0))
        mime_type = data.get("mimeType", "application/octet-stream")

    receipt_id = "rec_" + uuid.uuid4().hex[:8]
    receipt = {
        "id": receipt_id,
        "expenseId": exp_id,
        "filename": filename,
        "size": size,
        "mimeType": mime_type,
        "uploadedAt": now_iso(),
        "uploadedBy": g.user_id,
    }
    storage.receipts[receipt_id] = receipt
    return jsonify(_receipt_view(receipt)), 201


@bp.route("/expenses/<exp_id>/receipts/<receipt_id>", methods=["DELETE"])
@require_auth()
def delete_receipt(exp_id, receipt_id):
    exp = storage.expenses.get(exp_id)
    if not exp:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Expense '{exp_id}' not found"}}), 404
    if exp["ownerId"] != g.user_id:
        return jsonify({"error": {"code": "FORBIDDEN", "message": "You do not own this expense"}}), 403

    receipt = storage.receipts.get(receipt_id)
    if not receipt or receipt["expenseId"] != exp_id:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Receipt '{receipt_id}' not found"}}), 404

    # Cannot delete if frozen
    claim = get_claim_for_expense(exp_id)
    if claim and claim["status"] in FROZEN_STATUSES:
        return jsonify({"error": {"code": "CONFLICT", "message": "Cannot delete receipt — expense is part of a frozen claim"}}), 409

    del storage.receipts[receipt_id]
    return "", 204

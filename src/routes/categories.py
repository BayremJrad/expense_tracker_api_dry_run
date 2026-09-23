from flask import Blueprint, request, g, jsonify
from auth import require_auth
import storage
from storage import now_iso
import uuid

bp = Blueprint("categories", __name__, url_prefix="/categories")


def _category_view(cat: dict) -> dict:
    return {
        "id": cat["id"],
        "name": cat["name"],
        "receiptThreshold": cat["receiptThreshold"],
        "requiresReceiptAlways": cat["requiresReceiptAlways"],
        "requiresClientReference": cat["requiresClientReference"],
        "status": cat["status"],
        "createdAt": cat["createdAt"],
    }


@bp.route("", methods=["GET"])
@require_auth()
def list_categories():
    include_retired = request.args.get("includeRetired", "false").lower() == "true"
    # Only finance may request retired categories
    if include_retired and g.role != "finance":
        include_retired = False

    result = [
        _category_view(c)
        for c in storage.categories.values()
        if include_retired or c["status"] == "active"
    ]
    return jsonify(result), 200


@bp.route("/<cat_id>", methods=["GET"])
@require_auth()
def get_category(cat_id):
    cat = storage.categories.get(cat_id)
    if not cat:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Category '{cat_id}' not found"}}), 404
    return jsonify(_category_view(cat)), 200


@bp.route("", methods=["POST"])
@require_auth("finance")
def create_category():
    data = request.get_json(force=True) or {}
    name = data.get("name")
    if not name:
        return jsonify({"error": {"code": "VALIDATION_ERROR", "message": "'name' is required"}}), 422

    cat_id = "cat_" + uuid.uuid4().hex[:8]
    cat = {
        "id": cat_id,
        "name": name,
        "receiptThreshold": float(data.get("receiptThreshold", 0)),
        "requiresReceiptAlways": bool(data.get("requiresReceiptAlways", False)),
        "requiresClientReference": bool(data.get("requiresClientReference", False)),
        "status": "active",
        "createdAt": now_iso(),
    }
    storage.categories[cat_id] = cat
    return jsonify(_category_view(cat)), 201


@bp.route("/<cat_id>", methods=["PATCH"])
@require_auth("finance")
def update_category(cat_id):
    cat = storage.categories.get(cat_id)
    if not cat:
        return jsonify({"error": {"code": "NOT_FOUND", "message": f"Category '{cat_id}' not found"}}), 404

    data = request.get_json(force=True) or {}
    allowed = {"name", "receiptThreshold", "requiresReceiptAlways", "requiresClientReference", "status"}
    for key in allowed:
        if key in data:
            if key == "receiptThreshold":
                cat[key] = float(data[key])
            elif key in ("requiresReceiptAlways", "requiresClientReference"):
                cat[key] = bool(data[key])
            else:
                cat[key] = data[key]

    return jsonify(_category_view(cat)), 200

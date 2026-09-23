from datetime import datetime, timezone

# ---------------------------------------------------------------------------
# In-memory stores
# ---------------------------------------------------------------------------

categories: dict = {}
expenses: dict = {}
claims: dict = {}
receipts: dict = {}   # keyed by receipt_id; each receipt has expense_id ref

# ---------------------------------------------------------------------------
# Seed categories (matching mock data)
# ---------------------------------------------------------------------------

_SEED_CATEGORIES = [
    {
        "id": "cat_01",
        "name": "Travel",
        "receiptThreshold": 50,
        "requiresReceiptAlways": False,
        "requiresClientReference": False,
        "status": "active",
        "createdAt": "2024-01-01T00:00:00Z",
    },
    {
        "id": "cat_02",
        "name": "Accommodation",
        "receiptThreshold": 0.01,
        "requiresReceiptAlways": True,
        "requiresClientReference": False,
        "status": "active",
        "createdAt": "2024-01-01T00:00:00Z",
    },
    {
        "id": "cat_03",
        "name": "Meals",
        "receiptThreshold": 25,
        "requiresReceiptAlways": False,
        "requiresClientReference": False,
        "status": "active",
        "createdAt": "2024-01-01T00:00:00Z",
    },
    {
        "id": "cat_04",
        "name": "Software",
        "receiptThreshold": 10,
        "requiresReceiptAlways": False,
        "requiresClientReference": False,
        "status": "active",
        "createdAt": "2024-01-01T00:00:00Z",
    },
    {
        "id": "cat_05",
        "name": "Client Entertainment",
        "receiptThreshold": 0.01,
        "requiresReceiptAlways": True,
        "requiresClientReference": True,
        "status": "active",
        "createdAt": "2024-01-01T00:00:00Z",
    },
]

for _cat in _SEED_CATEGORIES:
    categories[_cat["id"]] = _cat


# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def now_iso() -> str:
    return datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")


def get_expense_receipts(expense_id: str) -> list:
    return [r for r in receipts.values() if r["expenseId"] == expense_id]


def get_claim_for_expense(expense_id: str):
    """Return the claim that contains this expense, or None."""
    for claim in claims.values():
        if expense_id in claim.get("expenseIds", []):
            return claim
    return None


FROZEN_STATUSES = {"submitted", "approved", "reimbursed"}

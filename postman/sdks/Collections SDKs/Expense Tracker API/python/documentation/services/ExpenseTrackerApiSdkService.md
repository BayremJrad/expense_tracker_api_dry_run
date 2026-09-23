# ExpenseTrackerApiSdkService

A list of all methods in the `ExpenseTrackerApiSdkService` service. Click on the method name to view detailed information about that method.

| Methods                             | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| :---------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [list_categories](#list_categories) | Returns all active categories available for use on new expenses. Finance may also see retired categories by passing `includeRetired=true`. **BR-06** – Any authenticated user can retrieve the category list. **BR-08** – Read-only for employees and approvers.                                                                                                                                                                                                                                               |
| [get_category](#get_category)       | Returns a single category by its ID, including whether it is active or retired.                                                                                                                                                                                                                                                                                                                                                                                                                                |
| [create_category](#create_category) | Creates a new expense category. **Finance role required** (BR-07, BR-08). The `receiptThreshold` is the amount above which a receipt becomes mandatory. Set `requiresReceiptAlways: true` to mandate a receipt at any amount. Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).                                                                                                                                                                                            |
| [update_category](#update_category) | Updates an existing category's name, receipt threshold, or client reference requirement. **Finance role required** (BR-07, BR-08). Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).                                                                                                                                                                                                                                                                                    |
| [list_expenses](#list_expenses)     | Returns a paginated list of expenses for the authenticated employee. Finance may retrieve expenses across all employees for reporting (BR-14). Supports filtering by date range, category, and claimed status (BR-03). Results are paginated (NFR-05).                                                                                                                                                                                                                                                         |
| [create_expense](#create_expense)   | Records a new expense for the authenticated employee (BR-01). **Business rules:** - Amount must be greater than zero (BR-16) - Date incurred must not be in the future (BR-17) - Category must be an active category (BR-06) - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28) - Client Entertainment requires a client reference (BR-20)                                                                                                        |
| [get_expense](#get_expense)         | Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05). Finance can retrieve any expense.                                                                                                                                                                                                                                                                                                                                                                                      |
| [update_expense](#update_expense)   | Amends an existing expense. Only the owner may update it (BR-02, BR-05). **Business rules:** - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25) - Amount must remain greater than zero (BR-16) - Date incurred must not be in the future (BR-17)                                                                                                                                                                                                                     |
| [delete_expense](#delete_expense)   | Deletes an expense. Only the owner may delete it (BR-02, BR-05). **Business rules:** - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)                                                                                                                                                                                                                                                                                                                             |
| [list_receipts](#list_receipts)     | Returns all receipt attachments for a given expense. Only the expense owner (or Finance) may access receipts (BR-05). **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.                                                                                                                                                                                                                                                                                                    |
| [upload_receipt](#upload_receipt)   | Attaches a receipt image to an expense (BR-04). **Business rules:** - Accepted formats: JPEG, PNG, PDF (NFR-07) - Maximum file size: 10 MB per receipt (NFR-07) - Maximum 10 receipts per expense (NFR-07) - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19) - Client Entertainment always requires a receipt (BR-20) - Only the expense owner may attach receipts (BR-05)                                                                                       |
| [delete_receipt](#delete_receipt)   | Removes a receipt attachment from an expense (BR-04). **Business rules:** - Only the expense owner may remove receipts (BR-05) - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)                                                                                                                                                                                                                                                                                 |
| [list_claims](#list_claims)         | Returns a paginated list of claims. - Employees see only their own claims (BR-10) - Approvers see claims submitted to them (BR-11) - Finance sees all claims (BR-14) Supports filtering by status. Results are paginated (NFR-05).                                                                                                                                                                                                                                                                             |
| [create_claim](#create_claim)       | Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09). **Business rules:** - A claim must contain at least one expense (BR-21) - Each expense may belong to at most one claim (BR-22) - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18) - All expenses must belong to the authenticated employee                                                                                                                                                     |
| [get_claim](#get_claim)             | Returns a single claim by ID, including all its expenses and receipts (BR-11). - Employees can only see their own claims (BR-10) - Approvers can see claims submitted to them (BR-11) - Finance can see all claims (BR-14)                                                                                                                                                                                                                                                                                     |
| [update_claim](#update_claim)       | Updates a claim's title or expense list while it is in draft status. Only the claim owner may update it. **Business rules:** - Can only update a claim in draft status - Expenses added must be unclaimed and belong to the owner (BR-22) - Expenses incurred more than 90 days ago cannot be added (BR-18) - A claim must retain at least one expense (BR-21)                                                                                                                                                 |
| [delete_claim](#delete_claim)       | Deletes a claim in draft status. Only the claim owner may delete it. **Business rules:** - Only draft claims can be deleted - Reimbursed claims are final and cannot be deleted (BR-27) - Deleting a claim releases its expenses back to unclaimed status                                                                                                                                                                                                                                                      |
| [submit_claim](#submit_claim)       | Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23). **Business rules enforced on submission:** - Claim must be in draft status (BR-23) - Claim must contain at least one expense (BR-21) - Any expense requiring a receipt (amount \> category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20) - No expense in the claim may be older than 90 days (BR-18) - Status change is recorded with actor and timestamp (BR-29) |
| [approve_claim](#approve_claim)     | Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - An approver cannot approve a claim they submitted themselves (BR-26) - Status change is recorded with actor and timestamp (BR-29)                                                                                                                                                                                     |
| [reject_claim](#reject_claim)       | Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - A rejection reason is required (BR-24) - The rejected claim returns to draft so the employee can correct and resubmit (BR-24) - The rejection reason is preserved on the claim (BR-24) - Status change is recorded with actor and timestamp (BR-29)                           |
| [reimburse_claim](#reimburse_claim) | Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23). **Finance role required.** **Business rules:** - Claim must be in approved status (BR-23) - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27) - Status change is recorded with actor and timestamp (BR-29) - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred                                                           |

## list_categories

Returns all active categories available for use on new expenses. Finance may also see retired categories by passing `includeRetired=true`. **BR-06** – Any authenticated user can retrieve the category list. **BR-08** – Read-only for employees and approvers.

- HTTP Method: `GET`
- Endpoint: `/categories`

**Parameters**

| Name            | Type | Required | Description                                                               |
| :-------------- | :--- | :------- | :------------------------------------------------------------------------ |
| include_retired | str  | ❌       | Set to true (Finance only) to include retired categories in the response. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.list_categories(include_retired="false")

print(result)
```

## get_category

Returns a single category by its ID, including whether it is active or retired.

- HTTP Method: `GET`
- Endpoint: `/categories/{categoryId}`

**Parameters**

| Name        | Type | Required | Description                            |
| :---------- | :--- | :------- | :------------------------------------- |
| category_id | str  | ✅       | The unique identifier of the category. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.get_category(category_id="cat_01")

print(result)
```

## create_category

Creates a new expense category. **Finance role required** (BR-07, BR-08). The `receiptThreshold` is the amount above which a receipt becomes mandatory. Set `requiresReceiptAlways: true` to mandate a receipt at any amount. Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).

- HTTP Method: `POST`
- Endpoint: `/categories`

**Parameters**

| Name         | Type                                                        | Required | Description       |
| :----------- | :---------------------------------------------------------- | :------- | :---------------- |
| request_body | [CreateCategoryRequest](../models/CreateCategoryRequest.md) | ✅       | The request body. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment
from expense_tracker_api_sdk.models import CreateCategoryRequest

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

request_body = CreateCategoryRequest(
    name="Client Entertainment",
    receipt_threshold=0.01,
    requires_receipt_always=True,
    requires_client_reference=True
)

result = sdk.expense_tracker_api_sdk.create_category(request_body=request_body)

print(result)
```

## update_category

Updates an existing category's name, receipt threshold, or client reference requirement. **Finance role required** (BR-07, BR-08). Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).

- HTTP Method: `PATCH`
- Endpoint: `/categories/{categoryId}`

**Parameters**

| Name         | Type                                                        | Required | Description                                      |
| :----------- | :---------------------------------------------------------- | :------- | :----------------------------------------------- |
| request_body | [UpdateCategoryRequest](../models/UpdateCategoryRequest.md) | ✅       | The request body.                                |
| category_id  | str                                                         | ✅       | The unique identifier of the category to update. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment
from expense_tracker_api_sdk.models import UpdateCategoryRequest

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

request_body = UpdateCategoryRequest(
    receipt_threshold=75
)

result = sdk.expense_tracker_api_sdk.update_category(
    request_body=request_body,
    category_id="cat_01"
)

print(result)
```

## list_expenses

Returns a paginated list of expenses for the authenticated employee. Finance may retrieve expenses across all employees for reporting (BR-14). Supports filtering by date range, category, and claimed status (BR-03). Results are paginated (NFR-05).

- HTTP Method: `GET`
- Endpoint: `/expenses`

**Parameters**

| Name        | Type | Required | Description                                                            |
| :---------- | :--- | :------- | :--------------------------------------------------------------------- |
| date_from   | str  | ❌       | Filter expenses incurred on or after this date (ISO 8601).             |
| date_to     | str  | ❌       | Filter expenses incurred on or before this date (ISO 8601).            |
| category_id | str  | ❌       | Filter by category ID.                                                 |
| claimed     | str  | ❌       | Filter by whether the expense has been added to a claim. true \| false |
| page        | str  | ❌       | Page number (1-based).                                                 |
| page_size   | str  | ❌       | Number of results per page. Max 100.                                   |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.list_expenses(
    date_from="2026-09-01",
    date_to="2026-09-30",
    category_id="cat_01",
    claimed="false",
    page="1",
    page_size="20"
)

print(result)
```

## create_expense

Records a new expense for the authenticated employee (BR-01). **Business rules:** - Amount must be greater than zero (BR-16) - Date incurred must not be in the future (BR-17) - Category must be an active category (BR-06) - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28) - Client Entertainment requires a client reference (BR-20)

- HTTP Method: `POST`
- Endpoint: `/expenses`

**Parameters**

| Name         | Type                                                      | Required | Description       |
| :----------- | :-------------------------------------------------------- | :------- | :---------------- |
| request_body | [CreateExpenseRequest](../models/CreateExpenseRequest.md) | ✅       | The request body. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment
from expense_tracker_api_sdk.models import CreateExpenseRequest

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

request_body = CreateExpenseRequest(
    amount=120.5,
    currency="GBP",
    date_incurred="2026-09-10",
    category_id="cat_01",
    description="Train to Manchester client site",
    payment_method="personal",
    client_reference=""
)

result = sdk.expense_tracker_api_sdk.create_expense(request_body=request_body)

print(result)
```

## get_expense

Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05). Finance can retrieve any expense.

- HTTP Method: `GET`
- Endpoint: `/expenses/{expenseId}`

**Parameters**

| Name       | Type | Required | Description                           |
| :--------- | :--- | :------- | :------------------------------------ |
| expense_id | str  | ✅       | The unique identifier of the expense. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.get_expense(expense_id="exp_001")

print(result)
```

## update_expense

Amends an existing expense. Only the owner may update it (BR-02, BR-05). **Business rules:** - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25) - Amount must remain greater than zero (BR-16) - Date incurred must not be in the future (BR-17)

- HTTP Method: `PATCH`
- Endpoint: `/expenses/{expenseId}`

**Parameters**

| Name         | Type                                                      | Required | Description                                     |
| :----------- | :-------------------------------------------------------- | :------- | :---------------------------------------------- |
| request_body | [UpdateExpenseRequest](../models/UpdateExpenseRequest.md) | ✅       | The request body.                               |
| expense_id   | str                                                       | ✅       | The unique identifier of the expense to update. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment
from expense_tracker_api_sdk.models import UpdateExpenseRequest

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

request_body = UpdateExpenseRequest(
    description="Train to Manchester client site (return)",
    amount=135
)

result = sdk.expense_tracker_api_sdk.update_expense(
    request_body=request_body,
    expense_id="exp_001"
)

print(result)
```

## delete_expense

Deletes an expense. Only the owner may delete it (BR-02, BR-05). **Business rules:** - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)

- HTTP Method: `DELETE`
- Endpoint: `/expenses/{expenseId}`

**Parameters**

| Name       | Type | Required | Description                                     |
| :--------- | :--- | :------- | :---------------------------------------------- |
| expense_id | str  | ✅       | The unique identifier of the expense to delete. |

**Return Type**

`str`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.delete_expense(expense_id="exp_001")

with open("output-file.ext", "w") as f:
    f.write(result if isinstance(result, str) else result.decode(errors="replace") if isinstance(result, (bytes, bytearray)) else str(result))
```

## list_receipts

Returns all receipt attachments for a given expense. Only the expense owner (or Finance) may access receipts (BR-05). **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.

- HTTP Method: `GET`
- Endpoint: `/expenses/{expenseId}/receipts`

**Parameters**

| Name       | Type | Required | Description                           |
| :--------- | :--- | :------- | :------------------------------------ |
| expense_id | str  | ✅       | The unique identifier of the expense. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.list_receipts(expense_id="exp_001")

print(result)
```

## upload_receipt

Attaches a receipt image to an expense (BR-04). **Business rules:** - Accepted formats: JPEG, PNG, PDF (NFR-07) - Maximum file size: 10 MB per receipt (NFR-07) - Maximum 10 receipts per expense (NFR-07) - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19) - Client Entertainment always requires a receipt (BR-20) - Only the expense owner may attach receipts (BR-05)

- HTTP Method: `POST`
- Endpoint: `/expenses/{expenseId}/receipts`

**Parameters**

| Name         | Type                                                      | Required | Description                           |
| :----------- | :-------------------------------------------------------- | :------- | :------------------------------------ |
| request_body | [UploadReceiptRequest](../models/UploadReceiptRequest.md) | ✅       | The request body.                     |
| expense_id   | str                                                       | ✅       | The unique identifier of the expense. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment
from expense_tracker_api_sdk.models import UploadReceiptRequest

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

request_body = UploadReceiptRequest(
    file="",
    filename="train_receipt.jpg"
)

result = sdk.expense_tracker_api_sdk.upload_receipt(
    request_body=request_body,
    expense_id="exp_001"
)

print(result)
```

## delete_receipt

Removes a receipt attachment from an expense (BR-04). **Business rules:** - Only the expense owner may remove receipts (BR-05) - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)

- HTTP Method: `DELETE`
- Endpoint: `/expenses/{expenseId}/receipts/{receiptId}`

**Parameters**

| Name       | Type | Required | Description                                     |
| :--------- | :--- | :------- | :---------------------------------------------- |
| expense_id | str  | ✅       | The unique identifier of the expense.           |
| receipt_id | str  | ✅       | The unique identifier of the receipt to delete. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.delete_receipt(
    expense_id="exp_001",
    receipt_id="rec_001"
)

print(result)
```

## list_claims

Returns a paginated list of claims. - Employees see only their own claims (BR-10) - Approvers see claims submitted to them (BR-11) - Finance sees all claims (BR-14) Supports filtering by status. Results are paginated (NFR-05).

- HTTP Method: `GET`
- Endpoint: `/claims`

**Parameters**

| Name      | Type | Required | Description                                                                      |
| :-------- | :--- | :------- | :------------------------------------------------------------------------------- |
| status    | str  | ❌       | Filter by claim status: draft \| submitted \| approved \| rejected \| reimbursed |
| page      | str  | ❌       | Page number (1-based).                                                           |
| page_size | str  | ❌       | Number of results per page. Max 100.                                             |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.list_claims(
    status="submitted",
    page="1",
    page_size="20"
)

print(result)
```

## create_claim

Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09). **Business rules:** - A claim must contain at least one expense (BR-21) - Each expense may belong to at most one claim (BR-22) - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18) - All expenses must belong to the authenticated employee

- HTTP Method: `POST`
- Endpoint: `/claims`

**Parameters**

| Name         | Type                                                  | Required | Description       |
| :----------- | :---------------------------------------------------- | :------- | :---------------- |
| request_body | [CreateClaimRequest](../models/CreateClaimRequest.md) | ✅       | The request body. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment
from expense_tracker_api_sdk.models import CreateClaimRequest

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

request_body = CreateClaimRequest(
    title="September 2026 - Client site visits",
    expense_ids=[
        "exp_001"
    ]
)

result = sdk.expense_tracker_api_sdk.create_claim(request_body=request_body)

print(result)
```

## get_claim

Returns a single claim by ID, including all its expenses and receipts (BR-11). - Employees can only see their own claims (BR-10) - Approvers can see claims submitted to them (BR-11) - Finance can see all claims (BR-14)

- HTTP Method: `GET`
- Endpoint: `/claims/{claimId}`

**Parameters**

| Name     | Type | Required | Description                         |
| :------- | :--- | :------- | :---------------------------------- |
| claim_id | str  | ✅       | The unique identifier of the claim. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.get_claim(claim_id="clm_001")

print(result)
```

## update_claim

Updates a claim's title or expense list while it is in draft status. Only the claim owner may update it. **Business rules:** - Can only update a claim in draft status - Expenses added must be unclaimed and belong to the owner (BR-22) - Expenses incurred more than 90 days ago cannot be added (BR-18) - A claim must retain at least one expense (BR-21)

- HTTP Method: `PATCH`
- Endpoint: `/claims/{claimId}`

**Parameters**

| Name         | Type                                                  | Required | Description                                   |
| :----------- | :---------------------------------------------------- | :------- | :-------------------------------------------- |
| request_body | [CreateClaimRequest](../models/CreateClaimRequest.md) | ✅       | The request body.                             |
| claim_id     | str                                                   | ✅       | The unique identifier of the claim to update. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment
from expense_tracker_api_sdk.models import CreateClaimRequest

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

request_body = CreateClaimRequest(
    title="September 2026 - Client site visits",
    expense_ids=[
        "exp_001"
    ]
)

result = sdk.expense_tracker_api_sdk.update_claim(
    request_body=request_body,
    claim_id="clm_001"
)

print(result)
```

## delete_claim

Deletes a claim in draft status. Only the claim owner may delete it. **Business rules:** - Only draft claims can be deleted - Reimbursed claims are final and cannot be deleted (BR-27) - Deleting a claim releases its expenses back to unclaimed status

- HTTP Method: `DELETE`
- Endpoint: `/claims/{claimId}`

**Parameters**

| Name     | Type | Required | Description                                   |
| :------- | :--- | :------- | :-------------------------------------------- |
| claim_id | str  | ✅       | The unique identifier of the claim to delete. |

**Return Type**

`str`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.delete_claim(claim_id="clm_001")

with open("output-file.ext", "w") as f:
    f.write(result if isinstance(result, str) else result.decode(errors="replace") if isinstance(result, (bytes, bytearray)) else str(result))
```

## submit_claim

Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23). **Business rules enforced on submission:** - Claim must be in draft status (BR-23) - Claim must contain at least one expense (BR-21) - Any expense requiring a receipt (amount \> category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20) - No expense in the claim may be older than 90 days (BR-18) - Status change is recorded with actor and timestamp (BR-29)

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/submit`

**Parameters**

| Name     | Type | Required | Description                                   |
| :------- | :--- | :------- | :-------------------------------------------- |
| claim_id | str  | ✅       | The unique identifier of the claim to submit. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.submit_claim(claim_id="clm_001")

print(result)
```

## approve_claim

Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - An approver cannot approve a claim they submitted themselves (BR-26) - Status change is recorded with actor and timestamp (BR-29)

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/approve`

**Parameters**

| Name     | Type | Required | Description                                    |
| :------- | :--- | :------- | :--------------------------------------------- |
| claim_id | str  | ✅       | The unique identifier of the claim to approve. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.approve_claim(claim_id="clm_010")

print(result)
```

## reject_claim

Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - A rejection reason is required (BR-24) - The rejected claim returns to draft so the employee can correct and resubmit (BR-24) - The rejection reason is preserved on the claim (BR-24) - Status change is recorded with actor and timestamp (BR-29)

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/reject`

**Parameters**

| Name         | Type                                                  | Required | Description                                   |
| :----------- | :---------------------------------------------------- | :------- | :-------------------------------------------- |
| request_body | [RejectClaimRequest](../models/RejectClaimRequest.md) | ✅       | The request body.                             |
| claim_id     | str                                                   | ✅       | The unique identifier of the claim to reject. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment
from expense_tracker_api_sdk.models import RejectClaimRequest

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

request_body = RejectClaimRequest(
    reason="Missing receipt for the Accommodation expense exceeding the threshold. Please attach the hotel invoice and resubmit."
)

result = sdk.expense_tracker_api_sdk.reject_claim(
    request_body=request_body,
    claim_id="clm_010"
)

print(result)
```

## reimburse_claim

Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23). **Finance role required.** **Business rules:** - Claim must be in approved status (BR-23) - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27) - Status change is recorded with actor and timestamp (BR-29) - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/reimburse`

**Parameters**

| Name     | Type | Required | Description                                               |
| :------- | :--- | :------- | :-------------------------------------------------------- |
| claim_id | str  | ✅       | The unique identifier of the claim to mark as reimbursed. |

**Return Type**

`Any`

**Example Usage Code Snippet**

```python
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.reimburse_claim(claim_id="clm_010")

print(result)
```

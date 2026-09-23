# ExpenseTrackerApiSdkService

A list of all methods in the `ExpenseTrackerApiSdkService` service. Click on the method name to view detailed information about that method.

| Methods                           | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| :-------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [listCategories](#listcategories) | Returns all active categories available for use on new expenses. Finance may also see retired categories by passing `includeRetired=true`. **BR-06** – Any authenticated user can retrieve the category list. **BR-08** – Read-only for employees and approvers.                                                                                                                                                                                                                                               |
| [getCategory](#getcategory)       | Returns a single category by its ID, including whether it is active or retired.                                                                                                                                                                                                                                                                                                                                                                                                                                |
| [createCategory](#createcategory) | Creates a new expense category. **Finance role required** (BR-07, BR-08). The `receiptThreshold` is the amount above which a receipt becomes mandatory. Set `requiresReceiptAlways: true` to mandate a receipt at any amount. Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).                                                                                                                                                                                            |
| [updateCategory](#updatecategory) | Updates an existing category's name, receipt threshold, or client reference requirement. **Finance role required** (BR-07, BR-08). Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).                                                                                                                                                                                                                                                                                    |
| [listExpenses](#listexpenses)     | Returns a paginated list of expenses for the authenticated employee. Finance may retrieve expenses across all employees for reporting (BR-14). Supports filtering by date range, category, and claimed status (BR-03). Results are paginated (NFR-05).                                                                                                                                                                                                                                                         |
| [createExpense](#createexpense)   | Records a new expense for the authenticated employee (BR-01). **Business rules:** - Amount must be greater than zero (BR-16) - Date incurred must not be in the future (BR-17) - Category must be an active category (BR-06) - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28) - Client Entertainment requires a client reference (BR-20)                                                                                                        |
| [getExpense](#getexpense)         | Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05). Finance can retrieve any expense.                                                                                                                                                                                                                                                                                                                                                                                      |
| [updateExpense](#updateexpense)   | Amends an existing expense. Only the owner may update it (BR-02, BR-05). **Business rules:** - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25) - Amount must remain greater than zero (BR-16) - Date incurred must not be in the future (BR-17)                                                                                                                                                                                                                     |
| [deleteExpense](#deleteexpense)   | Deletes an expense. Only the owner may delete it (BR-02, BR-05). **Business rules:** - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)                                                                                                                                                                                                                                                                                                                             |
| [listReceipts](#listreceipts)     | Returns all receipt attachments for a given expense. Only the expense owner (or Finance) may access receipts (BR-05). **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.                                                                                                                                                                                                                                                                                                    |
| [uploadReceipt](#uploadreceipt)   | Attaches a receipt image to an expense (BR-04). **Business rules:** - Accepted formats: JPEG, PNG, PDF (NFR-07) - Maximum file size: 10 MB per receipt (NFR-07) - Maximum 10 receipts per expense (NFR-07) - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19) - Client Entertainment always requires a receipt (BR-20) - Only the expense owner may attach receipts (BR-05)                                                                                       |
| [deleteReceipt](#deletereceipt)   | Removes a receipt attachment from an expense (BR-04). **Business rules:** - Only the expense owner may remove receipts (BR-05) - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)                                                                                                                                                                                                                                                                                 |
| [listClaims](#listclaims)         | Returns a paginated list of claims. - Employees see only their own claims (BR-10) - Approvers see claims submitted to them (BR-11) - Finance sees all claims (BR-14) Supports filtering by status. Results are paginated (NFR-05).                                                                                                                                                                                                                                                                             |
| [createClaim](#createclaim)       | Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09). **Business rules:** - A claim must contain at least one expense (BR-21) - Each expense may belong to at most one claim (BR-22) - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18) - All expenses must belong to the authenticated employee                                                                                                                                                     |
| [getClaim](#getclaim)             | Returns a single claim by ID, including all its expenses and receipts (BR-11). - Employees can only see their own claims (BR-10) - Approvers can see claims submitted to them (BR-11) - Finance can see all claims (BR-14)                                                                                                                                                                                                                                                                                     |
| [updateClaim](#updateclaim)       | Updates a claim's title or expense list while it is in draft status. Only the claim owner may update it. **Business rules:** - Can only update a claim in draft status - Expenses added must be unclaimed and belong to the owner (BR-22) - Expenses incurred more than 90 days ago cannot be added (BR-18) - A claim must retain at least one expense (BR-21)                                                                                                                                                 |
| [deleteClaim](#deleteclaim)       | Deletes a claim in draft status. Only the claim owner may delete it. **Business rules:** - Only draft claims can be deleted - Reimbursed claims are final and cannot be deleted (BR-27) - Deleting a claim releases its expenses back to unclaimed status                                                                                                                                                                                                                                                      |
| [submitClaim](#submitclaim)       | Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23). **Business rules enforced on submission:** - Claim must be in draft status (BR-23) - Claim must contain at least one expense (BR-21) - Any expense requiring a receipt (amount \> category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20) - No expense in the claim may be older than 90 days (BR-18) - Status change is recorded with actor and timestamp (BR-29) |
| [approveClaim](#approveclaim)     | Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - An approver cannot approve a claim they submitted themselves (BR-26) - Status change is recorded with actor and timestamp (BR-29)                                                                                                                                                                                     |
| [rejectClaim](#rejectclaim)       | Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - A rejection reason is required (BR-24) - The rejected claim returns to draft so the employee can correct and resubmit (BR-24) - The rejection reason is preserved on the claim (BR-24) - Status change is recorded with actor and timestamp (BR-29)                           |
| [reimburseClaim](#reimburseclaim) | Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23). **Finance role required.** **Business rules:** - Claim must be in approved status (BR-23) - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27) - Status change is recorded with actor and timestamp (BR-29) - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred                                                           |

## listCategories

Returns all active categories available for use on new expenses. Finance may also see retired categories by passing `includeRetired=true`. **BR-06** – Any authenticated user can retrieve the category list. **BR-08** – Read-only for employees and approvers.

- HTTP Method: `GET`
- Endpoint: `/categories`

**Parameters**

| Name           | Type   | Required | Description                                                               |
| :------------- | :----- | :------- | :------------------------------------------------------------------------ |
| includeRetired | string | ❌       | Set to true (Finance only) to include retired categories in the response. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.listCategories({
    includeRetired: 'false',
  });

  console.log(data);
})();
```

## getCategory

Returns a single category by its ID, including whether it is active or retired.

- HTTP Method: `GET`
- Endpoint: `/categories/{categoryId}`

**Parameters**

| Name       | Type   | Required | Description                            |
| :--------- | :----- | :------- | :------------------------------------- |
| categoryId | string | ✅       | The unique identifier of the category. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.getCategory('cat_01');

  console.log(data);
})();
```

## createCategory

Creates a new expense category. **Finance role required** (BR-07, BR-08). The `receiptThreshold` is the amount above which a receipt becomes mandatory. Set `requiresReceiptAlways: true` to mandate a receipt at any amount. Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).

- HTTP Method: `POST`
- Endpoint: `/categories`

**Parameters**

| Name | Type                                                        | Required | Description       |
| :--- | :---------------------------------------------------------- | :------- | :---------------- |
| body | [CreateCategoryRequest](../models/CreateCategoryRequest.md) | ✅       | The request body. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { CreateCategoryRequest, ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const createCategoryRequest: CreateCategoryRequest = {
    name: 'Client Entertainment',
    receiptThreshold: 0.01,
    requiresReceiptAlways: true,
    requiresClientReference: true,
  };

  const data =
    await expenseTrackerApiSdk.expenseTrackerApiSdk.createCategory(createCategoryRequest);

  console.log(data);
})();
```

## updateCategory

Updates an existing category's name, receipt threshold, or client reference requirement. **Finance role required** (BR-07, BR-08). Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).

- HTTP Method: `PATCH`
- Endpoint: `/categories/{categoryId}`

**Parameters**

| Name       | Type                                                        | Required | Description                                      |
| :--------- | :---------------------------------------------------------- | :------- | :----------------------------------------------- |
| body       | [UpdateCategoryRequest](../models/UpdateCategoryRequest.md) | ✅       | The request body.                                |
| categoryId | string                                                      | ✅       | The unique identifier of the category to update. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk, UpdateCategoryRequest } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const updateCategoryRequest: UpdateCategoryRequest = {
    receiptThreshold: 75,
  };

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.updateCategory(
    'cat_01',
    updateCategoryRequest,
  );

  console.log(data);
})();
```

## listExpenses

Returns a paginated list of expenses for the authenticated employee. Finance may retrieve expenses across all employees for reporting (BR-14). Supports filtering by date range, category, and claimed status (BR-03). Results are paginated (NFR-05).

- HTTP Method: `GET`
- Endpoint: `/expenses`

**Parameters**

| Name       | Type   | Required | Description                                                            |
| :--------- | :----- | :------- | :--------------------------------------------------------------------- |
| dateFrom   | string | ❌       | Filter expenses incurred on or after this date (ISO 8601).             |
| dateTo     | string | ❌       | Filter expenses incurred on or before this date (ISO 8601).            |
| categoryId | string | ❌       | Filter by category ID.                                                 |
| claimed    | string | ❌       | Filter by whether the expense has been added to a claim. true \| false |
| page       | string | ❌       | Page number (1-based).                                                 |
| pageSize   | string | ❌       | Number of results per page. Max 100.                                   |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.listExpenses({
    dateFrom: '2026-09-01',
    dateTo: '2026-09-30',
    categoryId: 'cat_01',
    claimed: 'false',
    page: '1',
    pageSize: '20',
  });

  console.log(data);
})();
```

## createExpense

Records a new expense for the authenticated employee (BR-01). **Business rules:** - Amount must be greater than zero (BR-16) - Date incurred must not be in the future (BR-17) - Category must be an active category (BR-06) - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28) - Client Entertainment requires a client reference (BR-20)

- HTTP Method: `POST`
- Endpoint: `/expenses`

**Parameters**

| Name | Type                                                      | Required | Description       |
| :--- | :-------------------------------------------------------- | :------- | :---------------- |
| body | [CreateExpenseRequest](../models/CreateExpenseRequest.md) | ✅       | The request body. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { CreateExpenseRequest, ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const createExpenseRequest: CreateExpenseRequest = {
    amount: 120.5,
    currency: 'GBP',
    dateIncurred: '2026-09-10',
    categoryId: 'cat_01',
    description: 'Train to Manchester client site',
    paymentMethod: 'personal',
    clientReference: [],
  };

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.createExpense(createExpenseRequest);

  console.log(data);
})();
```

## getExpense

Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05). Finance can retrieve any expense.

- HTTP Method: `GET`
- Endpoint: `/expenses/{expenseId}`

**Parameters**

| Name      | Type   | Required | Description                           |
| :-------- | :----- | :------- | :------------------------------------ |
| expenseId | string | ✅       | The unique identifier of the expense. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.getExpense('exp_001');

  console.log(data);
})();
```

## updateExpense

Amends an existing expense. Only the owner may update it (BR-02, BR-05). **Business rules:** - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25) - Amount must remain greater than zero (BR-16) - Date incurred must not be in the future (BR-17)

- HTTP Method: `PATCH`
- Endpoint: `/expenses/{expenseId}`

**Parameters**

| Name      | Type                                                      | Required | Description                                     |
| :-------- | :-------------------------------------------------------- | :------- | :---------------------------------------------- |
| body      | [UpdateExpenseRequest](../models/UpdateExpenseRequest.md) | ✅       | The request body.                               |
| expenseId | string                                                    | ✅       | The unique identifier of the expense to update. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk, UpdateExpenseRequest } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const updateExpenseRequest: UpdateExpenseRequest = {
    description: 'Train to Manchester client site (return)',
    amount: 135,
  };

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.updateExpense(
    'exp_001',
    updateExpenseRequest,
  );

  console.log(data);
})();
```

## deleteExpense

Deletes an expense. Only the owner may delete it (BR-02, BR-05). **Business rules:** - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)

- HTTP Method: `DELETE`
- Endpoint: `/expenses/{expenseId}`

**Parameters**

| Name      | Type   | Required | Description                                     |
| :-------- | :----- | :------- | :---------------------------------------------- |
| expenseId | string | ✅       | The unique identifier of the expense to delete. |

**Return Type**

`string`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.deleteExpense('exp_001');

  console.log(data);
})();
```

## listReceipts

Returns all receipt attachments for a given expense. Only the expense owner (or Finance) may access receipts (BR-05). **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.

- HTTP Method: `GET`
- Endpoint: `/expenses/{expenseId}/receipts`

**Parameters**

| Name      | Type   | Required | Description                           |
| :-------- | :----- | :------- | :------------------------------------ |
| expenseId | string | ✅       | The unique identifier of the expense. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.listReceipts('exp_001');

  console.log(data);
})();
```

## uploadReceipt

Attaches a receipt image to an expense (BR-04). **Business rules:** - Accepted formats: JPEG, PNG, PDF (NFR-07) - Maximum file size: 10 MB per receipt (NFR-07) - Maximum 10 receipts per expense (NFR-07) - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19) - Client Entertainment always requires a receipt (BR-20) - Only the expense owner may attach receipts (BR-05)

- HTTP Method: `POST`
- Endpoint: `/expenses/{expenseId}/receipts`

**Parameters**

| Name      | Type                                                      | Required | Description                           |
| :-------- | :-------------------------------------------------------- | :------- | :------------------------------------ |
| body      | [UploadReceiptRequest](../models/UploadReceiptRequest.md) | ✅       | The request body.                     |
| expenseId | string                                                    | ✅       | The unique identifier of the expense. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk, UploadReceiptRequest } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const uploadReceiptRequest: UploadReceiptRequest = {
    file: new ArrayBuffer(0),
    filename: 'train_receipt.jpg',
  };

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.uploadReceipt(
    'exp_001',
    uploadReceiptRequest,
  );

  console.log(data);
})();
```

## deleteReceipt

Removes a receipt attachment from an expense (BR-04). **Business rules:** - Only the expense owner may remove receipts (BR-05) - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)

- HTTP Method: `DELETE`
- Endpoint: `/expenses/{expenseId}/receipts/{receiptId}`

**Parameters**

| Name      | Type   | Required | Description                                     |
| :-------- | :----- | :------- | :---------------------------------------------- |
| expenseId | string | ✅       | The unique identifier of the expense.           |
| receiptId | string | ✅       | The unique identifier of the receipt to delete. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.deleteReceipt('exp_001', 'rec_001');

  console.log(data);
})();
```

## listClaims

Returns a paginated list of claims. - Employees see only their own claims (BR-10) - Approvers see claims submitted to them (BR-11) - Finance sees all claims (BR-14) Supports filtering by status. Results are paginated (NFR-05).

- HTTP Method: `GET`
- Endpoint: `/claims`

**Parameters**

| Name     | Type   | Required | Description                                                                      |
| :------- | :----- | :------- | :------------------------------------------------------------------------------- |
| status   | string | ❌       | Filter by claim status: draft \| submitted \| approved \| rejected \| reimbursed |
| page     | string | ❌       | Page number (1-based).                                                           |
| pageSize | string | ❌       | Number of results per page. Max 100.                                             |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.listClaims({
    status: 'submitted',
    page: '1',
    pageSize: '20',
  });

  console.log(data);
})();
```

## createClaim

Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09). **Business rules:** - A claim must contain at least one expense (BR-21) - Each expense may belong to at most one claim (BR-22) - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18) - All expenses must belong to the authenticated employee

- HTTP Method: `POST`
- Endpoint: `/claims`

**Parameters**

| Name | Type                                                  | Required | Description       |
| :--- | :---------------------------------------------------- | :------- | :---------------- |
| body | [CreateClaimRequest](../models/CreateClaimRequest.md) | ✅       | The request body. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { CreateClaimRequest, ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const createClaimRequest: CreateClaimRequest = {
    title: 'September 2026 - Client site visits',
    expenseIds: ['exp_001'],
  };

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.createClaim(createClaimRequest);

  console.log(data);
})();
```

## getClaim

Returns a single claim by ID, including all its expenses and receipts (BR-11). - Employees can only see their own claims (BR-10) - Approvers can see claims submitted to them (BR-11) - Finance can see all claims (BR-14)

- HTTP Method: `GET`
- Endpoint: `/claims/{claimId}`

**Parameters**

| Name    | Type   | Required | Description                         |
| :------ | :----- | :------- | :---------------------------------- |
| claimId | string | ✅       | The unique identifier of the claim. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.getClaim('clm_001');

  console.log(data);
})();
```

## updateClaim

Updates a claim's title or expense list while it is in draft status. Only the claim owner may update it. **Business rules:** - Can only update a claim in draft status - Expenses added must be unclaimed and belong to the owner (BR-22) - Expenses incurred more than 90 days ago cannot be added (BR-18) - A claim must retain at least one expense (BR-21)

- HTTP Method: `PATCH`
- Endpoint: `/claims/{claimId}`

**Parameters**

| Name    | Type                                                  | Required | Description                                   |
| :------ | :---------------------------------------------------- | :------- | :-------------------------------------------- |
| body    | [CreateClaimRequest](../models/CreateClaimRequest.md) | ✅       | The request body.                             |
| claimId | string                                                | ✅       | The unique identifier of the claim to update. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { CreateClaimRequest, ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const createClaimRequest: CreateClaimRequest = {
    title: 'September 2026 - Client site visits',
    expenseIds: ['exp_001'],
  };

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.updateClaim(
    'clm_001',
    createClaimRequest,
  );

  console.log(data);
})();
```

## deleteClaim

Deletes a claim in draft status. Only the claim owner may delete it. **Business rules:** - Only draft claims can be deleted - Reimbursed claims are final and cannot be deleted (BR-27) - Deleting a claim releases its expenses back to unclaimed status

- HTTP Method: `DELETE`
- Endpoint: `/claims/{claimId}`

**Parameters**

| Name    | Type   | Required | Description                                   |
| :------ | :----- | :------- | :-------------------------------------------- |
| claimId | string | ✅       | The unique identifier of the claim to delete. |

**Return Type**

`string`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.deleteClaim('clm_001');

  console.log(data);
})();
```

## submitClaim

Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23). **Business rules enforced on submission:** - Claim must be in draft status (BR-23) - Claim must contain at least one expense (BR-21) - Any expense requiring a receipt (amount \> category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20) - No expense in the claim may be older than 90 days (BR-18) - Status change is recorded with actor and timestamp (BR-29)

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/submit`

**Parameters**

| Name    | Type   | Required | Description                                   |
| :------ | :----- | :------- | :-------------------------------------------- |
| claimId | string | ✅       | The unique identifier of the claim to submit. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.submitClaim('clm_001');

  console.log(data);
})();
```

## approveClaim

Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - An approver cannot approve a claim they submitted themselves (BR-26) - Status change is recorded with actor and timestamp (BR-29)

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/approve`

**Parameters**

| Name    | Type   | Required | Description                                    |
| :------ | :----- | :------- | :--------------------------------------------- |
| claimId | string | ✅       | The unique identifier of the claim to approve. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.approveClaim('clm_010');

  console.log(data);
})();
```

## rejectClaim

Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - A rejection reason is required (BR-24) - The rejected claim returns to draft so the employee can correct and resubmit (BR-24) - The rejection reason is preserved on the claim (BR-24) - Status change is recorded with actor and timestamp (BR-29)

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/reject`

**Parameters**

| Name    | Type                                                  | Required | Description                                   |
| :------ | :---------------------------------------------------- | :------- | :-------------------------------------------- |
| body    | [RejectClaimRequest](../models/RejectClaimRequest.md) | ✅       | The request body.                             |
| claimId | string                                                | ✅       | The unique identifier of the claim to reject. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk, RejectClaimRequest } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const rejectClaimRequest: RejectClaimRequest = {
    reason:
      'Missing receipt for the Accommodation expense exceeding the threshold. Please attach the hotel invoice and resubmit.',
  };

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.rejectClaim(
    'clm_010',
    rejectClaimRequest,
  );

  console.log(data);
})();
```

## reimburseClaim

Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23). **Finance role required.** **Business rules:** - Claim must be in approved status (BR-23) - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27) - Status change is recorded with actor and timestamp (BR-29) - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/reimburse`

**Parameters**

| Name    | Type   | Required | Description                                               |
| :------ | :----- | :------- | :-------------------------------------------------------- |
| claimId | string | ✅       | The unique identifier of the claim to mark as reimbursed. |

**Return Type**

`any`

**Example Usage Code Snippet**

```typescript
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.reimburseClaim('clm_010');

  console.log(data);
})();
```

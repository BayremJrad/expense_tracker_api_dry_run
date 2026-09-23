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

| Name              | Type                                                              | Required | Description               |
| :---------------- | :---------------------------------------------------------------- | :------- | :------------------------ |
| requestParameters | [ListCategoriesParameters](../models/ListCategoriesParameters.md) | ❌       | Request Parameters Object |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.ListCategoriesParameters;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    ListCategoriesParameters requestParameters = ListCategoriesParameters.builder()
      .includeRetired("false")
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.listCategories(requestParameters);

    System.out.println(response);
  }
}

```

## getCategory

Returns a single category by its ID, including whether it is active or retired.

- HTTP Method: `GET`
- Endpoint: `/categories/{categoryId}`

**Parameters**

| Name       | Type   | Required | Description                            |
| :--------- | :----- | :------- | :------------------------------------- |
| categoryId | String | ✅       | The unique identifier of the category. |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.getCategory("cat_01");

    System.out.println(response);
  }
}

```

## createCategory

Creates a new expense category. **Finance role required** (BR-07, BR-08). The `receiptThreshold` is the amount above which a receipt becomes mandatory. Set `requiresReceiptAlways: true` to mandate a receipt at any amount. Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).

- HTTP Method: `POST`
- Endpoint: `/categories`

**Parameters**

| Name                  | Type                                                        | Required | Description  |
| :-------------------- | :---------------------------------------------------------- | :------- | :----------- |
| createCategoryRequest | [CreateCategoryRequest](../models/CreateCategoryRequest.md) | ✅       | Request Body |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.CreateCategoryRequest;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    CreateCategoryRequest createCategoryRequest = CreateCategoryRequest.builder()
      .name("Client Entertainment")
      .receiptThreshold(0.01D)
      .requiresReceiptAlways(true)
      .requiresClientReference(true)
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.createCategory(
      createCategoryRequest
    );

    System.out.println(response);
  }
}

```

## updateCategory

Updates an existing category's name, receipt threshold, or client reference requirement. **Finance role required** (BR-07, BR-08). Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).

- HTTP Method: `PATCH`
- Endpoint: `/categories/{categoryId}`

**Parameters**

| Name                  | Type                                                        | Required | Description                                      |
| :-------------------- | :---------------------------------------------------------- | :------- | :----------------------------------------------- |
| categoryId            | String                                                      | ✅       | The unique identifier of the category to update. |
| updateCategoryRequest | [UpdateCategoryRequest](../models/UpdateCategoryRequest.md) | ✅       | Request Body                                     |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.UpdateCategoryRequest;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest.builder()
      .receiptThreshold(75L)
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.updateCategory(
      "cat_01",
      updateCategoryRequest
    );

    System.out.println(response);
  }
}

```

## listExpenses

Returns a paginated list of expenses for the authenticated employee. Finance may retrieve expenses across all employees for reporting (BR-14). Supports filtering by date range, category, and claimed status (BR-03). Results are paginated (NFR-05).

- HTTP Method: `GET`
- Endpoint: `/expenses`

**Parameters**

| Name              | Type                                                          | Required | Description               |
| :---------------- | :------------------------------------------------------------ | :------- | :------------------------ |
| requestParameters | [ListExpensesParameters](../models/ListExpensesParameters.md) | ❌       | Request Parameters Object |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.ListExpensesParameters;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    ListExpensesParameters requestParameters = ListExpensesParameters.builder()
      .dateFrom("2026-09-01")
      .dateTo("2026-09-30")
      .categoryId("cat_01")
      .claimed("false")
      .page("1")
      .pageSize("20")
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.listExpenses(requestParameters);

    System.out.println(response);
  }
}

```

## createExpense

Records a new expense for the authenticated employee (BR-01). **Business rules:** - Amount must be greater than zero (BR-16) - Date incurred must not be in the future (BR-17) - Category must be an active category (BR-06) - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28) - Client Entertainment requires a client reference (BR-20)

- HTTP Method: `POST`
- Endpoint: `/expenses`

**Parameters**

| Name                 | Type                                                      | Required | Description  |
| :------------------- | :-------------------------------------------------------- | :------- | :----------- |
| createExpenseRequest | [CreateExpenseRequest](../models/CreateExpenseRequest.md) | ✅       | Request Body |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.CreateExpenseRequest;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    CreateExpenseRequest createExpenseRequest = CreateExpenseRequest.builder()
      .amount(120.5D)
      .currency("GBP")
      .dateIncurred("2026-09-10")
      .categoryId("cat_01")
      .description("Train to Manchester client site")
      .paymentMethod("personal")
      .clientReference(new Object())
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.createExpense(createExpenseRequest);

    System.out.println(response);
  }
}

```

## getExpense

Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05). Finance can retrieve any expense.

- HTTP Method: `GET`
- Endpoint: `/expenses/{expenseId}`

**Parameters**

| Name      | Type   | Required | Description                           |
| :-------- | :----- | :------- | :------------------------------------ |
| expenseId | String | ✅       | The unique identifier of the expense. |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.getExpense("exp_001");

    System.out.println(response);
  }
}

```

## updateExpense

Amends an existing expense. Only the owner may update it (BR-02, BR-05). **Business rules:** - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25) - Amount must remain greater than zero (BR-16) - Date incurred must not be in the future (BR-17)

- HTTP Method: `PATCH`
- Endpoint: `/expenses/{expenseId}`

**Parameters**

| Name                 | Type                                                      | Required | Description                                     |
| :------------------- | :-------------------------------------------------------- | :------- | :---------------------------------------------- |
| expenseId            | String                                                    | ✅       | The unique identifier of the expense to update. |
| updateExpenseRequest | [UpdateExpenseRequest](../models/UpdateExpenseRequest.md) | ✅       | Request Body                                    |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.UpdateExpenseRequest;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    UpdateExpenseRequest updateExpenseRequest = UpdateExpenseRequest.builder()
      .description("Train to Manchester client site (return)")
      .amount(135L)
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.updateExpense(
      "exp_001",
      updateExpenseRequest
    );

    System.out.println(response);
  }
}

```

## deleteExpense

Deletes an expense. Only the owner may delete it (BR-02, BR-05). **Business rules:** - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)

- HTTP Method: `DELETE`
- Endpoint: `/expenses/{expenseId}`

**Parameters**

| Name      | Type   | Required | Description                                     |
| :-------- | :----- | :------- | :---------------------------------------------- |
| expenseId | String | ✅       | The unique identifier of the expense to delete. |

**Return Type**

`String`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    String response = expenseTrackerApiSdk.expenseTrackerApiSdk.deleteExpense("exp_001");

    System.out.println(response);
  }
}

```

## listReceipts

Returns all receipt attachments for a given expense. Only the expense owner (or Finance) may access receipts (BR-05). **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.

- HTTP Method: `GET`
- Endpoint: `/expenses/{expenseId}/receipts`

**Parameters**

| Name      | Type   | Required | Description                           |
| :-------- | :----- | :------- | :------------------------------------ |
| expenseId | String | ✅       | The unique identifier of the expense. |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.listReceipts("exp_001");

    System.out.println(response);
  }
}

```

## uploadReceipt

Attaches a receipt image to an expense (BR-04). **Business rules:** - Accepted formats: JPEG, PNG, PDF (NFR-07) - Maximum file size: 10 MB per receipt (NFR-07) - Maximum 10 receipts per expense (NFR-07) - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19) - Client Entertainment always requires a receipt (BR-20) - Only the expense owner may attach receipts (BR-05)

- HTTP Method: `POST`
- Endpoint: `/expenses/{expenseId}/receipts`

**Parameters**

| Name                 | Type                                                      | Required | Description                           |
| :------------------- | :-------------------------------------------------------- | :------- | :------------------------------------ |
| expenseId            | String                                                    | ✅       | The unique identifier of the expense. |
| uploadReceiptRequest | [UploadReceiptRequest](../models/UploadReceiptRequest.md) | ✅       | Request Body                          |
| \_filename           | String                                                    | ✅       | Filename for the uploaded file        |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.UploadReceiptRequest;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    byte[] file = new byte[] { 0, 1, 2, 3, 4, 5 };

    UploadReceiptRequest uploadReceiptRequest = UploadReceiptRequest.builder()
      .file(file)
      .filename("train_receipt.jpg")
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.uploadReceipt(
      "exp_001",
      uploadReceiptRequest,
      "filename.txt"
    );

    System.out.println(response);
  }
}

```

## deleteReceipt

Removes a receipt attachment from an expense (BR-04). **Business rules:** - Only the expense owner may remove receipts (BR-05) - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)

- HTTP Method: `DELETE`
- Endpoint: `/expenses/{expenseId}/receipts/{receiptId}`

**Parameters**

| Name      | Type   | Required | Description                                     |
| :-------- | :----- | :------- | :---------------------------------------------- |
| expenseId | String | ✅       | The unique identifier of the expense.           |
| receiptId | String | ✅       | The unique identifier of the receipt to delete. |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.deleteReceipt("exp_001", "rec_001");

    System.out.println(response);
  }
}

```

## listClaims

Returns a paginated list of claims. - Employees see only their own claims (BR-10) - Approvers see claims submitted to them (BR-11) - Finance sees all claims (BR-14) Supports filtering by status. Results are paginated (NFR-05).

- HTTP Method: `GET`
- Endpoint: `/claims`

**Parameters**

| Name              | Type                                                      | Required | Description               |
| :---------------- | :-------------------------------------------------------- | :------- | :------------------------ |
| requestParameters | [ListClaimsParameters](../models/ListClaimsParameters.md) | ❌       | Request Parameters Object |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.ListClaimsParameters;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    ListClaimsParameters requestParameters = ListClaimsParameters.builder()
      .status("submitted")
      .page("1")
      .pageSize("20")
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.listClaims(requestParameters);

    System.out.println(response);
  }
}

```

## createClaim

Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09). **Business rules:** - A claim must contain at least one expense (BR-21) - Each expense may belong to at most one claim (BR-22) - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18) - All expenses must belong to the authenticated employee

- HTTP Method: `POST`
- Endpoint: `/claims`

**Parameters**

| Name               | Type                                                  | Required | Description  |
| :----------------- | :---------------------------------------------------- | :------- | :----------- |
| createClaimRequest | [CreateClaimRequest](../models/CreateClaimRequest.md) | ✅       | Request Body |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.CreateClaimRequest;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    List<String> expenseIdsList = Arrays.asList("exp_001");

    CreateClaimRequest createClaimRequest = CreateClaimRequest.builder()
      .title("September 2026 - Client site visits")
      .expenseIds(expenseIdsList)
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.createClaim(createClaimRequest);

    System.out.println(response);
  }
}

```

## getClaim

Returns a single claim by ID, including all its expenses and receipts (BR-11). - Employees can only see their own claims (BR-10) - Approvers can see claims submitted to them (BR-11) - Finance can see all claims (BR-14)

- HTTP Method: `GET`
- Endpoint: `/claims/{claimId}`

**Parameters**

| Name    | Type   | Required | Description                         |
| :------ | :----- | :------- | :---------------------------------- |
| claimId | String | ✅       | The unique identifier of the claim. |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.getClaim("clm_001");

    System.out.println(response);
  }
}

```

## updateClaim

Updates a claim's title or expense list while it is in draft status. Only the claim owner may update it. **Business rules:** - Can only update a claim in draft status - Expenses added must be unclaimed and belong to the owner (BR-22) - Expenses incurred more than 90 days ago cannot be added (BR-18) - A claim must retain at least one expense (BR-21)

- HTTP Method: `PATCH`
- Endpoint: `/claims/{claimId}`

**Parameters**

| Name               | Type                                                  | Required | Description                                   |
| :----------------- | :---------------------------------------------------- | :------- | :-------------------------------------------- |
| claimId            | String                                                | ✅       | The unique identifier of the claim to update. |
| createClaimRequest | [CreateClaimRequest](../models/CreateClaimRequest.md) | ✅       | Request Body                                  |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.CreateClaimRequest;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    List<String> expenseIdsList = Arrays.asList("exp_001");

    CreateClaimRequest createClaimRequest = CreateClaimRequest.builder()
      .title("September 2026 - Client site visits")
      .expenseIds(expenseIdsList)
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.updateClaim(
      "clm_001",
      createClaimRequest
    );

    System.out.println(response);
  }
}

```

## deleteClaim

Deletes a claim in draft status. Only the claim owner may delete it. **Business rules:** - Only draft claims can be deleted - Reimbursed claims are final and cannot be deleted (BR-27) - Deleting a claim releases its expenses back to unclaimed status

- HTTP Method: `DELETE`
- Endpoint: `/claims/{claimId}`

**Parameters**

| Name    | Type   | Required | Description                                   |
| :------ | :----- | :------- | :-------------------------------------------- |
| claimId | String | ✅       | The unique identifier of the claim to delete. |

**Return Type**

`String`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    String response = expenseTrackerApiSdk.expenseTrackerApiSdk.deleteClaim("clm_001");

    System.out.println(response);
  }
}

```

## submitClaim

Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23). **Business rules enforced on submission:** - Claim must be in draft status (BR-23) - Claim must contain at least one expense (BR-21) - Any expense requiring a receipt (amount \> category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20) - No expense in the claim may be older than 90 days (BR-18) - Status change is recorded with actor and timestamp (BR-29)

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/submit`

**Parameters**

| Name    | Type   | Required | Description                                   |
| :------ | :----- | :------- | :-------------------------------------------- |
| claimId | String | ✅       | The unique identifier of the claim to submit. |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.submitClaim("clm_001");

    System.out.println(response);
  }
}

```

## approveClaim

Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - An approver cannot approve a claim they submitted themselves (BR-26) - Status change is recorded with actor and timestamp (BR-29)

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/approve`

**Parameters**

| Name    | Type   | Required | Description                                    |
| :------ | :----- | :------- | :--------------------------------------------- |
| claimId | String | ✅       | The unique identifier of the claim to approve. |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.approveClaim("clm_010");

    System.out.println(response);
  }
}

```

## rejectClaim

Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - A rejection reason is required (BR-24) - The rejected claim returns to draft so the employee can correct and resubmit (BR-24) - The rejection reason is preserved on the claim (BR-24) - Status change is recorded with actor and timestamp (BR-29)

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/reject`

**Parameters**

| Name               | Type                                                  | Required | Description                                   |
| :----------------- | :---------------------------------------------------- | :------- | :-------------------------------------------- |
| claimId            | String                                                | ✅       | The unique identifier of the claim to reject. |
| rejectClaimRequest | [RejectClaimRequest](../models/RejectClaimRequest.md) | ✅       | Request Body                                  |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.models.RejectClaimRequest;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    RejectClaimRequest rejectClaimRequest = RejectClaimRequest.builder()
      .reason(
        "Missing receipt for the Accommodation expense exceeding the threshold. Please attach the hotel invoice and resubmit."
      )
      .build();

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.rejectClaim(
      "clm_010",
      rejectClaimRequest
    );

    System.out.println(response);
  }
}

```

## reimburseClaim

Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23). **Finance role required.** **Business rules:** - Claim must be in approved status (BR-23) - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27) - Status change is recorded with actor and timestamp (BR-29) - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred

- HTTP Method: `POST`
- Endpoint: `/claims/{claimId}/reimburse`

**Parameters**

| Name    | Type   | Required | Description                                               |
| :------ | :----- | :------- | :-------------------------------------------------------- |
| claimId | String | ✅       | The unique identifier of the claim to mark as reimbursed. |

**Return Type**

`Object`

**Example Usage Code Snippet**

```java
import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.reimburseClaim("clm_010");

    System.out.println(response);
  }
}

```

import { z } from 'zod';
import { BaseService } from '../base-service';
import { ContentType, HttpResponse, SdkConfig } from '../../http/types';
import { RequestBuilder } from '../../http/transport/request-builder';
import { SerializationStyle } from '../../http/serialization/base-serializer';
import { ThrowableError } from '../../http/errors/throwable-error';
import { Environment } from '../../http/environment';
import { ListCategoriesParams, ListClaimsParams, ListExpensesParams } from './request-params';
import {
  CreateCategoryRequest,
  createCategoryRequestRequest,
} from './models/create-category-request';
import {
  UpdateCategoryRequest,
  updateCategoryRequestRequest,
} from './models/update-category-request';
import { CreateExpenseRequest, createExpenseRequestRequest } from './models/create-expense-request';
import { UpdateExpenseRequest, updateExpenseRequestRequest } from './models/update-expense-request';
import { UploadReceiptRequest, uploadReceiptRequestRequest } from './models/upload-receipt-request';
import { CreateClaimRequest, createClaimRequestRequest } from './models/create-claim-request';
import { RejectClaimRequest, rejectClaimRequestRequest } from './models/reject-claim-request';

/**
 * Service class for ExpenseTrackerApiSdkService operations.
 * Provides methods to interact with ExpenseTrackerApiSdkService-related API endpoints.
 * All methods return promises and handle request/response serialization automatically.
 */
export class ExpenseTrackerApiSdkService extends BaseService {
  protected listCategoriesConfig?: Partial<SdkConfig>;

  protected getCategoryConfig?: Partial<SdkConfig>;

  protected createCategoryConfig?: Partial<SdkConfig>;

  protected updateCategoryConfig?: Partial<SdkConfig>;

  protected listExpensesConfig?: Partial<SdkConfig>;

  protected createExpenseConfig?: Partial<SdkConfig>;

  protected getExpenseConfig?: Partial<SdkConfig>;

  protected updateExpenseConfig?: Partial<SdkConfig>;

  protected deleteExpenseConfig?: Partial<SdkConfig>;

  protected listReceiptsConfig?: Partial<SdkConfig>;

  protected uploadReceiptConfig?: Partial<SdkConfig>;

  protected deleteReceiptConfig?: Partial<SdkConfig>;

  protected listClaimsConfig?: Partial<SdkConfig>;

  protected createClaimConfig?: Partial<SdkConfig>;

  protected getClaimConfig?: Partial<SdkConfig>;

  protected updateClaimConfig?: Partial<SdkConfig>;

  protected deleteClaimConfig?: Partial<SdkConfig>;

  protected submitClaimConfig?: Partial<SdkConfig>;

  protected approveClaimConfig?: Partial<SdkConfig>;

  protected rejectClaimConfig?: Partial<SdkConfig>;

  protected reimburseClaimConfig?: Partial<SdkConfig>;

  /**
   * Sets method-level configuration for listCategories.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setListCategoriesConfig(config: Partial<SdkConfig>): this {
    this.listCategoriesConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for getCategory.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setGetCategoryConfig(config: Partial<SdkConfig>): this {
    this.getCategoryConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for createCategory.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setCreateCategoryConfig(config: Partial<SdkConfig>): this {
    this.createCategoryConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for updateCategory.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setUpdateCategoryConfig(config: Partial<SdkConfig>): this {
    this.updateCategoryConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for listExpenses.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setListExpensesConfig(config: Partial<SdkConfig>): this {
    this.listExpensesConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for createExpense.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setCreateExpenseConfig(config: Partial<SdkConfig>): this {
    this.createExpenseConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for getExpense.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setGetExpenseConfig(config: Partial<SdkConfig>): this {
    this.getExpenseConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for updateExpense.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setUpdateExpenseConfig(config: Partial<SdkConfig>): this {
    this.updateExpenseConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for deleteExpense.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setDeleteExpenseConfig(config: Partial<SdkConfig>): this {
    this.deleteExpenseConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for listReceipts.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setListReceiptsConfig(config: Partial<SdkConfig>): this {
    this.listReceiptsConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for uploadReceipt.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setUploadReceiptConfig(config: Partial<SdkConfig>): this {
    this.uploadReceiptConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for deleteReceipt.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setDeleteReceiptConfig(config: Partial<SdkConfig>): this {
    this.deleteReceiptConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for listClaims.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setListClaimsConfig(config: Partial<SdkConfig>): this {
    this.listClaimsConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for createClaim.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setCreateClaimConfig(config: Partial<SdkConfig>): this {
    this.createClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for getClaim.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setGetClaimConfig(config: Partial<SdkConfig>): this {
    this.getClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for updateClaim.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setUpdateClaimConfig(config: Partial<SdkConfig>): this {
    this.updateClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for deleteClaim.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setDeleteClaimConfig(config: Partial<SdkConfig>): this {
    this.deleteClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for submitClaim.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setSubmitClaimConfig(config: Partial<SdkConfig>): this {
    this.submitClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for approveClaim.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setApproveClaimConfig(config: Partial<SdkConfig>): this {
    this.approveClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for rejectClaim.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setRejectClaimConfig(config: Partial<SdkConfig>): this {
    this.rejectClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for reimburseClaim.
   * @param config - Partial configuration to override service-level defaults
   * @returns This service instance for method chaining
   */
  setReimburseClaimConfig(config: Partial<SdkConfig>): this {
    this.reimburseClaimConfig = config;
    return this;
  }

  /**
 * Returns all active categories available for use on new expenses.Finance may also see retired categories by passing `includeRetired=true`.

**BR-06** – Any authenticated user can retrieve the category list.
**BR-08** – Read-only for employees and approvers.
 * @param {string} [params.includeRetired] - Set to true (Finance only) to include retired categories in the response.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Active categories
 */
  async listCategories(
    params?: ListCategoriesParams,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.listCategoriesConfig, requestConfig);
    z.object({ includeRetired: z.string().optional() }).parse(params ?? {});
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('GET')
      .setPath('/categories')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 401,
      })
      .addQueryParam({
        key: 'includeRetired',
        value: params?.includeRetired,
      })
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
   * Returns a single category by its ID, including whether it is active or retired.
   * @param {string} categoryId - The unique identifier of the category.
   * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
   * @returns {Promise<HttpResponse<any>>} - 200 OK - Category found
   */
  async getCategory(categoryId: string, requestConfig?: Partial<SdkConfig>): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.getCategoryConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('GET')
      .setPath('/categories/{categoryId}')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 404,
      })
      .addPathParam({
        key: 'categoryId',
        value: categoryId,
      })
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Creates a new expense category. **Finance role required** (BR-07, BR-08).
The `receiptThreshold` is the amount above which a receipt becomes mandatory.
Set `requiresReceiptAlways: true` to mandate a receipt at any amount.
Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 201 Created
 */
  async createCategory(
    body: CreateCategoryRequest,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.createCategoryConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/categories')
      .setRequestSchema(createCategoryRequestRequest)
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 201,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 403,
      })
      .addHeaderParam({ key: 'Content-Type', value: 'application/json' })
      .addBody(body)
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Updates an existing category's name, receipt threshold, or client reference requirement.**Finance role required** (BR-07, BR-08).

Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).
 * @param {string} categoryId - The unique identifier of the category to update.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Category retired
 */
  async updateCategory(
    categoryId: string,
    body: UpdateCategoryRequest,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any | any> {
    const resolvedConfig = this.getResolvedConfig(this.updateCategoryConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('PATCH')
      .setPath('/categories/{categoryId}')
      .setRequestSchema(updateCategoryRequestRequest)
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addPathParam({
        key: 'categoryId',
        value: categoryId,
      })
      .addHeaderParam({ key: 'Content-Type', value: 'application/json' })
      .addBody(body)
      .build();
    return this.client.callDirect<any | any>(request);
  }

  /**
 * Returns a paginated list of expenses for the authenticated employee.Finance may retrieve expenses across all employees for reporting (BR-14).

Supports filtering by date range, category, and claimed status (BR-03).
Results are paginated (NFR-05).
 * @param {string} [params.dateFrom] - Filter expenses incurred on or after this date (ISO 8601).
 * @param {string} [params.dateTo] - Filter expenses incurred on or before this date (ISO 8601).
 * @param {string} [params.categoryId] - Filter by category ID.
 * @param {string} [params.claimed] - Filter by whether the expense has been added to a claim. true | false
 * @param {string} [params.page] - Page number (1-based).
 * @param {string} [params.pageSize] - Number of results per page. Max 100.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Expenses list
 */
  async listExpenses(
    params?: ListExpensesParams,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any | any> {
    const resolvedConfig = this.getResolvedConfig(this.listExpensesConfig, requestConfig);
    z.object({
      dateFrom: z.string().optional(),
      dateTo: z.string().optional(),
      categoryId: z.string().optional(),
      claimed: z.string().optional(),
      page: z.string().optional(),
      pageSize: z.string().optional(),
    }).parse(params ?? {});
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('GET')
      .setPath('/expenses')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addQueryParam({
        key: 'dateFrom',
        value: params?.dateFrom,
      })
      .addQueryParam({
        key: 'dateTo',
        value: params?.dateTo,
      })
      .addQueryParam({
        key: 'categoryId',
        value: params?.categoryId,
      })
      .addQueryParam({
        key: 'claimed',
        value: params?.claimed,
      })
      .addQueryParam({
        key: 'page',
        value: params?.page,
      })
      .addQueryParam({
        key: 'pageSize',
        value: params?.pageSize,
      })
      .build();
    return this.client.callDirect<any | any>(request);
  }

  /**
 * Records a new expense for the authenticated employee (BR-01).
**Business rules:**
- Amount must be greater than zero (BR-16)
- Date incurred must not be in the future (BR-17)
- Category must be an active category (BR-06)
- For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28)
- Client Entertainment requires a client reference (BR-20)
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 201 Created
 */
  async createExpense(
    body: CreateExpenseRequest,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.createExpenseConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/expenses')
      .setRequestSchema(createExpenseRequestRequest)
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 201,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 422,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 422,
      })
      .addHeaderParam({ key: 'Content-Type', value: 'application/json' })
      .addBody(body)
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
   * Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05).Finance can retrieve any expense.
   * @param {string} expenseId - The unique identifier of the expense.
   * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
   * @returns {Promise<HttpResponse<any>>} - 200 OK - Expense found
   */
  async getExpense(expenseId: string, requestConfig?: Partial<SdkConfig>): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.getExpenseConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('GET')
      .setPath('/expenses/{expenseId}')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 403,
      })
      .addPathParam({
        key: 'expenseId',
        value: expenseId,
      })
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Amends an existing expense. Only the owner may update it (BR-02, BR-05).
**Business rules:**
- Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
- Amount must remain greater than zero (BR-16)
- Date incurred must not be in the future (BR-17)
 * @param {string} expenseId - The unique identifier of the expense to update.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Updated
 */
  async updateExpense(
    expenseId: string,
    body: UpdateExpenseRequest,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.updateExpenseConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('PATCH')
      .setPath('/expenses/{expenseId}')
      .setRequestSchema(updateExpenseRequestRequest)
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 409,
      })
      .addPathParam({
        key: 'expenseId',
        value: expenseId,
      })
      .addHeaderParam({ key: 'Content-Type', value: 'application/json' })
      .addBody(body)
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Deletes an expense. Only the owner may delete it (BR-02, BR-05).
**Business rules:**
- Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
 * @param {string} expenseId - The unique identifier of the expense to delete.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<string>>} - 204 No Content - Deleted
 */
  async deleteExpense(expenseId: string, requestConfig?: Partial<SdkConfig>): Promise<string> {
    const resolvedConfig = this.getResolvedConfig(this.deleteExpenseConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('DELETE')
      .setPath('/expenses/{expenseId}')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.string(),
        contentType: ContentType.Json,
        status: 204,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 409,
      })
      .addPathParam({
        key: 'expenseId',
        value: expenseId,
      })
      .build();
    return this.client.callDirect<string>(request);
  }

  /**
 * Returns all receipt attachments for a given expense.Only the expense owner (or Finance) may access receipts (BR-05).

**NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.
 * @param {string} expenseId - The unique identifier of the expense.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - No receipts
 */
  async listReceipts(expenseId: string, requestConfig?: Partial<SdkConfig>): Promise<any | any> {
    const resolvedConfig = this.getResolvedConfig(this.listReceiptsConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('GET')
      .setPath('/expenses/{expenseId}/receipts')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addPathParam({
        key: 'expenseId',
        value: expenseId,
      })
      .build();
    return this.client.callDirect<any | any>(request);
  }

  /**
 * Attaches a receipt image to an expense (BR-04).
**Business rules:**
- Accepted formats: JPEG, PNG, PDF (NFR-07)
- Maximum file size: 10 MB per receipt (NFR-07)
- Maximum 10 receipts per expense (NFR-07)
- A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19)
- Client Entertainment always requires a receipt (BR-20)
- Only the expense owner may attach receipts (BR-05)
 * @param {string} expenseId - The unique identifier of the expense.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 201 Created - Receipt uploaded
 */
  async uploadReceipt(
    expenseId: string,
    body: UploadReceiptRequest,
    filename?: string,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.uploadReceiptConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/expenses/{expenseId}/receipts')
      .setRequestSchema(uploadReceiptRequestRequest)
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.MultipartFormData)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 201,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 415,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 422,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 422,
      })
      .addPathParam({
        key: 'expenseId',
        value: expenseId,
      })
      .setFilename(filename)
      .addBody(body)
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Removes a receipt attachment from an expense (BR-04).
**Business rules:**
- Only the expense owner may remove receipts (BR-05)
- Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)
 * @param {string} expenseId - The unique identifier of the expense.
 * @param {string} receiptId - The unique identifier of the receipt to delete.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 204 No Content - Deleted
 */
  async deleteReceipt(
    expenseId: string,
    receiptId: string,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.deleteReceiptConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('DELETE')
      .setPath('/expenses/{expenseId}/receipts/{receiptId}')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 204,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 404,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 409,
      })
      .addPathParam({
        key: 'expenseId',
        value: expenseId,
      })
      .addPathParam({
        key: 'receiptId',
        value: receiptId,
      })
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Returns a paginated list of claims.
- Employees see only their own claims (BR-10)
- Approvers see claims submitted to them (BR-11)
- Finance sees all claims (BR-14)

Supports filtering by status. Results are paginated (NFR-05).
 * @param {string} [params.status] - Filter by claim status: draft | submitted | approved | rejected | reimbursed
 * @param {string} [params.page] - Page number (1-based).
 * @param {string} [params.pageSize] - Number of results per page. Max 100.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Claims list
 */
  async listClaims(
    params?: ListClaimsParams,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any | any> {
    const resolvedConfig = this.getResolvedConfig(this.listClaimsConfig, requestConfig);
    z.object({
      status: z.string().optional(),
      page: z.string().optional(),
      pageSize: z.string().optional(),
    }).parse(params ?? {});
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('GET')
      .setPath('/claims')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addQueryParam({
        key: 'status',
        value: params?.status,
      })
      .addQueryParam({
        key: 'page',
        value: params?.page,
      })
      .addQueryParam({
        key: 'pageSize',
        value: params?.pageSize,
      })
      .build();
    return this.client.callDirect<any | any>(request);
  }

  /**
 * Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09).
**Business rules:**
- A claim must contain at least one expense (BR-21)
- Each expense may belong to at most one claim (BR-22)
- Expenses incurred more than 90 days ago cannot be added to a claim (BR-18)
- All expenses must belong to the authenticated employee
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 201 Created
 */
  async createClaim(body: CreateClaimRequest, requestConfig?: Partial<SdkConfig>): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.createClaimConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/claims')
      .setRequestSchema(createClaimRequestRequest)
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 201,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 422,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 422,
      })
      .addHeaderParam({ key: 'Content-Type', value: 'application/json' })
      .addBody(body)
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Returns a single claim by ID, including all its expenses and receipts (BR-11).
- Employees can only see their own claims (BR-10)
- Approvers can see claims submitted to them (BR-11)
- Finance can see all claims (BR-14)
 * @param {string} claimId - The unique identifier of the claim.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Draft claim
 */
  async getClaim(claimId: string, requestConfig?: Partial<SdkConfig>): Promise<any | any> {
    const resolvedConfig = this.getResolvedConfig(this.getClaimConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('GET')
      .setPath('/claims/{claimId}')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addPathParam({
        key: 'claimId',
        value: claimId,
      })
      .build();
    return this.client.callDirect<any | any>(request);
  }

  /**
 * Updates a claim's title or expense list while it is in draft status.Only the claim owner may update it.

**Business rules:**
- Can only update a claim in draft status
- Expenses added must be unclaimed and belong to the owner (BR-22)
- Expenses incurred more than 90 days ago cannot be added (BR-18)
- A claim must retain at least one expense (BR-21)
 * @param {string} claimId - The unique identifier of the claim to update.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Updated
 */
  async updateClaim(
    claimId: string,
    body: CreateClaimRequest,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.updateClaimConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('PATCH')
      .setPath('/claims/{claimId}')
      .setRequestSchema(createClaimRequestRequest)
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 409,
      })
      .addPathParam({
        key: 'claimId',
        value: claimId,
      })
      .addHeaderParam({ key: 'Content-Type', value: 'application/json' })
      .addBody(body)
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Deletes a claim in draft status. Only the claim owner may delete it.
**Business rules:**
- Only draft claims can be deleted
- Reimbursed claims are final and cannot be deleted (BR-27)
- Deleting a claim releases its expenses back to unclaimed status
 * @param {string} claimId - The unique identifier of the claim to delete.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<string>>} - 204 No Content - Deleted
 */
  async deleteClaim(claimId: string, requestConfig?: Partial<SdkConfig>): Promise<string> {
    const resolvedConfig = this.getResolvedConfig(this.deleteClaimConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('DELETE')
      .setPath('/claims/{claimId}')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.string(),
        contentType: ContentType.Json,
        status: 204,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 409,
      })
      .addPathParam({
        key: 'claimId',
        value: claimId,
      })
      .build();
    return this.client.callDirect<string>(request);
  }

  /**
 * Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23).
**Business rules enforced on submission:**
- Claim must be in draft status (BR-23)
- Claim must contain at least one expense (BR-21)
- Any expense requiring a receipt (amount > category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20)
- No expense in the claim may be older than 90 days (BR-18)
- Status change is recorded with actor and timestamp (BR-29)
 * @param {string} claimId - The unique identifier of the claim to submit.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Claim submitted
 */
  async submitClaim(claimId: string, requestConfig?: Partial<SdkConfig>): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.submitClaimConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/claims/{claimId}/submit')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 409,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 422,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 422,
      })
      .addPathParam({
        key: 'claimId',
        value: claimId,
      })
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23).**Approver role required.**

**Business rules:**
- Claim must be in submitted status (BR-23)
- An approver cannot approve a claim they submitted themselves (BR-26)
- Status change is recorded with actor and timestamp (BR-29)
 * @param {string} claimId - The unique identifier of the claim to approve.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Claim approved
 */
  async approveClaim(claimId: string, requestConfig?: Partial<SdkConfig>): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.approveClaimConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/claims/{claimId}/approve')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 403,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 409,
      })
      .addPathParam({
        key: 'claimId',
        value: claimId,
      })
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24).**Approver role required.**

**Business rules:**
- Claim must be in submitted status (BR-23)
- A rejection reason is required (BR-24)
- The rejected claim returns to draft so the employee can correct and resubmit (BR-24)
- The rejection reason is preserved on the claim (BR-24)
- Status change is recorded with actor and timestamp (BR-29)
 * @param {string} claimId - The unique identifier of the claim to reject.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Claim rejected
 */
  async rejectClaim(
    claimId: string,
    body: RejectClaimRequest,
    requestConfig?: Partial<SdkConfig>,
  ): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.rejectClaimConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/claims/{claimId}/reject')
      .setRequestSchema(rejectClaimRequestRequest)
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 422,
      })
      .addPathParam({
        key: 'claimId',
        value: claimId,
      })
      .addHeaderParam({ key: 'Content-Type', value: 'application/json' })
      .addBody(body)
      .build();
    return this.client.callDirect<any>(request);
  }

  /**
 * Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23).**Finance role required.**

**Business rules:**
- Claim must be in approved status (BR-23)
- A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27)
- Status change is recorded with actor and timestamp (BR-29)
- Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred
 * @param {string} claimId - The unique identifier of the claim to mark as reimbursed.
 * @param {Partial<SdkConfig>} [requestConfig] - The request configuration for retry and validation.
 * @returns {Promise<HttpResponse<any>>} - 200 OK - Claim reimbursed
 */
  async reimburseClaim(claimId: string, requestConfig?: Partial<SdkConfig>): Promise<any> {
    const resolvedConfig = this.getResolvedConfig(this.reimburseClaimConfig, requestConfig);
    const request = new RequestBuilder()
      .setConfig(resolvedConfig)
      .setBaseUrl(resolvedConfig)
      .setMethod('POST')
      .setPath('/claims/{claimId}/reimburse')
      .setRequestSchema(z.any())
      .addAccessTokenAuth(resolvedConfig?.token, 'Bearer')
      .setRequestContentType(ContentType.Json)
      .addResponse({
        schema: z.any(),
        contentType: ContentType.Json,
        status: 200,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 403,
      })
      .addError({
        error: ThrowableError,
        contentType: ContentType.Json,
        status: 409,
      })
      .addPathParam({
        key: 'claimId',
        value: claimId,
      })
      .build();
    return this.client.callDirect<any>(request);
  }
}

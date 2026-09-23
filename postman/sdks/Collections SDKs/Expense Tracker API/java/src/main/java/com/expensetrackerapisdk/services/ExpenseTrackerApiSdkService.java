package com.expensetrackerapisdk.services;

import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.config.RequestConfig;
import com.expensetrackerapisdk.exceptions.ApiError;
import com.expensetrackerapisdk.http.Environment;
import com.expensetrackerapisdk.http.ExpenseTrackerApiSdkResponse;
import com.expensetrackerapisdk.http.HttpMethod;
import com.expensetrackerapisdk.http.ModelConverter;
import com.expensetrackerapisdk.http.util.RequestBuilder;
import com.expensetrackerapisdk.models.CreateCategoryRequest;
import com.expensetrackerapisdk.models.CreateClaimRequest;
import com.expensetrackerapisdk.models.CreateExpenseRequest;
import com.expensetrackerapisdk.models.ListCategoriesParameters;
import com.expensetrackerapisdk.models.ListClaimsParameters;
import com.expensetrackerapisdk.models.ListExpensesParameters;
import com.expensetrackerapisdk.models.RejectClaimRequest;
import com.expensetrackerapisdk.models.UpdateCategoryRequest;
import com.expensetrackerapisdk.models.UpdateExpenseRequest;
import com.expensetrackerapisdk.models.UploadReceiptRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * ExpenseTrackerApiSdkService Service
 */
public class ExpenseTrackerApiSdkService extends BaseService {

  private RequestConfig listCategoriesConfig;
  private RequestConfig getCategoryConfig;
  private RequestConfig createCategoryConfig;
  private RequestConfig updateCategoryConfig;
  private RequestConfig listExpensesConfig;
  private RequestConfig createExpenseConfig;
  private RequestConfig getExpenseConfig;
  private RequestConfig updateExpenseConfig;
  private RequestConfig deleteExpenseConfig;
  private RequestConfig listReceiptsConfig;
  private RequestConfig uploadReceiptConfig;
  private RequestConfig deleteReceiptConfig;
  private RequestConfig listClaimsConfig;
  private RequestConfig createClaimConfig;
  private RequestConfig getClaimConfig;
  private RequestConfig updateClaimConfig;
  private RequestConfig deleteClaimConfig;
  private RequestConfig submitClaimConfig;
  private RequestConfig approveClaimConfig;
  private RequestConfig rejectClaimConfig;
  private RequestConfig reimburseClaimConfig;

  /**
   * Constructs a new instance of ExpenseTrackerApiSdkService.
   *
   * @param httpClient The HTTP client to use for requests
   * @param config The SDK configuration
   */
  public ExpenseTrackerApiSdkService(
    @NonNull OkHttpClient httpClient,
    ExpenseTrackerApiSdkConfig config
  ) {
    super(httpClient, config);
  }

  /**
   * Sets method-level configuration for {@code listCategories}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setListCategoriesConfig(RequestConfig config) {
    this.listCategoriesConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code getCategory}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setGetCategoryConfig(RequestConfig config) {
    this.getCategoryConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code createCategory}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setCreateCategoryConfig(RequestConfig config) {
    this.createCategoryConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code updateCategory}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setUpdateCategoryConfig(RequestConfig config) {
    this.updateCategoryConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code listExpenses}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setListExpensesConfig(RequestConfig config) {
    this.listExpensesConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code createExpense}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setCreateExpenseConfig(RequestConfig config) {
    this.createExpenseConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code getExpense}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setGetExpenseConfig(RequestConfig config) {
    this.getExpenseConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code updateExpense}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setUpdateExpenseConfig(RequestConfig config) {
    this.updateExpenseConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code deleteExpense}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setDeleteExpenseConfig(RequestConfig config) {
    this.deleteExpenseConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code listReceipts}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setListReceiptsConfig(RequestConfig config) {
    this.listReceiptsConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code uploadReceipt}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setUploadReceiptConfig(RequestConfig config) {
    this.uploadReceiptConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code deleteReceipt}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setDeleteReceiptConfig(RequestConfig config) {
    this.deleteReceiptConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code listClaims}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setListClaimsConfig(RequestConfig config) {
    this.listClaimsConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code createClaim}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setCreateClaimConfig(RequestConfig config) {
    this.createClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code getClaim}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setGetClaimConfig(RequestConfig config) {
    this.getClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code updateClaim}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setUpdateClaimConfig(RequestConfig config) {
    this.updateClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code deleteClaim}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setDeleteClaimConfig(RequestConfig config) {
    this.deleteClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code submitClaim}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setSubmitClaimConfig(RequestConfig config) {
    this.submitClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code approveClaim}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setApproveClaimConfig(RequestConfig config) {
    this.approveClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code rejectClaim}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setRejectClaimConfig(RequestConfig config) {
    this.rejectClaimConfig = config;
    return this;
  }

  /**
   * Sets method-level configuration for {@code reimburseClaim}.
   * Method-level overrides take precedence over service-level configuration but are
   * overridden by request-level configurations.
   *
   * @param config The configuration overrides to apply at the method level
   * @return This service instance for method chaining
   */
  public ExpenseTrackerApiSdkService setReimburseClaimConfig(RequestConfig config) {
    this.reimburseClaimConfig = config;
    return this;
  }

  /**
   * Returns all active categories available for use on new expenses.
   * Finance may also see retired categories by passing `includeRetired=true`.
   *
   * **BR-06** – Any authenticated user can retrieve the category list.
   * **BR-08** – Read-only for employees and approvers.
   *
   * @return response of {@code Object}
   */
  public Object listCategories() throws ApiError {
    return this.listCategories(ListCategoriesParameters.builder().build());
  }

  /**
   * Returns all active categories available for use on new expenses.
   * Finance may also see retired categories by passing `includeRetired=true`.
   *
   * **BR-06** – Any authenticated user can retrieve the category list.
   * **BR-08** – Read-only for employees and approvers.
   *
   * @param requestParameters {@link ListCategoriesParameters} Request Parameters Object
   * @return response of {@code Object}
   */
  public Object listCategories(@NonNull ListCategoriesParameters requestParameters)
    throws ApiError {
    return this.listCategories(requestParameters, null);
  }

  /**
   * Returns all active categories available for use on new expenses.
   * Finance may also see retired categories by passing `includeRetired=true`.
   *
   * **BR-06** – Any authenticated user can retrieve the category list.
   * **BR-08** – Read-only for employees and approvers.
   *
   * @param requestParameters {@link ListCategoriesParameters} Request Parameters Object
   * @return response of {@code Object}
   */
  public Object listCategories(
    @NonNull ListCategoriesParameters requestParameters,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().listCategories(requestParameters, requestConfig).getData();
  }

  /**
   * Returns all active categories available for use on new expenses.
   * Finance may also see retired categories by passing `includeRetired=true`.
   *
   * **BR-06** – Any authenticated user can retrieve the category list.
   * **BR-08** – Read-only for employees and approvers.
   *
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listCategoriesAsync() throws ApiError {
    return this.listCategoriesAsync(ListCategoriesParameters.builder().build());
  }

  /**
   * Returns all active categories available for use on new expenses.
   * Finance may also see retired categories by passing `includeRetired=true`.
   *
   * **BR-06** – Any authenticated user can retrieve the category list.
   * **BR-08** – Read-only for employees and approvers.
   *
   * @param requestParameters {@link ListCategoriesParameters} Request Parameters Object
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listCategoriesAsync(
    @NonNull ListCategoriesParameters requestParameters
  ) throws ApiError {
    return this.listCategoriesAsync(requestParameters, null);
  }

  /**
   * Returns all active categories available for use on new expenses.
   * Finance may also see retired categories by passing `includeRetired=true`.
   *
   * **BR-06** – Any authenticated user can retrieve the category list.
   * **BR-08** – Read-only for employees and approvers.
   *
   * @param requestParameters {@link ListCategoriesParameters} Request Parameters Object
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listCategoriesAsync(
    @NonNull ListCategoriesParameters requestParameters,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .listCategoriesAsync(requestParameters, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildListCategoriesRequest(
    @NonNull ListCategoriesParameters requestParameters,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "categories"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setOptionalQueryParameter("includeRetired", requestParameters.getIncludeRetired())
      .build();
  }

  /**
   * Returns a single category by its ID, including whether it is active or retired.
   *
   * @param categoryId String The unique identifier of the category.
   * @return response of {@code Object}
   */
  public Object getCategory(@NonNull String categoryId) throws ApiError {
    return this.getCategory(categoryId, null);
  }

  /**
   * Returns a single category by its ID, including whether it is active or retired.
   *
   * @param categoryId String The unique identifier of the category.
   * @return response of {@code Object}
   */
  public Object getCategory(@NonNull String categoryId, RequestConfig requestConfig)
    throws ApiError {
    return withRawResponse().getCategory(categoryId, requestConfig).getData();
  }

  /**
   * Returns a single category by its ID, including whether it is active or retired.
   *
   * @param categoryId String The unique identifier of the category.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> getCategoryAsync(@NonNull String categoryId) throws ApiError {
    return this.getCategoryAsync(categoryId, null);
  }

  /**
   * Returns a single category by its ID, including whether it is active or retired.
   *
   * @param categoryId String The unique identifier of the category.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> getCategoryAsync(
    @NonNull String categoryId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .getCategoryAsync(categoryId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildGetCategoryRequest(
    @NonNull String categoryId,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "categories/{categoryId}"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("categoryId", categoryId)
      .build();
  }

  /**
   * Creates a new expense category. **Finance role required** (BR-07, BR-08).
   *
   * The `receiptThreshold` is the amount above which a receipt becomes mandatory.
   * Set `requiresReceiptAlways: true` to mandate a receipt at any amount.
   * Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).
   *
   * @param createCategoryRequest {@link CreateCategoryRequest} Request Body
   * @return response of {@code Object}
   */
  public Object createCategory(@NonNull CreateCategoryRequest createCategoryRequest)
    throws ApiError {
    return this.createCategory(createCategoryRequest, null);
  }

  /**
   * Creates a new expense category. **Finance role required** (BR-07, BR-08).
   *
   * The `receiptThreshold` is the amount above which a receipt becomes mandatory.
   * Set `requiresReceiptAlways: true` to mandate a receipt at any amount.
   * Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).
   *
   * @param createCategoryRequest {@link CreateCategoryRequest} Request Body
   * @return response of {@code Object}
   */
  public Object createCategory(
    @NonNull CreateCategoryRequest createCategoryRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().createCategory(createCategoryRequest, requestConfig).getData();
  }

  /**
   * Creates a new expense category. **Finance role required** (BR-07, BR-08).
   *
   * The `receiptThreshold` is the amount above which a receipt becomes mandatory.
   * Set `requiresReceiptAlways: true` to mandate a receipt at any amount.
   * Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).
   *
   * @param createCategoryRequest {@link CreateCategoryRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> createCategoryAsync(
    @NonNull CreateCategoryRequest createCategoryRequest
  ) throws ApiError {
    return this.createCategoryAsync(createCategoryRequest, null);
  }

  /**
   * Creates a new expense category. **Finance role required** (BR-07, BR-08).
   *
   * The `receiptThreshold` is the amount above which a receipt becomes mandatory.
   * Set `requiresReceiptAlways: true` to mandate a receipt at any amount.
   * Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).
   *
   * @param createCategoryRequest {@link CreateCategoryRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> createCategoryAsync(
    @NonNull CreateCategoryRequest createCategoryRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .createCategoryAsync(createCategoryRequest, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildCreateCategoryRequest(
    @NonNull CreateCategoryRequest createCategoryRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "categories"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setJsonContent(createCategoryRequest)
      .build();
  }

  /**
   * Updates an existing category's name, receipt threshold, or client reference requirement.
   * **Finance role required** (BR-07, BR-08).
   *
   * Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).
   *
   * @param categoryId String The unique identifier of the category to update.
   * @param updateCategoryRequest {@link UpdateCategoryRequest} Request Body
   * @return response of {@code Object}
   */
  public Object updateCategory(
    @NonNull String categoryId,
    @NonNull UpdateCategoryRequest updateCategoryRequest
  ) throws ApiError {
    return this.updateCategory(categoryId, updateCategoryRequest, null);
  }

  /**
   * Updates an existing category's name, receipt threshold, or client reference requirement.
   * **Finance role required** (BR-07, BR-08).
   *
   * Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).
   *
   * @param categoryId String The unique identifier of the category to update.
   * @param updateCategoryRequest {@link UpdateCategoryRequest} Request Body
   * @return response of {@code Object}
   */
  public Object updateCategory(
    @NonNull String categoryId,
    @NonNull UpdateCategoryRequest updateCategoryRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .updateCategory(categoryId, updateCategoryRequest, requestConfig)
      .getData();
  }

  /**
   * Updates an existing category's name, receipt threshold, or client reference requirement.
   * **Finance role required** (BR-07, BR-08).
   *
   * Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).
   *
   * @param categoryId String The unique identifier of the category to update.
   * @param updateCategoryRequest {@link UpdateCategoryRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> updateCategoryAsync(
    @NonNull String categoryId,
    @NonNull UpdateCategoryRequest updateCategoryRequest
  ) throws ApiError {
    return this.updateCategoryAsync(categoryId, updateCategoryRequest, null);
  }

  /**
   * Updates an existing category's name, receipt threshold, or client reference requirement.
   * **Finance role required** (BR-07, BR-08).
   *
   * Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).
   *
   * @param categoryId String The unique identifier of the category to update.
   * @param updateCategoryRequest {@link UpdateCategoryRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> updateCategoryAsync(
    @NonNull String categoryId,
    @NonNull UpdateCategoryRequest updateCategoryRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .updateCategoryAsync(categoryId, updateCategoryRequest, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildUpdateCategoryRequest(
    @NonNull String categoryId,
    @NonNull UpdateCategoryRequest updateCategoryRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.PATCH,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "categories/{categoryId}"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("categoryId", categoryId)
      .setJsonContent(updateCategoryRequest)
      .build();
  }

  /**
   * Returns a paginated list of expenses for the authenticated employee.
   * Finance may retrieve expenses across all employees for reporting (BR-14).
   *
   * Supports filtering by date range, category, and claimed status (BR-03).
   * Results are paginated (NFR-05).
   *
   * @return response of {@code Object}
   */
  public Object listExpenses() throws ApiError {
    return this.listExpenses(ListExpensesParameters.builder().build());
  }

  /**
   * Returns a paginated list of expenses for the authenticated employee.
   * Finance may retrieve expenses across all employees for reporting (BR-14).
   *
   * Supports filtering by date range, category, and claimed status (BR-03).
   * Results are paginated (NFR-05).
   *
   * @param requestParameters {@link ListExpensesParameters} Request Parameters Object
   * @return response of {@code Object}
   */
  public Object listExpenses(@NonNull ListExpensesParameters requestParameters) throws ApiError {
    return this.listExpenses(requestParameters, null);
  }

  /**
   * Returns a paginated list of expenses for the authenticated employee.
   * Finance may retrieve expenses across all employees for reporting (BR-14).
   *
   * Supports filtering by date range, category, and claimed status (BR-03).
   * Results are paginated (NFR-05).
   *
   * @param requestParameters {@link ListExpensesParameters} Request Parameters Object
   * @return response of {@code Object}
   */
  public Object listExpenses(
    @NonNull ListExpensesParameters requestParameters,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().listExpenses(requestParameters, requestConfig).getData();
  }

  /**
   * Returns a paginated list of expenses for the authenticated employee.
   * Finance may retrieve expenses across all employees for reporting (BR-14).
   *
   * Supports filtering by date range, category, and claimed status (BR-03).
   * Results are paginated (NFR-05).
   *
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listExpensesAsync() throws ApiError {
    return this.listExpensesAsync(ListExpensesParameters.builder().build());
  }

  /**
   * Returns a paginated list of expenses for the authenticated employee.
   * Finance may retrieve expenses across all employees for reporting (BR-14).
   *
   * Supports filtering by date range, category, and claimed status (BR-03).
   * Results are paginated (NFR-05).
   *
   * @param requestParameters {@link ListExpensesParameters} Request Parameters Object
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listExpensesAsync(
    @NonNull ListExpensesParameters requestParameters
  ) throws ApiError {
    return this.listExpensesAsync(requestParameters, null);
  }

  /**
   * Returns a paginated list of expenses for the authenticated employee.
   * Finance may retrieve expenses across all employees for reporting (BR-14).
   *
   * Supports filtering by date range, category, and claimed status (BR-03).
   * Results are paginated (NFR-05).
   *
   * @param requestParameters {@link ListExpensesParameters} Request Parameters Object
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listExpensesAsync(
    @NonNull ListExpensesParameters requestParameters,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .listExpensesAsync(requestParameters, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildListExpensesRequest(
    @NonNull ListExpensesParameters requestParameters,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "expenses"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setOptionalQueryParameter("dateFrom", requestParameters.getDateFrom())
      .setOptionalQueryParameter("dateTo", requestParameters.getDateTo())
      .setOptionalQueryParameter("categoryId", requestParameters.getCategoryId())
      .setOptionalQueryParameter("claimed", requestParameters.getClaimed())
      .setOptionalQueryParameter("page", requestParameters.getPage())
      .setOptionalQueryParameter("pageSize", requestParameters.getPageSize())
      .build();
  }

  /**
   * Records a new expense for the authenticated employee (BR-01).
   *
   * **Business rules:**
   * - Amount must be greater than zero (BR-16)
   * - Date incurred must not be in the future (BR-17)
   * - Category must be an active category (BR-06)
   * - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28)
   * - Client Entertainment requires a client reference (BR-20)
   *
   * @param createExpenseRequest {@link CreateExpenseRequest} Request Body
   * @return response of {@code Object}
   */
  public Object createExpense(@NonNull CreateExpenseRequest createExpenseRequest) throws ApiError {
    return this.createExpense(createExpenseRequest, null);
  }

  /**
   * Records a new expense for the authenticated employee (BR-01).
   *
   * **Business rules:**
   * - Amount must be greater than zero (BR-16)
   * - Date incurred must not be in the future (BR-17)
   * - Category must be an active category (BR-06)
   * - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28)
   * - Client Entertainment requires a client reference (BR-20)
   *
   * @param createExpenseRequest {@link CreateExpenseRequest} Request Body
   * @return response of {@code Object}
   */
  public Object createExpense(
    @NonNull CreateExpenseRequest createExpenseRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().createExpense(createExpenseRequest, requestConfig).getData();
  }

  /**
   * Records a new expense for the authenticated employee (BR-01).
   *
   * **Business rules:**
   * - Amount must be greater than zero (BR-16)
   * - Date incurred must not be in the future (BR-17)
   * - Category must be an active category (BR-06)
   * - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28)
   * - Client Entertainment requires a client reference (BR-20)
   *
   * @param createExpenseRequest {@link CreateExpenseRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> createExpenseAsync(
    @NonNull CreateExpenseRequest createExpenseRequest
  ) throws ApiError {
    return this.createExpenseAsync(createExpenseRequest, null);
  }

  /**
   * Records a new expense for the authenticated employee (BR-01).
   *
   * **Business rules:**
   * - Amount must be greater than zero (BR-16)
   * - Date incurred must not be in the future (BR-17)
   * - Category must be an active category (BR-06)
   * - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28)
   * - Client Entertainment requires a client reference (BR-20)
   *
   * @param createExpenseRequest {@link CreateExpenseRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> createExpenseAsync(
    @NonNull CreateExpenseRequest createExpenseRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .createExpenseAsync(createExpenseRequest, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildCreateExpenseRequest(
    @NonNull CreateExpenseRequest createExpenseRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "expenses"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setJsonContent(createExpenseRequest)
      .build();
  }

  /**
   * Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05).
   * Finance can retrieve any expense.
   *
   * @param expenseId String The unique identifier of the expense.
   * @return response of {@code Object}
   */
  public Object getExpense(@NonNull String expenseId) throws ApiError {
    return this.getExpense(expenseId, null);
  }

  /**
   * Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05).
   * Finance can retrieve any expense.
   *
   * @param expenseId String The unique identifier of the expense.
   * @return response of {@code Object}
   */
  public Object getExpense(@NonNull String expenseId, RequestConfig requestConfig) throws ApiError {
    return withRawResponse().getExpense(expenseId, requestConfig).getData();
  }

  /**
   * Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05).
   * Finance can retrieve any expense.
   *
   * @param expenseId String The unique identifier of the expense.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> getExpenseAsync(@NonNull String expenseId) throws ApiError {
    return this.getExpenseAsync(expenseId, null);
  }

  /**
   * Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05).
   * Finance can retrieve any expense.
   *
   * @param expenseId String The unique identifier of the expense.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> getExpenseAsync(
    @NonNull String expenseId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .getExpenseAsync(expenseId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildGetExpenseRequest(@NonNull String expenseId, RequestConfig resolvedConfig) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "expenses/{expenseId}"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("expenseId", expenseId)
      .build();
  }

  /**
   * Amends an existing expense. Only the owner may update it (BR-02, BR-05).
   *
   * **Business rules:**
   * - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
   * - Amount must remain greater than zero (BR-16)
   * - Date incurred must not be in the future (BR-17)
   *
   * @param expenseId String The unique identifier of the expense to update.
   * @param updateExpenseRequest {@link UpdateExpenseRequest} Request Body
   * @return response of {@code Object}
   */
  public Object updateExpense(
    @NonNull String expenseId,
    @NonNull UpdateExpenseRequest updateExpenseRequest
  ) throws ApiError {
    return this.updateExpense(expenseId, updateExpenseRequest, null);
  }

  /**
   * Amends an existing expense. Only the owner may update it (BR-02, BR-05).
   *
   * **Business rules:**
   * - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
   * - Amount must remain greater than zero (BR-16)
   * - Date incurred must not be in the future (BR-17)
   *
   * @param expenseId String The unique identifier of the expense to update.
   * @param updateExpenseRequest {@link UpdateExpenseRequest} Request Body
   * @return response of {@code Object}
   */
  public Object updateExpense(
    @NonNull String expenseId,
    @NonNull UpdateExpenseRequest updateExpenseRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .updateExpense(expenseId, updateExpenseRequest, requestConfig)
      .getData();
  }

  /**
   * Amends an existing expense. Only the owner may update it (BR-02, BR-05).
   *
   * **Business rules:**
   * - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
   * - Amount must remain greater than zero (BR-16)
   * - Date incurred must not be in the future (BR-17)
   *
   * @param expenseId String The unique identifier of the expense to update.
   * @param updateExpenseRequest {@link UpdateExpenseRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> updateExpenseAsync(
    @NonNull String expenseId,
    @NonNull UpdateExpenseRequest updateExpenseRequest
  ) throws ApiError {
    return this.updateExpenseAsync(expenseId, updateExpenseRequest, null);
  }

  /**
   * Amends an existing expense. Only the owner may update it (BR-02, BR-05).
   *
   * **Business rules:**
   * - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
   * - Amount must remain greater than zero (BR-16)
   * - Date incurred must not be in the future (BR-17)
   *
   * @param expenseId String The unique identifier of the expense to update.
   * @param updateExpenseRequest {@link UpdateExpenseRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> updateExpenseAsync(
    @NonNull String expenseId,
    @NonNull UpdateExpenseRequest updateExpenseRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .updateExpenseAsync(expenseId, updateExpenseRequest, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildUpdateExpenseRequest(
    @NonNull String expenseId,
    @NonNull UpdateExpenseRequest updateExpenseRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.PATCH,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "expenses/{expenseId}"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("expenseId", expenseId)
      .setJsonContent(updateExpenseRequest)
      .build();
  }

  /**
   * Deletes an expense. Only the owner may delete it (BR-02, BR-05).
   *
   * **Business rules:**
   * - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
   *
   * @param expenseId String The unique identifier of the expense to delete.
   * @return response of {@code String}
   */
  public String deleteExpense(@NonNull String expenseId) throws ApiError {
    return this.deleteExpense(expenseId, null);
  }

  /**
   * Deletes an expense. Only the owner may delete it (BR-02, BR-05).
   *
   * **Business rules:**
   * - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
   *
   * @param expenseId String The unique identifier of the expense to delete.
   * @return response of {@code String}
   */
  public String deleteExpense(@NonNull String expenseId, RequestConfig requestConfig)
    throws ApiError {
    return withRawResponse().deleteExpense(expenseId, requestConfig).getData();
  }

  /**
   * Deletes an expense. Only the owner may delete it (BR-02, BR-05).
   *
   * **Business rules:**
   * - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
   *
   * @param expenseId String The unique identifier of the expense to delete.
   * @return response of {@code CompletableFuture<String>}
   */
  public CompletableFuture<String> deleteExpenseAsync(@NonNull String expenseId) throws ApiError {
    return this.deleteExpenseAsync(expenseId, null);
  }

  /**
   * Deletes an expense. Only the owner may delete it (BR-02, BR-05).
   *
   * **Business rules:**
   * - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
   *
   * @param expenseId String The unique identifier of the expense to delete.
   * @return response of {@code CompletableFuture<String>}
   */
  public CompletableFuture<String> deleteExpenseAsync(
    @NonNull String expenseId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .deleteExpenseAsync(expenseId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildDeleteExpenseRequest(
    @NonNull String expenseId,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.DELETE,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "expenses/{expenseId}"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("expenseId", expenseId)
      .build();
  }

  /**
   * Returns all receipt attachments for a given expense.
   * Only the expense owner (or Finance) may access receipts (BR-05).
   *
   * **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.
   *
   * @param expenseId String The unique identifier of the expense.
   * @return response of {@code Object}
   */
  public Object listReceipts(@NonNull String expenseId) throws ApiError {
    return this.listReceipts(expenseId, null);
  }

  /**
   * Returns all receipt attachments for a given expense.
   * Only the expense owner (or Finance) may access receipts (BR-05).
   *
   * **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.
   *
   * @param expenseId String The unique identifier of the expense.
   * @return response of {@code Object}
   */
  public Object listReceipts(@NonNull String expenseId, RequestConfig requestConfig)
    throws ApiError {
    return withRawResponse().listReceipts(expenseId, requestConfig).getData();
  }

  /**
   * Returns all receipt attachments for a given expense.
   * Only the expense owner (or Finance) may access receipts (BR-05).
   *
   * **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.
   *
   * @param expenseId String The unique identifier of the expense.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listReceiptsAsync(@NonNull String expenseId) throws ApiError {
    return this.listReceiptsAsync(expenseId, null);
  }

  /**
   * Returns all receipt attachments for a given expense.
   * Only the expense owner (or Finance) may access receipts (BR-05).
   *
   * **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.
   *
   * @param expenseId String The unique identifier of the expense.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listReceiptsAsync(
    @NonNull String expenseId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .listReceiptsAsync(expenseId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildListReceiptsRequest(
    @NonNull String expenseId,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "expenses/{expenseId}/receipts"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("expenseId", expenseId)
      .build();
  }

  /**
   * Attaches a receipt image to an expense (BR-04).
   *
   * **Business rules:**
   * - Accepted formats: JPEG, PNG, PDF (NFR-07)
   * - Maximum file size: 10 MB per receipt (NFR-07)
   * - Maximum 10 receipts per expense (NFR-07)
   * - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19)
   * - Client Entertainment always requires a receipt (BR-20)
   * - Only the expense owner may attach receipts (BR-05)
   *
   * @param expenseId String The unique identifier of the expense.
   * @param uploadReceiptRequest {@link UploadReceiptRequest} Request Body
   * @param _filename String Filename for the uploaded file
   * @return response of {@code Object}
   */
  public Object uploadReceipt(
    @NonNull String expenseId,
    @NonNull UploadReceiptRequest uploadReceiptRequest,
    @NonNull String _filename
  ) throws ApiError {
    return this.uploadReceipt(expenseId, uploadReceiptRequest, _filename, null);
  }

  /**
   * Attaches a receipt image to an expense (BR-04).
   *
   * **Business rules:**
   * - Accepted formats: JPEG, PNG, PDF (NFR-07)
   * - Maximum file size: 10 MB per receipt (NFR-07)
   * - Maximum 10 receipts per expense (NFR-07)
   * - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19)
   * - Client Entertainment always requires a receipt (BR-20)
   * - Only the expense owner may attach receipts (BR-05)
   *
   * @param expenseId String The unique identifier of the expense.
   * @param uploadReceiptRequest {@link UploadReceiptRequest} Request Body
   * @param _filename String Filename for the uploaded file
   * @return response of {@code Object}
   */
  public Object uploadReceipt(
    @NonNull String expenseId,
    @NonNull UploadReceiptRequest uploadReceiptRequest,
    @NonNull String _filename,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .uploadReceipt(expenseId, uploadReceiptRequest, _filename, requestConfig)
      .getData();
  }

  /**
   * Attaches a receipt image to an expense (BR-04).
   *
   * **Business rules:**
   * - Accepted formats: JPEG, PNG, PDF (NFR-07)
   * - Maximum file size: 10 MB per receipt (NFR-07)
   * - Maximum 10 receipts per expense (NFR-07)
   * - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19)
   * - Client Entertainment always requires a receipt (BR-20)
   * - Only the expense owner may attach receipts (BR-05)
   *
   * @param expenseId String The unique identifier of the expense.
   * @param uploadReceiptRequest {@link UploadReceiptRequest} Request Body
   * @param _filename String Filename for the uploaded file
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> uploadReceiptAsync(
    @NonNull String expenseId,
    @NonNull UploadReceiptRequest uploadReceiptRequest,
    @NonNull String _filename
  ) throws ApiError {
    return this.uploadReceiptAsync(expenseId, uploadReceiptRequest, _filename, null);
  }

  /**
   * Attaches a receipt image to an expense (BR-04).
   *
   * **Business rules:**
   * - Accepted formats: JPEG, PNG, PDF (NFR-07)
   * - Maximum file size: 10 MB per receipt (NFR-07)
   * - Maximum 10 receipts per expense (NFR-07)
   * - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19)
   * - Client Entertainment always requires a receipt (BR-20)
   * - Only the expense owner may attach receipts (BR-05)
   *
   * @param expenseId String The unique identifier of the expense.
   * @param uploadReceiptRequest {@link UploadReceiptRequest} Request Body
   * @param _filename String Filename for the uploaded file
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> uploadReceiptAsync(
    @NonNull String expenseId,
    @NonNull UploadReceiptRequest uploadReceiptRequest,
    @NonNull String _filename,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .uploadReceiptAsync(expenseId, uploadReceiptRequest, _filename, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildUploadReceiptRequest(
    @NonNull String expenseId,
    @NonNull UploadReceiptRequest uploadReceiptRequest,
    @NonNull String _filename,
    RequestConfig resolvedConfig
  ) {
    MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder()
      .setType(MultipartBody.FORM)
      .addFormDataPart("filename", uploadReceiptRequest.getFilename());
    if (uploadReceiptRequest.getFile() != null) {
      multipartBodyBuilder.addFormDataPart(
        "file",
        _filename,
        RequestBody.create(
          uploadReceiptRequest.getFile(),
          MediaType.parse("application/octet-stream")
        )
      );
    }
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "expenses/{expenseId}/receipts"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("expenseId", expenseId)
      .setBody(multipartBodyBuilder.build())
      .build();
  }

  /**
   * Removes a receipt attachment from an expense (BR-04).
   *
   * **Business rules:**
   * - Only the expense owner may remove receipts (BR-05)
   * - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)
   *
   * @param expenseId String The unique identifier of the expense.
   * @param receiptId String The unique identifier of the receipt to delete.
   * @return response of {@code Object}
   */
  public Object deleteReceipt(@NonNull String expenseId, @NonNull String receiptId)
    throws ApiError {
    return this.deleteReceipt(expenseId, receiptId, null);
  }

  /**
   * Removes a receipt attachment from an expense (BR-04).
   *
   * **Business rules:**
   * - Only the expense owner may remove receipts (BR-05)
   * - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)
   *
   * @param expenseId String The unique identifier of the expense.
   * @param receiptId String The unique identifier of the receipt to delete.
   * @return response of {@code Object}
   */
  public Object deleteReceipt(
    @NonNull String expenseId,
    @NonNull String receiptId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().deleteReceipt(expenseId, receiptId, requestConfig).getData();
  }

  /**
   * Removes a receipt attachment from an expense (BR-04).
   *
   * **Business rules:**
   * - Only the expense owner may remove receipts (BR-05)
   * - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)
   *
   * @param expenseId String The unique identifier of the expense.
   * @param receiptId String The unique identifier of the receipt to delete.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> deleteReceiptAsync(
    @NonNull String expenseId,
    @NonNull String receiptId
  ) throws ApiError {
    return this.deleteReceiptAsync(expenseId, receiptId, null);
  }

  /**
   * Removes a receipt attachment from an expense (BR-04).
   *
   * **Business rules:**
   * - Only the expense owner may remove receipts (BR-05)
   * - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)
   *
   * @param expenseId String The unique identifier of the expense.
   * @param receiptId String The unique identifier of the receipt to delete.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> deleteReceiptAsync(
    @NonNull String expenseId,
    @NonNull String receiptId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .deleteReceiptAsync(expenseId, receiptId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildDeleteReceiptRequest(
    @NonNull String expenseId,
    @NonNull String receiptId,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.DELETE,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "expenses/{expenseId}/receipts/{receiptId}"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("expenseId", expenseId)
      .setPathParameter("receiptId", receiptId)
      .build();
  }

  /**
   * Returns a paginated list of claims.
   *
   * - Employees see only their own claims (BR-10)
   * - Approvers see claims submitted to them (BR-11)
   * - Finance sees all claims (BR-14)
   *
   * Supports filtering by status. Results are paginated (NFR-05).
   *
   * @return response of {@code Object}
   */
  public Object listClaims() throws ApiError {
    return this.listClaims(ListClaimsParameters.builder().build());
  }

  /**
   * Returns a paginated list of claims.
   *
   * - Employees see only their own claims (BR-10)
   * - Approvers see claims submitted to them (BR-11)
   * - Finance sees all claims (BR-14)
   *
   * Supports filtering by status. Results are paginated (NFR-05).
   *
   * @param requestParameters {@link ListClaimsParameters} Request Parameters Object
   * @return response of {@code Object}
   */
  public Object listClaims(@NonNull ListClaimsParameters requestParameters) throws ApiError {
    return this.listClaims(requestParameters, null);
  }

  /**
   * Returns a paginated list of claims.
   *
   * - Employees see only their own claims (BR-10)
   * - Approvers see claims submitted to them (BR-11)
   * - Finance sees all claims (BR-14)
   *
   * Supports filtering by status. Results are paginated (NFR-05).
   *
   * @param requestParameters {@link ListClaimsParameters} Request Parameters Object
   * @return response of {@code Object}
   */
  public Object listClaims(
    @NonNull ListClaimsParameters requestParameters,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().listClaims(requestParameters, requestConfig).getData();
  }

  /**
   * Returns a paginated list of claims.
   *
   * - Employees see only their own claims (BR-10)
   * - Approvers see claims submitted to them (BR-11)
   * - Finance sees all claims (BR-14)
   *
   * Supports filtering by status. Results are paginated (NFR-05).
   *
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listClaimsAsync() throws ApiError {
    return this.listClaimsAsync(ListClaimsParameters.builder().build());
  }

  /**
   * Returns a paginated list of claims.
   *
   * - Employees see only their own claims (BR-10)
   * - Approvers see claims submitted to them (BR-11)
   * - Finance sees all claims (BR-14)
   *
   * Supports filtering by status. Results are paginated (NFR-05).
   *
   * @param requestParameters {@link ListClaimsParameters} Request Parameters Object
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listClaimsAsync(@NonNull ListClaimsParameters requestParameters)
    throws ApiError {
    return this.listClaimsAsync(requestParameters, null);
  }

  /**
   * Returns a paginated list of claims.
   *
   * - Employees see only their own claims (BR-10)
   * - Approvers see claims submitted to them (BR-11)
   * - Finance sees all claims (BR-14)
   *
   * Supports filtering by status. Results are paginated (NFR-05).
   *
   * @param requestParameters {@link ListClaimsParameters} Request Parameters Object
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> listClaimsAsync(
    @NonNull ListClaimsParameters requestParameters,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .listClaimsAsync(requestParameters, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildListClaimsRequest(
    @NonNull ListClaimsParameters requestParameters,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "claims"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setOptionalQueryParameter("status", requestParameters.getStatus())
      .setOptionalQueryParameter("page", requestParameters.getPage())
      .setOptionalQueryParameter("pageSize", requestParameters.getPageSize())
      .build();
  }

  /**
   * Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09).
   *
   * **Business rules:**
   * - A claim must contain at least one expense (BR-21)
   * - Each expense may belong to at most one claim (BR-22)
   * - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18)
   * - All expenses must belong to the authenticated employee
   *
   * @param createClaimRequest {@link CreateClaimRequest} Request Body
   * @return response of {@code Object}
   */
  public Object createClaim(@NonNull CreateClaimRequest createClaimRequest) throws ApiError {
    return this.createClaim(createClaimRequest, null);
  }

  /**
   * Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09).
   *
   * **Business rules:**
   * - A claim must contain at least one expense (BR-21)
   * - Each expense may belong to at most one claim (BR-22)
   * - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18)
   * - All expenses must belong to the authenticated employee
   *
   * @param createClaimRequest {@link CreateClaimRequest} Request Body
   * @return response of {@code Object}
   */
  public Object createClaim(
    @NonNull CreateClaimRequest createClaimRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().createClaim(createClaimRequest, requestConfig).getData();
  }

  /**
   * Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09).
   *
   * **Business rules:**
   * - A claim must contain at least one expense (BR-21)
   * - Each expense may belong to at most one claim (BR-22)
   * - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18)
   * - All expenses must belong to the authenticated employee
   *
   * @param createClaimRequest {@link CreateClaimRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> createClaimAsync(@NonNull CreateClaimRequest createClaimRequest)
    throws ApiError {
    return this.createClaimAsync(createClaimRequest, null);
  }

  /**
   * Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09).
   *
   * **Business rules:**
   * - A claim must contain at least one expense (BR-21)
   * - Each expense may belong to at most one claim (BR-22)
   * - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18)
   * - All expenses must belong to the authenticated employee
   *
   * @param createClaimRequest {@link CreateClaimRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> createClaimAsync(
    @NonNull CreateClaimRequest createClaimRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .createClaimAsync(createClaimRequest, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildCreateClaimRequest(
    @NonNull CreateClaimRequest createClaimRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "claims"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setJsonContent(createClaimRequest)
      .build();
  }

  /**
   * Returns a single claim by ID, including all its expenses and receipts (BR-11).
   *
   * - Employees can only see their own claims (BR-10)
   * - Approvers can see claims submitted to them (BR-11)
   * - Finance can see all claims (BR-14)
   *
   * @param claimId String The unique identifier of the claim.
   * @return response of {@code Object}
   */
  public Object getClaim(@NonNull String claimId) throws ApiError {
    return this.getClaim(claimId, null);
  }

  /**
   * Returns a single claim by ID, including all its expenses and receipts (BR-11).
   *
   * - Employees can only see their own claims (BR-10)
   * - Approvers can see claims submitted to them (BR-11)
   * - Finance can see all claims (BR-14)
   *
   * @param claimId String The unique identifier of the claim.
   * @return response of {@code Object}
   */
  public Object getClaim(@NonNull String claimId, RequestConfig requestConfig) throws ApiError {
    return withRawResponse().getClaim(claimId, requestConfig).getData();
  }

  /**
   * Returns a single claim by ID, including all its expenses and receipts (BR-11).
   *
   * - Employees can only see their own claims (BR-10)
   * - Approvers can see claims submitted to them (BR-11)
   * - Finance can see all claims (BR-14)
   *
   * @param claimId String The unique identifier of the claim.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> getClaimAsync(@NonNull String claimId) throws ApiError {
    return this.getClaimAsync(claimId, null);
  }

  /**
   * Returns a single claim by ID, including all its expenses and receipts (BR-11).
   *
   * - Employees can only see their own claims (BR-10)
   * - Approvers can see claims submitted to them (BR-11)
   * - Finance can see all claims (BR-14)
   *
   * @param claimId String The unique identifier of the claim.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> getClaimAsync(
    @NonNull String claimId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .getClaimAsync(claimId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildGetClaimRequest(@NonNull String claimId, RequestConfig resolvedConfig) {
    return new RequestBuilder(
      HttpMethod.GET,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "claims/{claimId}"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("claimId", claimId)
      .build();
  }

  /**
   * Updates a claim's title or expense list while it is in draft status.
   * Only the claim owner may update it.
   *
   * **Business rules:**
   * - Can only update a claim in draft status
   * - Expenses added must be unclaimed and belong to the owner (BR-22)
   * - Expenses incurred more than 90 days ago cannot be added (BR-18)
   * - A claim must retain at least one expense (BR-21)
   *
   * @param claimId String The unique identifier of the claim to update.
   * @param createClaimRequest {@link CreateClaimRequest} Request Body
   * @return response of {@code Object}
   */
  public Object updateClaim(
    @NonNull String claimId,
    @NonNull CreateClaimRequest createClaimRequest
  ) throws ApiError {
    return this.updateClaim(claimId, createClaimRequest, null);
  }

  /**
   * Updates a claim's title or expense list while it is in draft status.
   * Only the claim owner may update it.
   *
   * **Business rules:**
   * - Can only update a claim in draft status
   * - Expenses added must be unclaimed and belong to the owner (BR-22)
   * - Expenses incurred more than 90 days ago cannot be added (BR-18)
   * - A claim must retain at least one expense (BR-21)
   *
   * @param claimId String The unique identifier of the claim to update.
   * @param createClaimRequest {@link CreateClaimRequest} Request Body
   * @return response of {@code Object}
   */
  public Object updateClaim(
    @NonNull String claimId,
    @NonNull CreateClaimRequest createClaimRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().updateClaim(claimId, createClaimRequest, requestConfig).getData();
  }

  /**
   * Updates a claim's title or expense list while it is in draft status.
   * Only the claim owner may update it.
   *
   * **Business rules:**
   * - Can only update a claim in draft status
   * - Expenses added must be unclaimed and belong to the owner (BR-22)
   * - Expenses incurred more than 90 days ago cannot be added (BR-18)
   * - A claim must retain at least one expense (BR-21)
   *
   * @param claimId String The unique identifier of the claim to update.
   * @param createClaimRequest {@link CreateClaimRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> updateClaimAsync(
    @NonNull String claimId,
    @NonNull CreateClaimRequest createClaimRequest
  ) throws ApiError {
    return this.updateClaimAsync(claimId, createClaimRequest, null);
  }

  /**
   * Updates a claim's title or expense list while it is in draft status.
   * Only the claim owner may update it.
   *
   * **Business rules:**
   * - Can only update a claim in draft status
   * - Expenses added must be unclaimed and belong to the owner (BR-22)
   * - Expenses incurred more than 90 days ago cannot be added (BR-18)
   * - A claim must retain at least one expense (BR-21)
   *
   * @param claimId String The unique identifier of the claim to update.
   * @param createClaimRequest {@link CreateClaimRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> updateClaimAsync(
    @NonNull String claimId,
    @NonNull CreateClaimRequest createClaimRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .updateClaimAsync(claimId, createClaimRequest, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildUpdateClaimRequest(
    @NonNull String claimId,
    @NonNull CreateClaimRequest createClaimRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.PATCH,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "claims/{claimId}"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("claimId", claimId)
      .setJsonContent(createClaimRequest)
      .build();
  }

  /**
   * Deletes a claim in draft status. Only the claim owner may delete it.
   *
   * **Business rules:**
   * - Only draft claims can be deleted
   * - Reimbursed claims are final and cannot be deleted (BR-27)
   * - Deleting a claim releases its expenses back to unclaimed status
   *
   * @param claimId String The unique identifier of the claim to delete.
   * @return response of {@code String}
   */
  public String deleteClaim(@NonNull String claimId) throws ApiError {
    return this.deleteClaim(claimId, null);
  }

  /**
   * Deletes a claim in draft status. Only the claim owner may delete it.
   *
   * **Business rules:**
   * - Only draft claims can be deleted
   * - Reimbursed claims are final and cannot be deleted (BR-27)
   * - Deleting a claim releases its expenses back to unclaimed status
   *
   * @param claimId String The unique identifier of the claim to delete.
   * @return response of {@code String}
   */
  public String deleteClaim(@NonNull String claimId, RequestConfig requestConfig) throws ApiError {
    return withRawResponse().deleteClaim(claimId, requestConfig).getData();
  }

  /**
   * Deletes a claim in draft status. Only the claim owner may delete it.
   *
   * **Business rules:**
   * - Only draft claims can be deleted
   * - Reimbursed claims are final and cannot be deleted (BR-27)
   * - Deleting a claim releases its expenses back to unclaimed status
   *
   * @param claimId String The unique identifier of the claim to delete.
   * @return response of {@code CompletableFuture<String>}
   */
  public CompletableFuture<String> deleteClaimAsync(@NonNull String claimId) throws ApiError {
    return this.deleteClaimAsync(claimId, null);
  }

  /**
   * Deletes a claim in draft status. Only the claim owner may delete it.
   *
   * **Business rules:**
   * - Only draft claims can be deleted
   * - Reimbursed claims are final and cannot be deleted (BR-27)
   * - Deleting a claim releases its expenses back to unclaimed status
   *
   * @param claimId String The unique identifier of the claim to delete.
   * @return response of {@code CompletableFuture<String>}
   */
  public CompletableFuture<String> deleteClaimAsync(
    @NonNull String claimId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .deleteClaimAsync(claimId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildDeleteClaimRequest(@NonNull String claimId, RequestConfig resolvedConfig) {
    return new RequestBuilder(
      HttpMethod.DELETE,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "claims/{claimId}"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("claimId", claimId)
      .build();
  }

  /**
   * Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23).
   *
   * **Business rules enforced on submission:**
   * - Claim must be in draft status (BR-23)
   * - Claim must contain at least one expense (BR-21)
   * - Any expense requiring a receipt (amount &gt; category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20)
   * - No expense in the claim may be older than 90 days (BR-18)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to submit.
   * @return response of {@code Object}
   */
  public Object submitClaim(@NonNull String claimId) throws ApiError {
    return this.submitClaim(claimId, null);
  }

  /**
   * Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23).
   *
   * **Business rules enforced on submission:**
   * - Claim must be in draft status (BR-23)
   * - Claim must contain at least one expense (BR-21)
   * - Any expense requiring a receipt (amount &gt; category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20)
   * - No expense in the claim may be older than 90 days (BR-18)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to submit.
   * @return response of {@code Object}
   */
  public Object submitClaim(@NonNull String claimId, RequestConfig requestConfig) throws ApiError {
    return withRawResponse().submitClaim(claimId, requestConfig).getData();
  }

  /**
   * Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23).
   *
   * **Business rules enforced on submission:**
   * - Claim must be in draft status (BR-23)
   * - Claim must contain at least one expense (BR-21)
   * - Any expense requiring a receipt (amount &gt; category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20)
   * - No expense in the claim may be older than 90 days (BR-18)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to submit.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> submitClaimAsync(@NonNull String claimId) throws ApiError {
    return this.submitClaimAsync(claimId, null);
  }

  /**
   * Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23).
   *
   * **Business rules enforced on submission:**
   * - Claim must be in draft status (BR-23)
   * - Claim must contain at least one expense (BR-21)
   * - Any expense requiring a receipt (amount &gt; category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20)
   * - No expense in the claim may be older than 90 days (BR-18)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to submit.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> submitClaimAsync(
    @NonNull String claimId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .submitClaimAsync(claimId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildSubmitClaimRequest(@NonNull String claimId, RequestConfig resolvedConfig) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "claims/{claimId}/submit"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("claimId", claimId)
      .build();
  }

  /**
   * Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23).
   * **Approver role required.**
   *
   * **Business rules:**
   * - Claim must be in submitted status (BR-23)
   * - An approver cannot approve a claim they submitted themselves (BR-26)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to approve.
   * @return response of {@code Object}
   */
  public Object approveClaim(@NonNull String claimId) throws ApiError {
    return this.approveClaim(claimId, null);
  }

  /**
   * Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23).
   * **Approver role required.**
   *
   * **Business rules:**
   * - Claim must be in submitted status (BR-23)
   * - An approver cannot approve a claim they submitted themselves (BR-26)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to approve.
   * @return response of {@code Object}
   */
  public Object approveClaim(@NonNull String claimId, RequestConfig requestConfig) throws ApiError {
    return withRawResponse().approveClaim(claimId, requestConfig).getData();
  }

  /**
   * Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23).
   * **Approver role required.**
   *
   * **Business rules:**
   * - Claim must be in submitted status (BR-23)
   * - An approver cannot approve a claim they submitted themselves (BR-26)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to approve.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> approveClaimAsync(@NonNull String claimId) throws ApiError {
    return this.approveClaimAsync(claimId, null);
  }

  /**
   * Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23).
   * **Approver role required.**
   *
   * **Business rules:**
   * - Claim must be in submitted status (BR-23)
   * - An approver cannot approve a claim they submitted themselves (BR-26)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to approve.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> approveClaimAsync(
    @NonNull String claimId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .approveClaimAsync(claimId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildApproveClaimRequest(@NonNull String claimId, RequestConfig resolvedConfig) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "claims/{claimId}/approve"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("claimId", claimId)
      .build();
  }

  /**
   * Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24).
   * **Approver role required.**
   *
   * **Business rules:**
   * - Claim must be in submitted status (BR-23)
   * - A rejection reason is required (BR-24)
   * - The rejected claim returns to draft so the employee can correct and resubmit (BR-24)
   * - The rejection reason is preserved on the claim (BR-24)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to reject.
   * @param rejectClaimRequest {@link RejectClaimRequest} Request Body
   * @return response of {@code Object}
   */
  public Object rejectClaim(
    @NonNull String claimId,
    @NonNull RejectClaimRequest rejectClaimRequest
  ) throws ApiError {
    return this.rejectClaim(claimId, rejectClaimRequest, null);
  }

  /**
   * Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24).
   * **Approver role required.**
   *
   * **Business rules:**
   * - Claim must be in submitted status (BR-23)
   * - A rejection reason is required (BR-24)
   * - The rejected claim returns to draft so the employee can correct and resubmit (BR-24)
   * - The rejection reason is preserved on the claim (BR-24)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to reject.
   * @param rejectClaimRequest {@link RejectClaimRequest} Request Body
   * @return response of {@code Object}
   */
  public Object rejectClaim(
    @NonNull String claimId,
    @NonNull RejectClaimRequest rejectClaimRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse().rejectClaim(claimId, rejectClaimRequest, requestConfig).getData();
  }

  /**
   * Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24).
   * **Approver role required.**
   *
   * **Business rules:**
   * - Claim must be in submitted status (BR-23)
   * - A rejection reason is required (BR-24)
   * - The rejected claim returns to draft so the employee can correct and resubmit (BR-24)
   * - The rejection reason is preserved on the claim (BR-24)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to reject.
   * @param rejectClaimRequest {@link RejectClaimRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> rejectClaimAsync(
    @NonNull String claimId,
    @NonNull RejectClaimRequest rejectClaimRequest
  ) throws ApiError {
    return this.rejectClaimAsync(claimId, rejectClaimRequest, null);
  }

  /**
   * Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24).
   * **Approver role required.**
   *
   * **Business rules:**
   * - Claim must be in submitted status (BR-23)
   * - A rejection reason is required (BR-24)
   * - The rejected claim returns to draft so the employee can correct and resubmit (BR-24)
   * - The rejection reason is preserved on the claim (BR-24)
   * - Status change is recorded with actor and timestamp (BR-29)
   *
   * @param claimId String The unique identifier of the claim to reject.
   * @param rejectClaimRequest {@link RejectClaimRequest} Request Body
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> rejectClaimAsync(
    @NonNull String claimId,
    @NonNull RejectClaimRequest rejectClaimRequest,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .rejectClaimAsync(claimId, rejectClaimRequest, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildRejectClaimRequest(
    @NonNull String claimId,
    @NonNull RejectClaimRequest rejectClaimRequest,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "claims/{claimId}/reject"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("claimId", claimId)
      .setJsonContent(rejectClaimRequest)
      .build();
  }

  /**
   * Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23).
   * **Finance role required.**
   *
   * **Business rules:**
   * - Claim must be in approved status (BR-23)
   * - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27)
   * - Status change is recorded with actor and timestamp (BR-29)
   * - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred
   *
   * @param claimId String The unique identifier of the claim to mark as reimbursed.
   * @return response of {@code Object}
   */
  public Object reimburseClaim(@NonNull String claimId) throws ApiError {
    return this.reimburseClaim(claimId, null);
  }

  /**
   * Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23).
   * **Finance role required.**
   *
   * **Business rules:**
   * - Claim must be in approved status (BR-23)
   * - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27)
   * - Status change is recorded with actor and timestamp (BR-29)
   * - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred
   *
   * @param claimId String The unique identifier of the claim to mark as reimbursed.
   * @return response of {@code Object}
   */
  public Object reimburseClaim(@NonNull String claimId, RequestConfig requestConfig)
    throws ApiError {
    return withRawResponse().reimburseClaim(claimId, requestConfig).getData();
  }

  /**
   * Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23).
   * **Finance role required.**
   *
   * **Business rules:**
   * - Claim must be in approved status (BR-23)
   * - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27)
   * - Status change is recorded with actor and timestamp (BR-29)
   * - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred
   *
   * @param claimId String The unique identifier of the claim to mark as reimbursed.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> reimburseClaimAsync(@NonNull String claimId) throws ApiError {
    return this.reimburseClaimAsync(claimId, null);
  }

  /**
   * Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23).
   * **Finance role required.**
   *
   * **Business rules:**
   * - Claim must be in approved status (BR-23)
   * - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27)
   * - Status change is recorded with actor and timestamp (BR-29)
   * - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred
   *
   * @param claimId String The unique identifier of the claim to mark as reimbursed.
   * @return response of {@code CompletableFuture<Object>}
   */
  public CompletableFuture<Object> reimburseClaimAsync(
    @NonNull String claimId,
    RequestConfig requestConfig
  ) throws ApiError {
    return withRawResponse()
      .reimburseClaimAsync(claimId, requestConfig)
      .thenApply(response -> response.getData());
  }

  private Request buildReimburseClaimRequest(
    @NonNull String claimId,
    RequestConfig resolvedConfig
  ) {
    return new RequestBuilder(
      HttpMethod.POST,
      resolveBaseUrl(resolvedConfig, Environment.DEFAULT),
      "claims/{claimId}/reimburse"
    )
      .setAccessTokenAuth(resolveAccessToken(resolvedConfig), "Bearer")
      .setPathParameter("claimId", claimId)
      .build();
  }

  /**
   * Returns an accessor whose methods mirror this service but return the full HTTP response
   * (status code, headers, and raw body) wrapped alongside the parsed data.
   *
   * @return An accessor exposing raw-response variants of this service's methods
   */
  public WithRawResponse withRawResponse() {
    return new WithRawResponse();
  }

  /**
   * Per-call accessor exposing raw-response variants of {@link ExpenseTrackerApiSdkService}'s methods.
   * Reuses the enclosing service's request builders and configuration.
   */
  public class WithRawResponse {

    /**
     * Returns all active categories available for use on new expenses.
     * Finance may also see retired categories by passing `includeRetired=true`.
     *
     * **BR-06** – Any authenticated user can retrieve the category list.
     * **BR-08** – Read-only for employees and approvers.
     *
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listCategories() throws ApiError {
      return this.listCategories(ListCategoriesParameters.builder().build());
    }

    /**
     * Returns all active categories available for use on new expenses.
     * Finance may also see retired categories by passing `includeRetired=true`.
     *
     * **BR-06** – Any authenticated user can retrieve the category list.
     * **BR-08** – Read-only for employees and approvers.
     *
     * @param requestParameters {@link ListCategoriesParameters} Request Parameters Object
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listCategories(
      @NonNull ListCategoriesParameters requestParameters
    ) throws ApiError {
      return this.listCategories(requestParameters, null);
    }

    /**
     * Returns all active categories available for use on new expenses.
     * Finance may also see retired categories by passing `includeRetired=true`.
     *
     * **BR-06** – Any authenticated user can retrieve the category list.
     * **BR-08** – Read-only for employees and approvers.
     *
     * @param requestParameters {@link ListCategoriesParameters} Request Parameters Object
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listCategories(
      @NonNull ListCategoriesParameters requestParameters,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(listCategoriesConfig, requestConfig);
      Request request = buildListCategoriesRequest(requestParameters, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Returns all active categories available for use on new expenses.
     * Finance may also see retired categories by passing `includeRetired=true`.
     *
     * **BR-06** – Any authenticated user can retrieve the category list.
     * **BR-08** – Read-only for employees and approvers.
     *
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listCategoriesAsync()
      throws ApiError {
      return this.listCategoriesAsync(ListCategoriesParameters.builder().build());
    }

    /**
     * Returns all active categories available for use on new expenses.
     * Finance may also see retired categories by passing `includeRetired=true`.
     *
     * **BR-06** – Any authenticated user can retrieve the category list.
     * **BR-08** – Read-only for employees and approvers.
     *
     * @param requestParameters {@link ListCategoriesParameters} Request Parameters Object
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listCategoriesAsync(
      @NonNull ListCategoriesParameters requestParameters
    ) throws ApiError {
      return this.listCategoriesAsync(requestParameters, null);
    }

    /**
     * Returns all active categories available for use on new expenses.
     * Finance may also see retired categories by passing `includeRetired=true`.
     *
     * **BR-06** – Any authenticated user can retrieve the category list.
     * **BR-08** – Read-only for employees and approvers.
     *
     * @param requestParameters {@link ListCategoriesParameters} Request Parameters Object
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listCategoriesAsync(
      @NonNull ListCategoriesParameters requestParameters,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(listCategoriesConfig, requestConfig);
      Request request = buildListCategoriesRequest(requestParameters, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Returns a single category by its ID, including whether it is active or retired.
     *
     * @param categoryId String The unique identifier of the category.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> getCategory(@NonNull String categoryId)
      throws ApiError {
      return this.getCategory(categoryId, null);
    }

    /**
     * Returns a single category by its ID, including whether it is active or retired.
     *
     * @param categoryId String The unique identifier of the category.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> getCategory(
      @NonNull String categoryId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(getCategoryConfig, requestConfig);
      Request request = buildGetCategoryRequest(categoryId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Returns a single category by its ID, including whether it is active or retired.
     *
     * @param categoryId String The unique identifier of the category.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> getCategoryAsync(
      @NonNull String categoryId
    ) throws ApiError {
      return this.getCategoryAsync(categoryId, null);
    }

    /**
     * Returns a single category by its ID, including whether it is active or retired.
     *
     * @param categoryId String The unique identifier of the category.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> getCategoryAsync(
      @NonNull String categoryId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(getCategoryConfig, requestConfig);
      Request request = buildGetCategoryRequest(categoryId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Creates a new expense category. **Finance role required** (BR-07, BR-08).
     *
     * The `receiptThreshold` is the amount above which a receipt becomes mandatory.
     * Set `requiresReceiptAlways: true` to mandate a receipt at any amount.
     * Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).
     *
     * @param createCategoryRequest {@link CreateCategoryRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> createCategory(
      @NonNull CreateCategoryRequest createCategoryRequest
    ) throws ApiError {
      return this.createCategory(createCategoryRequest, null);
    }

    /**
     * Creates a new expense category. **Finance role required** (BR-07, BR-08).
     *
     * The `receiptThreshold` is the amount above which a receipt becomes mandatory.
     * Set `requiresReceiptAlways: true` to mandate a receipt at any amount.
     * Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).
     *
     * @param createCategoryRequest {@link CreateCategoryRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> createCategory(
      @NonNull CreateCategoryRequest createCategoryRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(createCategoryConfig, requestConfig);
      Request request = buildCreateCategoryRequest(createCategoryRequest, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Creates a new expense category. **Finance role required** (BR-07, BR-08).
     *
     * The `receiptThreshold` is the amount above which a receipt becomes mandatory.
     * Set `requiresReceiptAlways: true` to mandate a receipt at any amount.
     * Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).
     *
     * @param createCategoryRequest {@link CreateCategoryRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> createCategoryAsync(
      @NonNull CreateCategoryRequest createCategoryRequest
    ) throws ApiError {
      return this.createCategoryAsync(createCategoryRequest, null);
    }

    /**
     * Creates a new expense category. **Finance role required** (BR-07, BR-08).
     *
     * The `receiptThreshold` is the amount above which a receipt becomes mandatory.
     * Set `requiresReceiptAlways: true` to mandate a receipt at any amount.
     * Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).
     *
     * @param createCategoryRequest {@link CreateCategoryRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> createCategoryAsync(
      @NonNull CreateCategoryRequest createCategoryRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(createCategoryConfig, requestConfig);
      Request request = buildCreateCategoryRequest(createCategoryRequest, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Updates an existing category's name, receipt threshold, or client reference requirement.
     * **Finance role required** (BR-07, BR-08).
     *
     * Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).
     *
     * @param categoryId String The unique identifier of the category to update.
     * @param updateCategoryRequest {@link UpdateCategoryRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> updateCategory(
      @NonNull String categoryId,
      @NonNull UpdateCategoryRequest updateCategoryRequest
    ) throws ApiError {
      return this.updateCategory(categoryId, updateCategoryRequest, null);
    }

    /**
     * Updates an existing category's name, receipt threshold, or client reference requirement.
     * **Finance role required** (BR-07, BR-08).
     *
     * Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).
     *
     * @param categoryId String The unique identifier of the category to update.
     * @param updateCategoryRequest {@link UpdateCategoryRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> updateCategory(
      @NonNull String categoryId,
      @NonNull UpdateCategoryRequest updateCategoryRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(updateCategoryConfig, requestConfig);
      Request request = buildUpdateCategoryRequest(
        categoryId,
        updateCategoryRequest,
        resolvedConfig
      );
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Updates an existing category's name, receipt threshold, or client reference requirement.
     * **Finance role required** (BR-07, BR-08).
     *
     * Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).
     *
     * @param categoryId String The unique identifier of the category to update.
     * @param updateCategoryRequest {@link UpdateCategoryRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> updateCategoryAsync(
      @NonNull String categoryId,
      @NonNull UpdateCategoryRequest updateCategoryRequest
    ) throws ApiError {
      return this.updateCategoryAsync(categoryId, updateCategoryRequest, null);
    }

    /**
     * Updates an existing category's name, receipt threshold, or client reference requirement.
     * **Finance role required** (BR-07, BR-08).
     *
     * Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).
     *
     * @param categoryId String The unique identifier of the category to update.
     * @param updateCategoryRequest {@link UpdateCategoryRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> updateCategoryAsync(
      @NonNull String categoryId,
      @NonNull UpdateCategoryRequest updateCategoryRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(updateCategoryConfig, requestConfig);
      Request request = buildUpdateCategoryRequest(
        categoryId,
        updateCategoryRequest,
        resolvedConfig
      );
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Returns a paginated list of expenses for the authenticated employee.
     * Finance may retrieve expenses across all employees for reporting (BR-14).
     *
     * Supports filtering by date range, category, and claimed status (BR-03).
     * Results are paginated (NFR-05).
     *
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listExpenses() throws ApiError {
      return this.listExpenses(ListExpensesParameters.builder().build());
    }

    /**
     * Returns a paginated list of expenses for the authenticated employee.
     * Finance may retrieve expenses across all employees for reporting (BR-14).
     *
     * Supports filtering by date range, category, and claimed status (BR-03).
     * Results are paginated (NFR-05).
     *
     * @param requestParameters {@link ListExpensesParameters} Request Parameters Object
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listExpenses(
      @NonNull ListExpensesParameters requestParameters
    ) throws ApiError {
      return this.listExpenses(requestParameters, null);
    }

    /**
     * Returns a paginated list of expenses for the authenticated employee.
     * Finance may retrieve expenses across all employees for reporting (BR-14).
     *
     * Supports filtering by date range, category, and claimed status (BR-03).
     * Results are paginated (NFR-05).
     *
     * @param requestParameters {@link ListExpensesParameters} Request Parameters Object
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listExpenses(
      @NonNull ListExpensesParameters requestParameters,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(listExpensesConfig, requestConfig);
      Request request = buildListExpensesRequest(requestParameters, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Returns a paginated list of expenses for the authenticated employee.
     * Finance may retrieve expenses across all employees for reporting (BR-14).
     *
     * Supports filtering by date range, category, and claimed status (BR-03).
     * Results are paginated (NFR-05).
     *
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listExpensesAsync()
      throws ApiError {
      return this.listExpensesAsync(ListExpensesParameters.builder().build());
    }

    /**
     * Returns a paginated list of expenses for the authenticated employee.
     * Finance may retrieve expenses across all employees for reporting (BR-14).
     *
     * Supports filtering by date range, category, and claimed status (BR-03).
     * Results are paginated (NFR-05).
     *
     * @param requestParameters {@link ListExpensesParameters} Request Parameters Object
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listExpensesAsync(
      @NonNull ListExpensesParameters requestParameters
    ) throws ApiError {
      return this.listExpensesAsync(requestParameters, null);
    }

    /**
     * Returns a paginated list of expenses for the authenticated employee.
     * Finance may retrieve expenses across all employees for reporting (BR-14).
     *
     * Supports filtering by date range, category, and claimed status (BR-03).
     * Results are paginated (NFR-05).
     *
     * @param requestParameters {@link ListExpensesParameters} Request Parameters Object
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listExpensesAsync(
      @NonNull ListExpensesParameters requestParameters,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(listExpensesConfig, requestConfig);
      Request request = buildListExpensesRequest(requestParameters, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Records a new expense for the authenticated employee (BR-01).
     *
     * **Business rules:**
     * - Amount must be greater than zero (BR-16)
     * - Date incurred must not be in the future (BR-17)
     * - Category must be an active category (BR-06)
     * - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28)
     * - Client Entertainment requires a client reference (BR-20)
     *
     * @param createExpenseRequest {@link CreateExpenseRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> createExpense(
      @NonNull CreateExpenseRequest createExpenseRequest
    ) throws ApiError {
      return this.createExpense(createExpenseRequest, null);
    }

    /**
     * Records a new expense for the authenticated employee (BR-01).
     *
     * **Business rules:**
     * - Amount must be greater than zero (BR-16)
     * - Date incurred must not be in the future (BR-17)
     * - Category must be an active category (BR-06)
     * - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28)
     * - Client Entertainment requires a client reference (BR-20)
     *
     * @param createExpenseRequest {@link CreateExpenseRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> createExpense(
      @NonNull CreateExpenseRequest createExpenseRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(createExpenseConfig, requestConfig);
      Request request = buildCreateExpenseRequest(createExpenseRequest, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Records a new expense for the authenticated employee (BR-01).
     *
     * **Business rules:**
     * - Amount must be greater than zero (BR-16)
     * - Date incurred must not be in the future (BR-17)
     * - Category must be an active category (BR-06)
     * - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28)
     * - Client Entertainment requires a client reference (BR-20)
     *
     * @param createExpenseRequest {@link CreateExpenseRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> createExpenseAsync(
      @NonNull CreateExpenseRequest createExpenseRequest
    ) throws ApiError {
      return this.createExpenseAsync(createExpenseRequest, null);
    }

    /**
     * Records a new expense for the authenticated employee (BR-01).
     *
     * **Business rules:**
     * - Amount must be greater than zero (BR-16)
     * - Date incurred must not be in the future (BR-17)
     * - Category must be an active category (BR-06)
     * - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28)
     * - Client Entertainment requires a client reference (BR-20)
     *
     * @param createExpenseRequest {@link CreateExpenseRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> createExpenseAsync(
      @NonNull CreateExpenseRequest createExpenseRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(createExpenseConfig, requestConfig);
      Request request = buildCreateExpenseRequest(createExpenseRequest, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05).
     * Finance can retrieve any expense.
     *
     * @param expenseId String The unique identifier of the expense.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> getExpense(@NonNull String expenseId)
      throws ApiError {
      return this.getExpense(expenseId, null);
    }

    /**
     * Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05).
     * Finance can retrieve any expense.
     *
     * @param expenseId String The unique identifier of the expense.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> getExpense(
      @NonNull String expenseId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(getExpenseConfig, requestConfig);
      Request request = buildGetExpenseRequest(expenseId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05).
     * Finance can retrieve any expense.
     *
     * @param expenseId String The unique identifier of the expense.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> getExpenseAsync(
      @NonNull String expenseId
    ) throws ApiError {
      return this.getExpenseAsync(expenseId, null);
    }

    /**
     * Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05).
     * Finance can retrieve any expense.
     *
     * @param expenseId String The unique identifier of the expense.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> getExpenseAsync(
      @NonNull String expenseId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(getExpenseConfig, requestConfig);
      Request request = buildGetExpenseRequest(expenseId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Amends an existing expense. Only the owner may update it (BR-02, BR-05).
     *
     * **Business rules:**
     * - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
     * - Amount must remain greater than zero (BR-16)
     * - Date incurred must not be in the future (BR-17)
     *
     * @param expenseId String The unique identifier of the expense to update.
     * @param updateExpenseRequest {@link UpdateExpenseRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> updateExpense(
      @NonNull String expenseId,
      @NonNull UpdateExpenseRequest updateExpenseRequest
    ) throws ApiError {
      return this.updateExpense(expenseId, updateExpenseRequest, null);
    }

    /**
     * Amends an existing expense. Only the owner may update it (BR-02, BR-05).
     *
     * **Business rules:**
     * - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
     * - Amount must remain greater than zero (BR-16)
     * - Date incurred must not be in the future (BR-17)
     *
     * @param expenseId String The unique identifier of the expense to update.
     * @param updateExpenseRequest {@link UpdateExpenseRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> updateExpense(
      @NonNull String expenseId,
      @NonNull UpdateExpenseRequest updateExpenseRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(updateExpenseConfig, requestConfig);
      Request request = buildUpdateExpenseRequest(expenseId, updateExpenseRequest, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Amends an existing expense. Only the owner may update it (BR-02, BR-05).
     *
     * **Business rules:**
     * - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
     * - Amount must remain greater than zero (BR-16)
     * - Date incurred must not be in the future (BR-17)
     *
     * @param expenseId String The unique identifier of the expense to update.
     * @param updateExpenseRequest {@link UpdateExpenseRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> updateExpenseAsync(
      @NonNull String expenseId,
      @NonNull UpdateExpenseRequest updateExpenseRequest
    ) throws ApiError {
      return this.updateExpenseAsync(expenseId, updateExpenseRequest, null);
    }

    /**
     * Amends an existing expense. Only the owner may update it (BR-02, BR-05).
     *
     * **Business rules:**
     * - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
     * - Amount must remain greater than zero (BR-16)
     * - Date incurred must not be in the future (BR-17)
     *
     * @param expenseId String The unique identifier of the expense to update.
     * @param updateExpenseRequest {@link UpdateExpenseRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> updateExpenseAsync(
      @NonNull String expenseId,
      @NonNull UpdateExpenseRequest updateExpenseRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(updateExpenseConfig, requestConfig);
      Request request = buildUpdateExpenseRequest(expenseId, updateExpenseRequest, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Deletes an expense. Only the owner may delete it (BR-02, BR-05).
     *
     * **Business rules:**
     * - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
     *
     * @param expenseId String The unique identifier of the expense to delete.
     * @return response of {@code ExpenseTrackerApiSdkResponse<String>}
     */
    public ExpenseTrackerApiSdkResponse<String> deleteExpense(@NonNull String expenseId)
      throws ApiError {
      return this.deleteExpense(expenseId, null);
    }

    /**
     * Deletes an expense. Only the owner may delete it (BR-02, BR-05).
     *
     * **Business rules:**
     * - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
     *
     * @param expenseId String The unique identifier of the expense to delete.
     * @return response of {@code ExpenseTrackerApiSdkResponse<String>}
     */
    public ExpenseTrackerApiSdkResponse<String> deleteExpense(
      @NonNull String expenseId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(deleteExpenseConfig, requestConfig);
      Request request = buildDeleteExpenseRequest(expenseId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.toBodyString(bodyBytes)
      );
    }

    /**
     * Deletes an expense. Only the owner may delete it (BR-02, BR-05).
     *
     * **Business rules:**
     * - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
     *
     * @param expenseId String The unique identifier of the expense to delete.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<String>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<String>> deleteExpenseAsync(
      @NonNull String expenseId
    ) throws ApiError {
      return this.deleteExpenseAsync(expenseId, null);
    }

    /**
     * Deletes an expense. Only the owner may delete it (BR-02, BR-05).
     *
     * **Business rules:**
     * - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)
     *
     * @param expenseId String The unique identifier of the expense to delete.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<String>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<String>> deleteExpenseAsync(
      @NonNull String expenseId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(deleteExpenseConfig, requestConfig);
      Request request = buildDeleteExpenseRequest(expenseId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.toBodyString(bodyBytes)
        );
      });
    }

    /**
     * Returns all receipt attachments for a given expense.
     * Only the expense owner (or Finance) may access receipts (BR-05).
     *
     * **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.
     *
     * @param expenseId String The unique identifier of the expense.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listReceipts(@NonNull String expenseId)
      throws ApiError {
      return this.listReceipts(expenseId, null);
    }

    /**
     * Returns all receipt attachments for a given expense.
     * Only the expense owner (or Finance) may access receipts (BR-05).
     *
     * **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.
     *
     * @param expenseId String The unique identifier of the expense.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listReceipts(
      @NonNull String expenseId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(listReceiptsConfig, requestConfig);
      Request request = buildListReceiptsRequest(expenseId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Returns all receipt attachments for a given expense.
     * Only the expense owner (or Finance) may access receipts (BR-05).
     *
     * **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.
     *
     * @param expenseId String The unique identifier of the expense.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listReceiptsAsync(
      @NonNull String expenseId
    ) throws ApiError {
      return this.listReceiptsAsync(expenseId, null);
    }

    /**
     * Returns all receipt attachments for a given expense.
     * Only the expense owner (or Finance) may access receipts (BR-05).
     *
     * **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.
     *
     * @param expenseId String The unique identifier of the expense.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listReceiptsAsync(
      @NonNull String expenseId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(listReceiptsConfig, requestConfig);
      Request request = buildListReceiptsRequest(expenseId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Attaches a receipt image to an expense (BR-04).
     *
     * **Business rules:**
     * - Accepted formats: JPEG, PNG, PDF (NFR-07)
     * - Maximum file size: 10 MB per receipt (NFR-07)
     * - Maximum 10 receipts per expense (NFR-07)
     * - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19)
     * - Client Entertainment always requires a receipt (BR-20)
     * - Only the expense owner may attach receipts (BR-05)
     *
     * @param expenseId String The unique identifier of the expense.
     * @param uploadReceiptRequest {@link UploadReceiptRequest} Request Body
     * @param _filename String Filename for the uploaded file
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> uploadReceipt(
      @NonNull String expenseId,
      @NonNull UploadReceiptRequest uploadReceiptRequest,
      @NonNull String _filename
    ) throws ApiError {
      return this.uploadReceipt(expenseId, uploadReceiptRequest, _filename, null);
    }

    /**
     * Attaches a receipt image to an expense (BR-04).
     *
     * **Business rules:**
     * - Accepted formats: JPEG, PNG, PDF (NFR-07)
     * - Maximum file size: 10 MB per receipt (NFR-07)
     * - Maximum 10 receipts per expense (NFR-07)
     * - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19)
     * - Client Entertainment always requires a receipt (BR-20)
     * - Only the expense owner may attach receipts (BR-05)
     *
     * @param expenseId String The unique identifier of the expense.
     * @param uploadReceiptRequest {@link UploadReceiptRequest} Request Body
     * @param _filename String Filename for the uploaded file
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> uploadReceipt(
      @NonNull String expenseId,
      @NonNull UploadReceiptRequest uploadReceiptRequest,
      @NonNull String _filename,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(uploadReceiptConfig, requestConfig);
      Request request = buildUploadReceiptRequest(
        expenseId,
        uploadReceiptRequest,
        _filename,
        resolvedConfig
      );
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Attaches a receipt image to an expense (BR-04).
     *
     * **Business rules:**
     * - Accepted formats: JPEG, PNG, PDF (NFR-07)
     * - Maximum file size: 10 MB per receipt (NFR-07)
     * - Maximum 10 receipts per expense (NFR-07)
     * - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19)
     * - Client Entertainment always requires a receipt (BR-20)
     * - Only the expense owner may attach receipts (BR-05)
     *
     * @param expenseId String The unique identifier of the expense.
     * @param uploadReceiptRequest {@link UploadReceiptRequest} Request Body
     * @param _filename String Filename for the uploaded file
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> uploadReceiptAsync(
      @NonNull String expenseId,
      @NonNull UploadReceiptRequest uploadReceiptRequest,
      @NonNull String _filename
    ) throws ApiError {
      return this.uploadReceiptAsync(expenseId, uploadReceiptRequest, _filename, null);
    }

    /**
     * Attaches a receipt image to an expense (BR-04).
     *
     * **Business rules:**
     * - Accepted formats: JPEG, PNG, PDF (NFR-07)
     * - Maximum file size: 10 MB per receipt (NFR-07)
     * - Maximum 10 receipts per expense (NFR-07)
     * - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19)
     * - Client Entertainment always requires a receipt (BR-20)
     * - Only the expense owner may attach receipts (BR-05)
     *
     * @param expenseId String The unique identifier of the expense.
     * @param uploadReceiptRequest {@link UploadReceiptRequest} Request Body
     * @param _filename String Filename for the uploaded file
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> uploadReceiptAsync(
      @NonNull String expenseId,
      @NonNull UploadReceiptRequest uploadReceiptRequest,
      @NonNull String _filename,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(uploadReceiptConfig, requestConfig);
      Request request = buildUploadReceiptRequest(
        expenseId,
        uploadReceiptRequest,
        _filename,
        resolvedConfig
      );
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Removes a receipt attachment from an expense (BR-04).
     *
     * **Business rules:**
     * - Only the expense owner may remove receipts (BR-05)
     * - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)
     *
     * @param expenseId String The unique identifier of the expense.
     * @param receiptId String The unique identifier of the receipt to delete.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> deleteReceipt(
      @NonNull String expenseId,
      @NonNull String receiptId
    ) throws ApiError {
      return this.deleteReceipt(expenseId, receiptId, null);
    }

    /**
     * Removes a receipt attachment from an expense (BR-04).
     *
     * **Business rules:**
     * - Only the expense owner may remove receipts (BR-05)
     * - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)
     *
     * @param expenseId String The unique identifier of the expense.
     * @param receiptId String The unique identifier of the receipt to delete.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> deleteReceipt(
      @NonNull String expenseId,
      @NonNull String receiptId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(deleteReceiptConfig, requestConfig);
      Request request = buildDeleteReceiptRequest(expenseId, receiptId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Removes a receipt attachment from an expense (BR-04).
     *
     * **Business rules:**
     * - Only the expense owner may remove receipts (BR-05)
     * - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)
     *
     * @param expenseId String The unique identifier of the expense.
     * @param receiptId String The unique identifier of the receipt to delete.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> deleteReceiptAsync(
      @NonNull String expenseId,
      @NonNull String receiptId
    ) throws ApiError {
      return this.deleteReceiptAsync(expenseId, receiptId, null);
    }

    /**
     * Removes a receipt attachment from an expense (BR-04).
     *
     * **Business rules:**
     * - Only the expense owner may remove receipts (BR-05)
     * - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)
     *
     * @param expenseId String The unique identifier of the expense.
     * @param receiptId String The unique identifier of the receipt to delete.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> deleteReceiptAsync(
      @NonNull String expenseId,
      @NonNull String receiptId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(deleteReceiptConfig, requestConfig);
      Request request = buildDeleteReceiptRequest(expenseId, receiptId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Returns a paginated list of claims.
     *
     * - Employees see only their own claims (BR-10)
     * - Approvers see claims submitted to them (BR-11)
     * - Finance sees all claims (BR-14)
     *
     * Supports filtering by status. Results are paginated (NFR-05).
     *
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listClaims() throws ApiError {
      return this.listClaims(ListClaimsParameters.builder().build());
    }

    /**
     * Returns a paginated list of claims.
     *
     * - Employees see only their own claims (BR-10)
     * - Approvers see claims submitted to them (BR-11)
     * - Finance sees all claims (BR-14)
     *
     * Supports filtering by status. Results are paginated (NFR-05).
     *
     * @param requestParameters {@link ListClaimsParameters} Request Parameters Object
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listClaims(
      @NonNull ListClaimsParameters requestParameters
    ) throws ApiError {
      return this.listClaims(requestParameters, null);
    }

    /**
     * Returns a paginated list of claims.
     *
     * - Employees see only their own claims (BR-10)
     * - Approvers see claims submitted to them (BR-11)
     * - Finance sees all claims (BR-14)
     *
     * Supports filtering by status. Results are paginated (NFR-05).
     *
     * @param requestParameters {@link ListClaimsParameters} Request Parameters Object
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> listClaims(
      @NonNull ListClaimsParameters requestParameters,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(listClaimsConfig, requestConfig);
      Request request = buildListClaimsRequest(requestParameters, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Returns a paginated list of claims.
     *
     * - Employees see only their own claims (BR-10)
     * - Approvers see claims submitted to them (BR-11)
     * - Finance sees all claims (BR-14)
     *
     * Supports filtering by status. Results are paginated (NFR-05).
     *
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listClaimsAsync()
      throws ApiError {
      return this.listClaimsAsync(ListClaimsParameters.builder().build());
    }

    /**
     * Returns a paginated list of claims.
     *
     * - Employees see only their own claims (BR-10)
     * - Approvers see claims submitted to them (BR-11)
     * - Finance sees all claims (BR-14)
     *
     * Supports filtering by status. Results are paginated (NFR-05).
     *
     * @param requestParameters {@link ListClaimsParameters} Request Parameters Object
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listClaimsAsync(
      @NonNull ListClaimsParameters requestParameters
    ) throws ApiError {
      return this.listClaimsAsync(requestParameters, null);
    }

    /**
     * Returns a paginated list of claims.
     *
     * - Employees see only their own claims (BR-10)
     * - Approvers see claims submitted to them (BR-11)
     * - Finance sees all claims (BR-14)
     *
     * Supports filtering by status. Results are paginated (NFR-05).
     *
     * @param requestParameters {@link ListClaimsParameters} Request Parameters Object
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> listClaimsAsync(
      @NonNull ListClaimsParameters requestParameters,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(listClaimsConfig, requestConfig);
      Request request = buildListClaimsRequest(requestParameters, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09).
     *
     * **Business rules:**
     * - A claim must contain at least one expense (BR-21)
     * - Each expense may belong to at most one claim (BR-22)
     * - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18)
     * - All expenses must belong to the authenticated employee
     *
     * @param createClaimRequest {@link CreateClaimRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> createClaim(
      @NonNull CreateClaimRequest createClaimRequest
    ) throws ApiError {
      return this.createClaim(createClaimRequest, null);
    }

    /**
     * Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09).
     *
     * **Business rules:**
     * - A claim must contain at least one expense (BR-21)
     * - Each expense may belong to at most one claim (BR-22)
     * - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18)
     * - All expenses must belong to the authenticated employee
     *
     * @param createClaimRequest {@link CreateClaimRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> createClaim(
      @NonNull CreateClaimRequest createClaimRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(createClaimConfig, requestConfig);
      Request request = buildCreateClaimRequest(createClaimRequest, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09).
     *
     * **Business rules:**
     * - A claim must contain at least one expense (BR-21)
     * - Each expense may belong to at most one claim (BR-22)
     * - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18)
     * - All expenses must belong to the authenticated employee
     *
     * @param createClaimRequest {@link CreateClaimRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> createClaimAsync(
      @NonNull CreateClaimRequest createClaimRequest
    ) throws ApiError {
      return this.createClaimAsync(createClaimRequest, null);
    }

    /**
     * Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09).
     *
     * **Business rules:**
     * - A claim must contain at least one expense (BR-21)
     * - Each expense may belong to at most one claim (BR-22)
     * - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18)
     * - All expenses must belong to the authenticated employee
     *
     * @param createClaimRequest {@link CreateClaimRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> createClaimAsync(
      @NonNull CreateClaimRequest createClaimRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(createClaimConfig, requestConfig);
      Request request = buildCreateClaimRequest(createClaimRequest, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Returns a single claim by ID, including all its expenses and receipts (BR-11).
     *
     * - Employees can only see their own claims (BR-10)
     * - Approvers can see claims submitted to them (BR-11)
     * - Finance can see all claims (BR-14)
     *
     * @param claimId String The unique identifier of the claim.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> getClaim(@NonNull String claimId) throws ApiError {
      return this.getClaim(claimId, null);
    }

    /**
     * Returns a single claim by ID, including all its expenses and receipts (BR-11).
     *
     * - Employees can only see their own claims (BR-10)
     * - Approvers can see claims submitted to them (BR-11)
     * - Finance can see all claims (BR-14)
     *
     * @param claimId String The unique identifier of the claim.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> getClaim(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(getClaimConfig, requestConfig);
      Request request = buildGetClaimRequest(claimId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Returns a single claim by ID, including all its expenses and receipts (BR-11).
     *
     * - Employees can only see their own claims (BR-10)
     * - Approvers can see claims submitted to them (BR-11)
     * - Finance can see all claims (BR-14)
     *
     * @param claimId String The unique identifier of the claim.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> getClaimAsync(
      @NonNull String claimId
    ) throws ApiError {
      return this.getClaimAsync(claimId, null);
    }

    /**
     * Returns a single claim by ID, including all its expenses and receipts (BR-11).
     *
     * - Employees can only see their own claims (BR-10)
     * - Approvers can see claims submitted to them (BR-11)
     * - Finance can see all claims (BR-14)
     *
     * @param claimId String The unique identifier of the claim.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> getClaimAsync(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(getClaimConfig, requestConfig);
      Request request = buildGetClaimRequest(claimId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Updates a claim's title or expense list while it is in draft status.
     * Only the claim owner may update it.
     *
     * **Business rules:**
     * - Can only update a claim in draft status
     * - Expenses added must be unclaimed and belong to the owner (BR-22)
     * - Expenses incurred more than 90 days ago cannot be added (BR-18)
     * - A claim must retain at least one expense (BR-21)
     *
     * @param claimId String The unique identifier of the claim to update.
     * @param createClaimRequest {@link CreateClaimRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> updateClaim(
      @NonNull String claimId,
      @NonNull CreateClaimRequest createClaimRequest
    ) throws ApiError {
      return this.updateClaim(claimId, createClaimRequest, null);
    }

    /**
     * Updates a claim's title or expense list while it is in draft status.
     * Only the claim owner may update it.
     *
     * **Business rules:**
     * - Can only update a claim in draft status
     * - Expenses added must be unclaimed and belong to the owner (BR-22)
     * - Expenses incurred more than 90 days ago cannot be added (BR-18)
     * - A claim must retain at least one expense (BR-21)
     *
     * @param claimId String The unique identifier of the claim to update.
     * @param createClaimRequest {@link CreateClaimRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> updateClaim(
      @NonNull String claimId,
      @NonNull CreateClaimRequest createClaimRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(updateClaimConfig, requestConfig);
      Request request = buildUpdateClaimRequest(claimId, createClaimRequest, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Updates a claim's title or expense list while it is in draft status.
     * Only the claim owner may update it.
     *
     * **Business rules:**
     * - Can only update a claim in draft status
     * - Expenses added must be unclaimed and belong to the owner (BR-22)
     * - Expenses incurred more than 90 days ago cannot be added (BR-18)
     * - A claim must retain at least one expense (BR-21)
     *
     * @param claimId String The unique identifier of the claim to update.
     * @param createClaimRequest {@link CreateClaimRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> updateClaimAsync(
      @NonNull String claimId,
      @NonNull CreateClaimRequest createClaimRequest
    ) throws ApiError {
      return this.updateClaimAsync(claimId, createClaimRequest, null);
    }

    /**
     * Updates a claim's title or expense list while it is in draft status.
     * Only the claim owner may update it.
     *
     * **Business rules:**
     * - Can only update a claim in draft status
     * - Expenses added must be unclaimed and belong to the owner (BR-22)
     * - Expenses incurred more than 90 days ago cannot be added (BR-18)
     * - A claim must retain at least one expense (BR-21)
     *
     * @param claimId String The unique identifier of the claim to update.
     * @param createClaimRequest {@link CreateClaimRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> updateClaimAsync(
      @NonNull String claimId,
      @NonNull CreateClaimRequest createClaimRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(updateClaimConfig, requestConfig);
      Request request = buildUpdateClaimRequest(claimId, createClaimRequest, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Deletes a claim in draft status. Only the claim owner may delete it.
     *
     * **Business rules:**
     * - Only draft claims can be deleted
     * - Reimbursed claims are final and cannot be deleted (BR-27)
     * - Deleting a claim releases its expenses back to unclaimed status
     *
     * @param claimId String The unique identifier of the claim to delete.
     * @return response of {@code ExpenseTrackerApiSdkResponse<String>}
     */
    public ExpenseTrackerApiSdkResponse<String> deleteClaim(@NonNull String claimId)
      throws ApiError {
      return this.deleteClaim(claimId, null);
    }

    /**
     * Deletes a claim in draft status. Only the claim owner may delete it.
     *
     * **Business rules:**
     * - Only draft claims can be deleted
     * - Reimbursed claims are final and cannot be deleted (BR-27)
     * - Deleting a claim releases its expenses back to unclaimed status
     *
     * @param claimId String The unique identifier of the claim to delete.
     * @return response of {@code ExpenseTrackerApiSdkResponse<String>}
     */
    public ExpenseTrackerApiSdkResponse<String> deleteClaim(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(deleteClaimConfig, requestConfig);
      Request request = buildDeleteClaimRequest(claimId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.toBodyString(bodyBytes)
      );
    }

    /**
     * Deletes a claim in draft status. Only the claim owner may delete it.
     *
     * **Business rules:**
     * - Only draft claims can be deleted
     * - Reimbursed claims are final and cannot be deleted (BR-27)
     * - Deleting a claim releases its expenses back to unclaimed status
     *
     * @param claimId String The unique identifier of the claim to delete.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<String>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<String>> deleteClaimAsync(
      @NonNull String claimId
    ) throws ApiError {
      return this.deleteClaimAsync(claimId, null);
    }

    /**
     * Deletes a claim in draft status. Only the claim owner may delete it.
     *
     * **Business rules:**
     * - Only draft claims can be deleted
     * - Reimbursed claims are final and cannot be deleted (BR-27)
     * - Deleting a claim releases its expenses back to unclaimed status
     *
     * @param claimId String The unique identifier of the claim to delete.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<String>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<String>> deleteClaimAsync(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(deleteClaimConfig, requestConfig);
      Request request = buildDeleteClaimRequest(claimId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.toBodyString(bodyBytes)
        );
      });
    }

    /**
     * Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23).
     *
     * **Business rules enforced on submission:**
     * - Claim must be in draft status (BR-23)
     * - Claim must contain at least one expense (BR-21)
     * - Any expense requiring a receipt (amount &gt; category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20)
     * - No expense in the claim may be older than 90 days (BR-18)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to submit.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> submitClaim(@NonNull String claimId)
      throws ApiError {
      return this.submitClaim(claimId, null);
    }

    /**
     * Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23).
     *
     * **Business rules enforced on submission:**
     * - Claim must be in draft status (BR-23)
     * - Claim must contain at least one expense (BR-21)
     * - Any expense requiring a receipt (amount &gt; category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20)
     * - No expense in the claim may be older than 90 days (BR-18)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to submit.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> submitClaim(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(submitClaimConfig, requestConfig);
      Request request = buildSubmitClaimRequest(claimId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23).
     *
     * **Business rules enforced on submission:**
     * - Claim must be in draft status (BR-23)
     * - Claim must contain at least one expense (BR-21)
     * - Any expense requiring a receipt (amount &gt; category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20)
     * - No expense in the claim may be older than 90 days (BR-18)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to submit.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> submitClaimAsync(
      @NonNull String claimId
    ) throws ApiError {
      return this.submitClaimAsync(claimId, null);
    }

    /**
     * Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23).
     *
     * **Business rules enforced on submission:**
     * - Claim must be in draft status (BR-23)
     * - Claim must contain at least one expense (BR-21)
     * - Any expense requiring a receipt (amount &gt; category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20)
     * - No expense in the claim may be older than 90 days (BR-18)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to submit.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> submitClaimAsync(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(submitClaimConfig, requestConfig);
      Request request = buildSubmitClaimRequest(claimId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23).
     * **Approver role required.**
     *
     * **Business rules:**
     * - Claim must be in submitted status (BR-23)
     * - An approver cannot approve a claim they submitted themselves (BR-26)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to approve.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> approveClaim(@NonNull String claimId)
      throws ApiError {
      return this.approveClaim(claimId, null);
    }

    /**
     * Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23).
     * **Approver role required.**
     *
     * **Business rules:**
     * - Claim must be in submitted status (BR-23)
     * - An approver cannot approve a claim they submitted themselves (BR-26)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to approve.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> approveClaim(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(approveClaimConfig, requestConfig);
      Request request = buildApproveClaimRequest(claimId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23).
     * **Approver role required.**
     *
     * **Business rules:**
     * - Claim must be in submitted status (BR-23)
     * - An approver cannot approve a claim they submitted themselves (BR-26)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to approve.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> approveClaimAsync(
      @NonNull String claimId
    ) throws ApiError {
      return this.approveClaimAsync(claimId, null);
    }

    /**
     * Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23).
     * **Approver role required.**
     *
     * **Business rules:**
     * - Claim must be in submitted status (BR-23)
     * - An approver cannot approve a claim they submitted themselves (BR-26)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to approve.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> approveClaimAsync(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(approveClaimConfig, requestConfig);
      Request request = buildApproveClaimRequest(claimId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24).
     * **Approver role required.**
     *
     * **Business rules:**
     * - Claim must be in submitted status (BR-23)
     * - A rejection reason is required (BR-24)
     * - The rejected claim returns to draft so the employee can correct and resubmit (BR-24)
     * - The rejection reason is preserved on the claim (BR-24)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to reject.
     * @param rejectClaimRequest {@link RejectClaimRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> rejectClaim(
      @NonNull String claimId,
      @NonNull RejectClaimRequest rejectClaimRequest
    ) throws ApiError {
      return this.rejectClaim(claimId, rejectClaimRequest, null);
    }

    /**
     * Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24).
     * **Approver role required.**
     *
     * **Business rules:**
     * - Claim must be in submitted status (BR-23)
     * - A rejection reason is required (BR-24)
     * - The rejected claim returns to draft so the employee can correct and resubmit (BR-24)
     * - The rejection reason is preserved on the claim (BR-24)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to reject.
     * @param rejectClaimRequest {@link RejectClaimRequest} Request Body
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> rejectClaim(
      @NonNull String claimId,
      @NonNull RejectClaimRequest rejectClaimRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(rejectClaimConfig, requestConfig);
      Request request = buildRejectClaimRequest(claimId, rejectClaimRequest, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24).
     * **Approver role required.**
     *
     * **Business rules:**
     * - Claim must be in submitted status (BR-23)
     * - A rejection reason is required (BR-24)
     * - The rejected claim returns to draft so the employee can correct and resubmit (BR-24)
     * - The rejection reason is preserved on the claim (BR-24)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to reject.
     * @param rejectClaimRequest {@link RejectClaimRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> rejectClaimAsync(
      @NonNull String claimId,
      @NonNull RejectClaimRequest rejectClaimRequest
    ) throws ApiError {
      return this.rejectClaimAsync(claimId, rejectClaimRequest, null);
    }

    /**
     * Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24).
     * **Approver role required.**
     *
     * **Business rules:**
     * - Claim must be in submitted status (BR-23)
     * - A rejection reason is required (BR-24)
     * - The rejected claim returns to draft so the employee can correct and resubmit (BR-24)
     * - The rejection reason is preserved on the claim (BR-24)
     * - Status change is recorded with actor and timestamp (BR-29)
     *
     * @param claimId String The unique identifier of the claim to reject.
     * @param rejectClaimRequest {@link RejectClaimRequest} Request Body
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> rejectClaimAsync(
      @NonNull String claimId,
      @NonNull RejectClaimRequest rejectClaimRequest,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(rejectClaimConfig, requestConfig);
      Request request = buildRejectClaimRequest(claimId, rejectClaimRequest, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }

    /**
     * Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23).
     * **Finance role required.**
     *
     * **Business rules:**
     * - Claim must be in approved status (BR-23)
     * - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27)
     * - Status change is recorded with actor and timestamp (BR-29)
     * - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred
     *
     * @param claimId String The unique identifier of the claim to mark as reimbursed.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> reimburseClaim(@NonNull String claimId)
      throws ApiError {
      return this.reimburseClaim(claimId, null);
    }

    /**
     * Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23).
     * **Finance role required.**
     *
     * **Business rules:**
     * - Claim must be in approved status (BR-23)
     * - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27)
     * - Status change is recorded with actor and timestamp (BR-29)
     * - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred
     *
     * @param claimId String The unique identifier of the claim to mark as reimbursed.
     * @return response of {@code ExpenseTrackerApiSdkResponse<Object>}
     */
    public ExpenseTrackerApiSdkResponse<Object> reimburseClaim(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(reimburseClaimConfig, requestConfig);
      Request request = buildReimburseClaimRequest(claimId, resolvedConfig);
      Response response = execute(request, resolvedConfig);
      byte[] bodyBytes = ModelConverter.readBytes(response);
      return new ExpenseTrackerApiSdkResponse<>(
        response,
        bodyBytes,
        ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
      );
    }

    /**
     * Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23).
     * **Finance role required.**
     *
     * **Business rules:**
     * - Claim must be in approved status (BR-23)
     * - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27)
     * - Status change is recorded with actor and timestamp (BR-29)
     * - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred
     *
     * @param claimId String The unique identifier of the claim to mark as reimbursed.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> reimburseClaimAsync(
      @NonNull String claimId
    ) throws ApiError {
      return this.reimburseClaimAsync(claimId, null);
    }

    /**
     * Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23).
     * **Finance role required.**
     *
     * **Business rules:**
     * - Claim must be in approved status (BR-23)
     * - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27)
     * - Status change is recorded with actor and timestamp (BR-29)
     * - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred
     *
     * @param claimId String The unique identifier of the claim to mark as reimbursed.
     * @return response of {@code CompletableFuture<ExpenseTrackerApiSdkResponse<Object>>}
     */
    public CompletableFuture<ExpenseTrackerApiSdkResponse<Object>> reimburseClaimAsync(
      @NonNull String claimId,
      RequestConfig requestConfig
    ) throws ApiError {
      RequestConfig resolvedConfig = getResolvedConfig(reimburseClaimConfig, requestConfig);
      Request request = buildReimburseClaimRequest(claimId, resolvedConfig);
      CompletableFuture<Response> futureResponse = executeAsync(request, resolvedConfig);
      return futureResponse.thenApplyAsync(response -> {
        byte[] bodyBytes = ModelConverter.readBytes(response);
        return new ExpenseTrackerApiSdkResponse<>(
          response,
          bodyBytes,
          ModelConverter.convert(bodyBytes, new TypeReference<Object>() {})
        );
      });
    }
  }
}

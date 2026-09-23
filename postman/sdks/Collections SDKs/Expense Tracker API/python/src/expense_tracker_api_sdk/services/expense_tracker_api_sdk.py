from typing import Any, Optional, Union
from .utils.validator import Validator
from .utils.base_service import BaseService
from ..net.transport.serializer import Serializer
from ..net.transport.api_error import ApiError
from ..net.sdk_config import SdkConfig
from ..net.environment.environment import Environment
from ..models.utils.sentinel import SENTINEL
from ..models.utils.cast_models import cast_models
from ..models import (
    CreateCategoryRequest,
    CreateClaimRequest,
    CreateExpenseRequest,
    RejectClaimRequest,
    UpdateCategoryRequest,
    UpdateExpenseRequest,
    UploadReceiptRequest,
)


class ExpenseTrackerApiSdkService(BaseService):
    """
    Service class for ExpenseTrackerApiSdkService operations.
    Provides methods to interact with ExpenseTrackerApiSdkService-related API endpoints.
    Inherits common functionality from BaseService including authentication and request handling.
    """

    def __init__(self, *args, **kwargs):
        """Initialize the service and method-level configurations."""
        super().__init__(*args, **kwargs)
        self._list_categories_config: SdkConfig = {}
        self._get_category_config: SdkConfig = {}
        self._create_category_config: SdkConfig = {}
        self._update_category_config: SdkConfig = {}
        self._list_expenses_config: SdkConfig = {}
        self._create_expense_config: SdkConfig = {}
        self._get_expense_config: SdkConfig = {}
        self._update_expense_config: SdkConfig = {}
        self._delete_expense_config: SdkConfig = {}
        self._list_receipts_config: SdkConfig = {}
        self._upload_receipt_config: SdkConfig = {}
        self._delete_receipt_config: SdkConfig = {}
        self._list_claims_config: SdkConfig = {}
        self._create_claim_config: SdkConfig = {}
        self._get_claim_config: SdkConfig = {}
        self._update_claim_config: SdkConfig = {}
        self._delete_claim_config: SdkConfig = {}
        self._submit_claim_config: SdkConfig = {}
        self._approve_claim_config: SdkConfig = {}
        self._reject_claim_config: SdkConfig = {}
        self._reimburse_claim_config: SdkConfig = {}

    def set_list_categories_config(self, config: SdkConfig):
        """
        Sets method-level configuration for list_categories.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._list_categories_config = config
        return self

    def set_get_category_config(self, config: SdkConfig):
        """
        Sets method-level configuration for get_category.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._get_category_config = config
        return self

    def set_create_category_config(self, config: SdkConfig):
        """
        Sets method-level configuration for create_category.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._create_category_config = config
        return self

    def set_update_category_config(self, config: SdkConfig):
        """
        Sets method-level configuration for update_category.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._update_category_config = config
        return self

    def set_list_expenses_config(self, config: SdkConfig):
        """
        Sets method-level configuration for list_expenses.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._list_expenses_config = config
        return self

    def set_create_expense_config(self, config: SdkConfig):
        """
        Sets method-level configuration for create_expense.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._create_expense_config = config
        return self

    def set_get_expense_config(self, config: SdkConfig):
        """
        Sets method-level configuration for get_expense.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._get_expense_config = config
        return self

    def set_update_expense_config(self, config: SdkConfig):
        """
        Sets method-level configuration for update_expense.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._update_expense_config = config
        return self

    def set_delete_expense_config(self, config: SdkConfig):
        """
        Sets method-level configuration for delete_expense.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._delete_expense_config = config
        return self

    def set_list_receipts_config(self, config: SdkConfig):
        """
        Sets method-level configuration for list_receipts.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._list_receipts_config = config
        return self

    def set_upload_receipt_config(self, config: SdkConfig):
        """
        Sets method-level configuration for upload_receipt.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._upload_receipt_config = config
        return self

    def set_delete_receipt_config(self, config: SdkConfig):
        """
        Sets method-level configuration for delete_receipt.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._delete_receipt_config = config
        return self

    def set_list_claims_config(self, config: SdkConfig):
        """
        Sets method-level configuration for list_claims.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._list_claims_config = config
        return self

    def set_create_claim_config(self, config: SdkConfig):
        """
        Sets method-level configuration for create_claim.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._create_claim_config = config
        return self

    def set_get_claim_config(self, config: SdkConfig):
        """
        Sets method-level configuration for get_claim.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._get_claim_config = config
        return self

    def set_update_claim_config(self, config: SdkConfig):
        """
        Sets method-level configuration for update_claim.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._update_claim_config = config
        return self

    def set_delete_claim_config(self, config: SdkConfig):
        """
        Sets method-level configuration for delete_claim.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._delete_claim_config = config
        return self

    def set_submit_claim_config(self, config: SdkConfig):
        """
        Sets method-level configuration for submit_claim.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._submit_claim_config = config
        return self

    def set_approve_claim_config(self, config: SdkConfig):
        """
        Sets method-level configuration for approve_claim.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._approve_claim_config = config
        return self

    def set_reject_claim_config(self, config: SdkConfig):
        """
        Sets method-level configuration for reject_claim.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._reject_claim_config = config
        return self

    def set_reimburse_claim_config(self, config: SdkConfig):
        """
        Sets method-level configuration for reimburse_claim.

        :param SdkConfig config: Configuration dictionary to override service-level defaults.
        :return: The service instance for method chaining.
        """
        self._reimburse_claim_config = config
        return self

    @cast_models
    def list_categories(
        self,
        include_retired: str = SENTINEL,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Any:
        """Returns all active categories available for use on new expenses. Finance may also see retired categories by passing `includeRetired=true`. **BR-06** – Any authenticated user can retrieve the category list. **BR-08** – Read-only for employees and approvers.

        :param include_retired: Set to true (Finance only) to include retired categories in the response., defaults to None
        :type include_retired: str, optional
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(str).is_optional().validate(include_retired, "include_retired")

        resolved_config = self._get_resolved_config(
            self._list_categories_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/categories",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_query("includeRetired", include_retired, nullable=True)
            .serialize()
            .set_method("GET")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def get_category(
        self, category_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Any:
        """Returns a single category by its ID, including whether it is active or retired.

        :param category_id: The unique identifier of the category.
        :type category_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(str).validate(category_id, "category_id")

        resolved_config = self._get_resolved_config(
            self._get_category_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/categories/{{categoryId}}",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("categoryId", category_id)
            .serialize()
            .set_method("GET")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def create_category(
        self,
        request_body: CreateCategoryRequest,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Any:
        """Creates a new expense category. **Finance role required** (BR-07, BR-08). The `receiptThreshold` is the amount above which a receipt becomes mandatory. Set `requiresReceiptAlways: true` to mandate a receipt at any amount. Set `requiresClientReference: true` for categories like Client Entertainment (BR-20).

        :param request_body: The request body.
        :type request_body: CreateCategoryRequest
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(CreateCategoryRequest).is_nullable().validate(
            request_body, "request_body"
        )

        resolved_config = self._get_resolved_config(
            self._create_category_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/categories",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .serialize()
            .set_method("POST")
            .set_body(request_body)
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def update_category(
        self,
        request_body: UpdateCategoryRequest,
        category_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Union[Any, Any]:
        """Updates an existing category's name, receipt threshold, or client reference requirement. **Finance role required** (BR-07, BR-08). Retiring a category (setting `active: false`) does not affect historic expenses (BR-30).

        :param request_body: The request body.
        :type request_body: UpdateCategoryRequest
        :param category_id: The unique identifier of the category to update.
        :type category_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Union[Any, Any]
        """

        Validator(UpdateCategoryRequest).is_nullable().validate(
            request_body, "request_body"
        )
        Validator(str).validate(category_id, "category_id")

        resolved_config = self._get_resolved_config(
            self._update_category_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/categories/{{categoryId}}",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("categoryId", category_id)
            .serialize()
            .set_method("PATCH")
            .set_body(request_body)
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def list_expenses(
        self,
        date_from: str = SENTINEL,
        date_to: str = SENTINEL,
        category_id: str = SENTINEL,
        claimed: str = SENTINEL,
        page: str = SENTINEL,
        page_size: str = SENTINEL,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Union[Any, Any]:
        """Returns a paginated list of expenses for the authenticated employee. Finance may retrieve expenses across all employees for reporting (BR-14). Supports filtering by date range, category, and claimed status (BR-03). Results are paginated (NFR-05).

        :param date_from: Filter expenses incurred on or after this date (ISO 8601)., defaults to None
        :type date_from: str, optional
        :param date_to: Filter expenses incurred on or before this date (ISO 8601)., defaults to None
        :type date_to: str, optional
        :param category_id: Filter by category ID., defaults to None
        :type category_id: str, optional
        :param claimed: Filter by whether the expense has been added to a claim. true | false, defaults to None
        :type claimed: str, optional
        :param page: Page number (1-based)., defaults to None
        :type page: str, optional
        :param page_size: Number of results per page. Max 100., defaults to None
        :type page_size: str, optional
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Union[Any, Any]
        """

        Validator(str).is_optional().validate(date_from, "date_from")
        Validator(str).is_optional().validate(date_to, "date_to")
        Validator(str).is_optional().validate(category_id, "category_id")
        Validator(str).is_optional().validate(claimed, "claimed")
        Validator(str).is_optional().validate(page, "page")
        Validator(str).is_optional().validate(page_size, "page_size")

        resolved_config = self._get_resolved_config(
            self._list_expenses_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/expenses",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_query("dateFrom", date_from, nullable=True)
            .add_query("dateTo", date_to, nullable=True)
            .add_query("categoryId", category_id, nullable=True)
            .add_query("claimed", claimed, nullable=True)
            .add_query("page", page, nullable=True)
            .add_query("pageSize", page_size, nullable=True)
            .serialize()
            .set_method("GET")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def create_expense(
        self,
        request_body: CreateExpenseRequest,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Any:
        """Records a new expense for the authenticated employee (BR-01). **Business rules:** - Amount must be greater than zero (BR-16) - Date incurred must not be in the future (BR-17) - Category must be an active category (BR-06) - For non-EUR currencies, the EUR equivalent is calculated using the exchange rate on the date incurred (BR-28) - Client Entertainment requires a client reference (BR-20)

        :param request_body: The request body.
        :type request_body: CreateExpenseRequest
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(CreateExpenseRequest).is_nullable().validate(
            request_body, "request_body"
        )

        resolved_config = self._get_resolved_config(
            self._create_expense_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/expenses",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .serialize()
            .set_method("POST")
            .set_body(request_body)
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def get_expense(
        self, expense_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Any:
        """Returns a single expense by ID. Employees can only retrieve their own expenses (BR-05). Finance can retrieve any expense.

        :param expense_id: The unique identifier of the expense.
        :type expense_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(str).validate(expense_id, "expense_id")

        resolved_config = self._get_resolved_config(
            self._get_expense_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/expenses/{{expenseId}}",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("expenseId", expense_id)
            .serialize()
            .set_method("GET")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def update_expense(
        self,
        request_body: UpdateExpenseRequest,
        expense_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Any:
        """Amends an existing expense. Only the owner may update it (BR-02, BR-05). **Business rules:** - Cannot amend an expense that is part of a submitted, approved, or reimbursed claim (BR-25) - Amount must remain greater than zero (BR-16) - Date incurred must not be in the future (BR-17)

        :param request_body: The request body.
        :type request_body: UpdateExpenseRequest
        :param expense_id: The unique identifier of the expense to update.
        :type expense_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(UpdateExpenseRequest).is_nullable().validate(
            request_body, "request_body"
        )
        Validator(str).validate(expense_id, "expense_id")

        resolved_config = self._get_resolved_config(
            self._update_expense_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/expenses/{{expenseId}}",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("expenseId", expense_id)
            .serialize()
            .set_method("PATCH")
            .set_body(request_body)
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def delete_expense(
        self, expense_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> str:
        """Deletes an expense. Only the owner may delete it (BR-02, BR-05). **Business rules:** - Cannot delete an expense that is part of a submitted, approved, or reimbursed claim (BR-25)

        :param expense_id: The unique identifier of the expense to delete.
        :type expense_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: str
        """

        Validator(str).validate(expense_id, "expense_id")

        resolved_config = self._get_resolved_config(
            self._delete_expense_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/expenses/{{expenseId}}",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("expenseId", expense_id)
            .serialize()
            .set_method("DELETE")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def list_receipts(
        self, expense_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Union[Any, Any]:
        """Returns all receipt attachments for a given expense. Only the expense owner (or Finance) may access receipts (BR-05). **NFR-07** – Receipts may be JPEG, PNG, or PDF, up to 10 MB each. Max 10 per expense.

        :param expense_id: The unique identifier of the expense.
        :type expense_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Union[Any, Any]
        """

        Validator(str).validate(expense_id, "expense_id")

        resolved_config = self._get_resolved_config(
            self._list_receipts_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/expenses/{{expenseId}}/receipts",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("expenseId", expense_id)
            .serialize()
            .set_method("GET")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def upload_receipt(
        self,
        request_body: UploadReceiptRequest,
        expense_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Any:
        """Attaches a receipt image to an expense (BR-04). **Business rules:** - Accepted formats: JPEG, PNG, PDF (NFR-07) - Maximum file size: 10 MB per receipt (NFR-07) - Maximum 10 receipts per expense (NFR-07) - A receipt is mandatory when the expense amount exceeds the category receipt threshold (BR-19) - Client Entertainment always requires a receipt (BR-20) - Only the expense owner may attach receipts (BR-05)

        :param request_body: The request body.
        :type request_body: UploadReceiptRequest
        :param expense_id: The unique identifier of the expense.
        :type expense_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(UploadReceiptRequest).is_nullable().validate(
            request_body, "request_body"
        )
        Validator(str).validate(expense_id, "expense_id")

        resolved_config = self._get_resolved_config(
            self._upload_receipt_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/expenses/{{expenseId}}/receipts",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("expenseId", expense_id)
            .serialize()
            .set_method("POST")
            .set_body(request_body, "multipart/form-data")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def delete_receipt(
        self,
        expense_id: str,
        receipt_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Any:
        """Removes a receipt attachment from an expense (BR-04). **Business rules:** - Only the expense owner may remove receipts (BR-05) - Cannot remove a receipt from an expense in a submitted, approved, or reimbursed claim (BR-25)

        :param expense_id: The unique identifier of the expense.
        :type expense_id: str
        :param receipt_id: The unique identifier of the receipt to delete.
        :type receipt_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(str).validate(expense_id, "expense_id")
        Validator(str).validate(receipt_id, "receipt_id")

        resolved_config = self._get_resolved_config(
            self._delete_receipt_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/expenses/{{expenseId}}/receipts/{{receiptId}}",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("expenseId", expense_id)
            .add_path("receiptId", receipt_id)
            .serialize()
            .set_method("DELETE")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def list_claims(
        self,
        status: str = SENTINEL,
        page: str = SENTINEL,
        page_size: str = SENTINEL,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Union[Any, Any]:
        """Returns a paginated list of claims. - Employees see only their own claims (BR-10) - Approvers see claims submitted to them (BR-11) - Finance sees all claims (BR-14) Supports filtering by status. Results are paginated (NFR-05).

        :param status: Filter by claim status: draft | submitted | approved | rejected | reimbursed, defaults to None
        :type status: str, optional
        :param page: Page number (1-based)., defaults to None
        :type page: str, optional
        :param page_size: Number of results per page. Max 100., defaults to None
        :type page_size: str, optional
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Union[Any, Any]
        """

        Validator(str).is_optional().validate(status, "status")
        Validator(str).is_optional().validate(page, "page")
        Validator(str).is_optional().validate(page_size, "page_size")

        resolved_config = self._get_resolved_config(
            self._list_claims_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/claims",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_query("status", status, nullable=True)
            .add_query("page", page, nullable=True)
            .add_query("pageSize", page_size, nullable=True)
            .serialize()
            .set_method("GET")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def create_claim(
        self,
        request_body: CreateClaimRequest,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Any:
        """Creates a new claim in draft status, grouping one or more unclaimed expenses (BR-09). **Business rules:** - A claim must contain at least one expense (BR-21) - Each expense may belong to at most one claim (BR-22) - Expenses incurred more than 90 days ago cannot be added to a claim (BR-18) - All expenses must belong to the authenticated employee

        :param request_body: The request body.
        :type request_body: CreateClaimRequest
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(CreateClaimRequest).is_nullable().validate(
            request_body, "request_body"
        )

        resolved_config = self._get_resolved_config(
            self._create_claim_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/claims",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .serialize()
            .set_method("POST")
            .set_body(request_body)
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def get_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Union[Any, Any]:
        """Returns a single claim by ID, including all its expenses and receipts (BR-11). - Employees can only see their own claims (BR-10) - Approvers can see claims submitted to them (BR-11) - Finance can see all claims (BR-14)

        :param claim_id: The unique identifier of the claim.
        :type claim_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Union[Any, Any]
        """

        Validator(str).validate(claim_id, "claim_id")

        resolved_config = self._get_resolved_config(
            self._get_claim_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/claims/{{claimId}}",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("claimId", claim_id)
            .serialize()
            .set_method("GET")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def update_claim(
        self,
        request_body: CreateClaimRequest,
        claim_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Any:
        """Updates a claim's title or expense list while it is in draft status. Only the claim owner may update it. **Business rules:** - Can only update a claim in draft status - Expenses added must be unclaimed and belong to the owner (BR-22) - Expenses incurred more than 90 days ago cannot be added (BR-18) - A claim must retain at least one expense (BR-21)

        :param request_body: The request body.
        :type request_body: CreateClaimRequest
        :param claim_id: The unique identifier of the claim to update.
        :type claim_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(CreateClaimRequest).is_nullable().validate(
            request_body, "request_body"
        )
        Validator(str).validate(claim_id, "claim_id")

        resolved_config = self._get_resolved_config(
            self._update_claim_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/claims/{{claimId}}",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("claimId", claim_id)
            .serialize()
            .set_method("PATCH")
            .set_body(request_body)
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def delete_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> str:
        """Deletes a claim in draft status. Only the claim owner may delete it. **Business rules:** - Only draft claims can be deleted - Reimbursed claims are final and cannot be deleted (BR-27) - Deleting a claim releases its expenses back to unclaimed status

        :param claim_id: The unique identifier of the claim to delete.
        :type claim_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: str
        """

        Validator(str).validate(claim_id, "claim_id")

        resolved_config = self._get_resolved_config(
            self._delete_claim_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/claims/{{claimId}}",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("claimId", claim_id)
            .serialize()
            .set_method("DELETE")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def submit_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Any:
        """Submits a draft claim for approval, transitioning it from `draft` → `submitted` (BR-09, BR-23). **Business rules enforced on submission:** - Claim must be in draft status (BR-23) - Claim must contain at least one expense (BR-21) - Any expense requiring a receipt (amount > category threshold, or Client Entertainment) must have at least one receipt attached (BR-19, BR-20) - No expense in the claim may be older than 90 days (BR-18) - Status change is recorded with actor and timestamp (BR-29)

        :param claim_id: The unique identifier of the claim to submit.
        :type claim_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(str).validate(claim_id, "claim_id")

        resolved_config = self._get_resolved_config(
            self._submit_claim_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/claims/{{claimId}}/submit",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("claimId", claim_id)
            .serialize()
            .set_method("POST")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def approve_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Any:
        """Approves a submitted claim, transitioning it from `submitted` → `approved` (BR-12, BR-23). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - An approver cannot approve a claim they submitted themselves (BR-26) - Status change is recorded with actor and timestamp (BR-29)

        :param claim_id: The unique identifier of the claim to approve.
        :type claim_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(str).validate(claim_id, "claim_id")

        resolved_config = self._get_resolved_config(
            self._approve_claim_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/claims/{{claimId}}/approve",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("claimId", claim_id)
            .serialize()
            .set_method("POST")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def reject_claim(
        self,
        request_body: RejectClaimRequest,
        claim_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Any:
        """Rejects a submitted claim with a mandatory reason, transitioning it from `submitted` → `rejected` → `draft` (BR-12, BR-23, BR-24). **Approver role required.** **Business rules:** - Claim must be in submitted status (BR-23) - A rejection reason is required (BR-24) - The rejected claim returns to draft so the employee can correct and resubmit (BR-24) - The rejection reason is preserved on the claim (BR-24) - Status change is recorded with actor and timestamp (BR-29)

        :param request_body: The request body.
        :type request_body: RejectClaimRequest
        :param claim_id: The unique identifier of the claim to reject.
        :type claim_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(RejectClaimRequest).is_nullable().validate(
            request_body, "request_body"
        )
        Validator(str).validate(claim_id, "claim_id")

        resolved_config = self._get_resolved_config(
            self._reject_claim_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/claims/{{claimId}}/reject",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("claimId", claim_id)
            .serialize()
            .set_method("POST")
            .set_body(request_body)
        )

        response, status, _ = self.send_request(serialized_request)
        return response

    @cast_models
    def reimburse_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Any:
        """Marks an approved claim as reimbursed, transitioning it from `approved` → `reimbursed` (BR-13, BR-23). **Finance role required.** **Business rules:** - Claim must be in approved status (BR-23) - A reimbursed claim is final — it cannot be reopened, amended, or deleted (BR-27) - Status change is recorded with actor and timestamp (BR-29) - Note: actual payment is handled by payroll; this endpoint records that reimbursement has occurred

        :param claim_id: The unique identifier of the claim to mark as reimbursed.
        :type claim_id: str
        ...
        :raises RequestError: Raised when a request fails, with optional HTTP status code and details.
        ...
        :return: The parsed response data.
        :rtype: Any
        """

        Validator(str).validate(claim_id, "claim_id")

        resolved_config = self._get_resolved_config(
            self._reimburse_claim_config, request_config
        )

        serialized_request = (
            Serializer(
                f"{self._resolve_base_url(resolved_config) or Environment.DEFAULT.url}/claims/{{claimId}}/reimburse",
                [self.get_access_token(resolved_config)],
                resolved_config,
            )
            .add_path("claimId", claim_id)
            .serialize()
            .set_method("POST")
        )

        response, status, _ = self.send_request(serialized_request)
        return response

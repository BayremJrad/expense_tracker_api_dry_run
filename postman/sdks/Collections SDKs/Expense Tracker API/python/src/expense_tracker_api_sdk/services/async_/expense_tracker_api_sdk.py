from typing import Awaitable, Optional, Any, Union
from .utils.to_async import to_async
from ..expense_tracker_api_sdk import ExpenseTrackerApiSdkService
from ...net.sdk_config import SdkConfig
from ...models.utils.sentinel import SENTINEL
from ...models import (
    CreateCategoryRequest,
    UpdateCategoryRequest,
    CreateExpenseRequest,
    UpdateExpenseRequest,
    UploadReceiptRequest,
    CreateClaimRequest,
    RejectClaimRequest,
)


class ExpenseTrackerApiSdkServiceAsync(ExpenseTrackerApiSdkService):
    """
    Async Wrapper for ExpenseTrackerApiSdkServiceAsync
    """

    def list_categories(
        self,
        include_retired: str = SENTINEL,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Any]:
        return to_async(super().list_categories)(
            **{
                k: v
                for k, v in {"include_retired": include_retired}.items()
                if v is not SENTINEL
            },
            request_config=request_config,
        )

    def get_category(
        self, category_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[Any]:
        return to_async(super().get_category)(
            category_id, request_config=request_config
        )

    def create_category(
        self,
        request_body: CreateCategoryRequest,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Any]:
        return to_async(super().create_category)(
            request_body, request_config=request_config
        )

    def update_category(
        self,
        request_body: UpdateCategoryRequest,
        category_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Union[Any, Any]]:
        return to_async(super().update_category)(
            request_body, category_id, request_config=request_config
        )

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
    ) -> Awaitable[Union[Any, Any]]:
        return to_async(super().list_expenses)(
            **{
                k: v
                for k, v in {
                    "date_from": date_from,
                    "date_to": date_to,
                    "category_id": category_id,
                    "claimed": claimed,
                    "page": page,
                    "page_size": page_size,
                }.items()
                if v is not SENTINEL
            },
            request_config=request_config,
        )

    def create_expense(
        self,
        request_body: CreateExpenseRequest,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Any]:
        return to_async(super().create_expense)(
            request_body, request_config=request_config
        )

    def get_expense(
        self, expense_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[Any]:
        return to_async(super().get_expense)(expense_id, request_config=request_config)

    def update_expense(
        self,
        request_body: UpdateExpenseRequest,
        expense_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Any]:
        return to_async(super().update_expense)(
            request_body, expense_id, request_config=request_config
        )

    def delete_expense(
        self, expense_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[str]:
        return to_async(super().delete_expense)(
            expense_id, request_config=request_config
        )

    def list_receipts(
        self, expense_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[Union[Any, Any]]:
        return to_async(super().list_receipts)(
            expense_id, request_config=request_config
        )

    def upload_receipt(
        self,
        request_body: UploadReceiptRequest,
        expense_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Any]:
        return to_async(super().upload_receipt)(
            request_body, expense_id, request_config=request_config
        )

    def delete_receipt(
        self,
        expense_id: str,
        receipt_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Any]:
        return to_async(super().delete_receipt)(
            expense_id, receipt_id, request_config=request_config
        )

    def list_claims(
        self,
        status: str = SENTINEL,
        page: str = SENTINEL,
        page_size: str = SENTINEL,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Union[Any, Any]]:
        return to_async(super().list_claims)(
            **{
                k: v
                for k, v in {
                    "status": status,
                    "page": page,
                    "page_size": page_size,
                }.items()
                if v is not SENTINEL
            },
            request_config=request_config,
        )

    def create_claim(
        self,
        request_body: CreateClaimRequest,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Any]:
        return to_async(super().create_claim)(
            request_body, request_config=request_config
        )

    def get_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[Union[Any, Any]]:
        return to_async(super().get_claim)(claim_id, request_config=request_config)

    def update_claim(
        self,
        request_body: CreateClaimRequest,
        claim_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Any]:
        return to_async(super().update_claim)(
            request_body, claim_id, request_config=request_config
        )

    def delete_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[str]:
        return to_async(super().delete_claim)(claim_id, request_config=request_config)

    def submit_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[Any]:
        return to_async(super().submit_claim)(claim_id, request_config=request_config)

    def approve_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[Any]:
        return to_async(super().approve_claim)(claim_id, request_config=request_config)

    def reject_claim(
        self,
        request_body: RejectClaimRequest,
        claim_id: str,
        *,
        request_config: Optional[SdkConfig] = None,
    ) -> Awaitable[Any]:
        return to_async(super().reject_claim)(
            request_body, claim_id, request_config=request_config
        )

    def reimburse_claim(
        self, claim_id: str, *, request_config: Optional[SdkConfig] = None
    ) -> Awaitable[Any]:
        return to_async(super().reimburse_claim)(
            claim_id, request_config=request_config
        )

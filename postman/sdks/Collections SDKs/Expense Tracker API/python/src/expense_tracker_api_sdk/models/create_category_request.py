from __future__ import annotations
from pydantic import Field
from typing import Optional
from typing import Any
from typing import Union
from .utils.base_model import BaseModel


class CreateCategoryRequest(BaseModel):
    """CreateCategoryRequest

    :param name: name, defaults to None
    :type name: str, optional
    :param receipt_threshold: receipt_threshold, defaults to None
    :type receipt_threshold: float, optional
    :param requires_receipt_always: requires_receipt_always, defaults to None
    :type requires_receipt_always: bool, optional
    :param requires_client_reference: requires_client_reference, defaults to None
    :type requires_client_reference: bool, optional
    """

    name: Optional[str] = Field(default=None)
    receipt_threshold: Optional[float] = Field(
        alias="receiptThreshold", serialization_alias="receiptThreshold", default=None
    )
    requires_receipt_always: Optional[bool] = Field(
        alias="requiresReceiptAlways",
        serialization_alias="requiresReceiptAlways",
        default=None,
    )
    requires_client_reference: Optional[bool] = Field(
        alias="requiresClientReference",
        serialization_alias="requiresClientReference",
        default=None,
    )

    def model_dump_original(self, **kwargs):
        result = super().model_dump_original(**kwargs)
        extra_fields = self.model_extra or {}
        return {**extra_fields, **result}

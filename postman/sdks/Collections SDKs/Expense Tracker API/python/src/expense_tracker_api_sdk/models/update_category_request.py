from __future__ import annotations
from pydantic import Field
from typing import Optional
from typing import Any
from typing import Union
from .utils.base_model import BaseModel


class UpdateCategoryRequest(BaseModel):
    """UpdateCategoryRequest

    :param receipt_threshold: receipt_threshold, defaults to None
    :type receipt_threshold: int, optional
    """

    receipt_threshold: Optional[int] = Field(
        alias="receiptThreshold", serialization_alias="receiptThreshold", default=None
    )

    def model_dump_original(self, **kwargs):
        result = super().model_dump_original(**kwargs)
        extra_fields = self.model_extra or {}
        return {**extra_fields, **result}

from __future__ import annotations
from typing import List
from pydantic import Field
from typing import Optional
from typing import Any
from typing import Union
from .utils.base_model import BaseModel


class CreateClaimRequest(BaseModel):
    """CreateClaimRequest

    :param title: title, defaults to None
    :type title: str, optional
    :param expense_ids: expense_ids, defaults to None
    :type expense_ids: List[str], optional
    """

    title: Optional[str] = Field(default=None)
    expense_ids: Optional[List[str]] = Field(
        alias="expenseIds", serialization_alias="expenseIds", default=None
    )

    def model_dump_original(self, **kwargs):
        result = super().model_dump_original(**kwargs)
        extra_fields = self.model_extra or {}
        return {**extra_fields, **result}

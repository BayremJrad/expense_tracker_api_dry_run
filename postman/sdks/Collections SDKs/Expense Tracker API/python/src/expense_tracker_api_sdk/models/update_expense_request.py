from __future__ import annotations
from pydantic import Field
from typing import Optional
from typing import Any
from typing import Union
from .utils.base_model import BaseModel


class UpdateExpenseRequest(BaseModel):
    """UpdateExpenseRequest

    :param description: description, defaults to None
    :type description: str, optional
    :param amount: amount, defaults to None
    :type amount: int, optional
    """

    description: Optional[str] = Field(default=None)
    amount: Optional[int] = Field(default=None)

    def model_dump_original(self, **kwargs):
        result = super().model_dump_original(**kwargs)
        extra_fields = self.model_extra or {}
        return {**extra_fields, **result}

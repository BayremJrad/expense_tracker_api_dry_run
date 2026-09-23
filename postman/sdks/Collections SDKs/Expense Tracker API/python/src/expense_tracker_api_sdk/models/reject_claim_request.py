from __future__ import annotations
from pydantic import Field
from typing import Optional
from typing import Any
from typing import Union
from .utils.base_model import BaseModel


class RejectClaimRequest(BaseModel):
    """RejectClaimRequest

    :param reason: reason, defaults to None
    :type reason: str, optional
    """

    reason: Optional[str] = Field(default=None)

    def model_dump_original(self, **kwargs):
        result = super().model_dump_original(**kwargs)
        extra_fields = self.model_extra or {}
        return {**extra_fields, **result}

from __future__ import annotations
from pydantic import Field
from typing import Optional
from typing import Any
from typing import Union
from .utils.base_model import BaseModel


class CreateExpenseRequest(BaseModel):
    """CreateExpenseRequest

    :param amount: amount, defaults to None
    :type amount: float, optional
    :param currency: currency, defaults to None
    :type currency: str, optional
    :param date_incurred: date_incurred, defaults to None
    :type date_incurred: str, optional
    :param category_id: category_id, defaults to None
    :type category_id: str, optional
    :param description: description, defaults to None
    :type description: str, optional
    :param payment_method: payment_method, defaults to None
    :type payment_method: str, optional
    :param client_reference: client_reference, defaults to None
    :type client_reference: Any, optional
    """

    amount: Optional[float] = Field(default=None)
    currency: Optional[str] = Field(default=None)
    date_incurred: Optional[str] = Field(
        alias="dateIncurred", serialization_alias="dateIncurred", default=None
    )
    category_id: Optional[str] = Field(
        alias="categoryId", serialization_alias="categoryId", default=None
    )
    description: Optional[str] = Field(default=None)
    payment_method: Optional[str] = Field(
        alias="paymentMethod", serialization_alias="paymentMethod", default=None
    )
    client_reference: Optional[Any] = Field(
        alias="clientReference", serialization_alias="clientReference", default=None
    )

    def model_dump_original(self, **kwargs):
        result = super().model_dump_original(**kwargs)
        extra_fields = self.model_extra or {}
        return {**extra_fields, **result}

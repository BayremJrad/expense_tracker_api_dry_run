from __future__ import annotations
from pydantic import Field
from typing import Optional
from typing import Any
from typing import Union
from .utils.base_model import BaseModel


class UploadReceiptRequest(BaseModel):
    """UploadReceiptRequest

    :param file: The receipt image file. Accepted: JPEG, PNG, PDF. Max 10 MB., defaults to None
    :type file: bytes, optional
    :param filename: Optional display filename override., defaults to None
    :type filename: str, optional
    """

    file: Optional[bytes] = Field(
        default=None,
        description="The receipt image file. Accepted: JPEG, PNG, PDF. Max 10 MB.",
    )
    filename: Optional[str] = Field(
        default=None, description="Optional display filename override."
    )

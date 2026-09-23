from typing import Union
from .net.environment import Environment
from .sdk import ExpenseTrackerApiSdk
from .services.async_.expense_tracker_api_sdk import ExpenseTrackerApiSdkServiceAsync


class ExpenseTrackerApiSdkAsync(ExpenseTrackerApiSdk):
    """
    ExpenseTrackerApiSdkAsync is the asynchronous version of the ExpenseTrackerApiSdk SDK Client.
    """

    def __init__(
        self,
        *,
        access_token: str = None,
        base_url: Union[Environment, str, None] = None,
        timeout: float = None,
        timeout_ms: int = None,
        retry: "RetryConfig" = None,
    ):
        super().__init__(
            access_token=access_token,
            base_url=base_url,
            timeout=timeout,
            timeout_ms=timeout_ms,
            retry=retry,
        )

        self.expense_tracker_api_sdk = ExpenseTrackerApiSdkServiceAsync(
            base_url=self._base_url
        )
        if retry is not None:
            self.set_retry(retry)

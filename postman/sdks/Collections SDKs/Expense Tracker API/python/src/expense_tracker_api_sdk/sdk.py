import warnings
from typing import Union
from .services.expense_tracker_api_sdk import ExpenseTrackerApiSdkService
from .net.environment import Environment


class ExpenseTrackerApiSdk:
    """
    Main SDK client class for ExpenseTrackerApiSdk.
    Provides centralized configuration and access to all service endpoints.
    Supports authentication, environment management, and global timeout settings.
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
        """
        Initializes ExpenseTrackerApiSdk the SDK class.
        """

        _resolved_url = (
            base_url.value if isinstance(base_url, Environment) else base_url
        )
        self._base_url = _resolved_url.rstrip("/") if _resolved_url else _resolved_url
        self.expense_tracker_api_sdk = ExpenseTrackerApiSdkService(
            base_url=self._base_url
        )
        self.set_access_token(access_token)
        if timeout_ms is not None:
            warnings.warn(
                "`timeout_ms` is deprecated; use `timeout` (in seconds) instead.",
                DeprecationWarning,
                stacklevel=2,
            )
            timeout = timeout_ms / 1000 if timeout is None else timeout
        if timeout is None:
            timeout = 60
        self.set_timeout(timeout)
        if retry is not None:
            self.set_retry(retry)

    def set_base_url(self, base_url: Union[Environment, str]):
        """
        Sets the base URL for the entire SDK.

        :param Union[Environment, str] base_url: The base URL to be set.
        :return: The SDK instance.
        """
        _resolved_url = (
            base_url.value if isinstance(base_url, Environment) else base_url
        )
        self._base_url = _resolved_url.rstrip("/") if _resolved_url else _resolved_url

        self.expense_tracker_api_sdk.set_base_url(self._base_url)

        return self

    def set_access_token(self, access_token: str):
        """
        Sets the access token for the entire SDK.
        """
        self.expense_tracker_api_sdk.set_access_token(access_token)

        return self

    def set_timeout(self, timeout: float):
        """
        Sets the timeout for the entire SDK.

        :param float timeout: The timeout (in seconds) to be set.
        :return: The SDK instance.
        """
        self.expense_tracker_api_sdk.set_timeout(timeout)

        return self

    def set_retry(self, retry: "RetryConfig"):
        """
        Sets the retry configuration for the entire SDK.

        :param RetryConfig retry: The retry configuration to be set.
        :return: The SDK instance.
        """
        self.expense_tracker_api_sdk.set_retry(retry)

        return self


# c029837e0e474b76bc487506e8799df5e3335891efe4fb02bda7a1441840310c

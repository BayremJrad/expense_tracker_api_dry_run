from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN", base_url=Environment.DEFAULT.value, timeout=10
)

result = sdk.expense_tracker_api_sdk.list_categories(include_retired="false")

print(result)

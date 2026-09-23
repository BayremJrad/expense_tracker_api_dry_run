# ExpenseTrackerApiSdk Python SDK 1.0.0

Welcome to the ExpenseTrackerApiSdk SDK documentation. This guide will help you get started with integrating and using the ExpenseTrackerApiSdk SDK in your project.

## Versions

- SDK version: `1.0.0`

## About the API

API for Northbeam Consulting's Expense Tracker service. Allows employees to record expenses, attach receipts, group them into claims, and submit for approval. Approvers can review and approve/reject claims. Finance can manage categories and mark claims as reimbursed.

**Roles:**

- **Employee** – records expenses, attaches receipts, creates and submits claims
- **Approver** – reviews and approves/rejects submitted claims
- **Finance** – manages categories, marks claims as reimbursed, runs reports

**Base URL:** `{{baseUrl}}`

**Authentication:** Bearer token required on all endpoints (NFR-01).

## Table of Contents

- [Setup & Configuration](#setup--configuration)
  - [Supported Language Versions](#supported-language-versions)
  - [Installation](#installation)
- [Authentication](#authentication)
  - [Access Token Authentication](#access-token-authentication)
- [Setting a Custom Timeout](#setting-a-custom-timeout)
- [Sample Usage](#sample-usage)
- [Async Usage](#async-usage)
- [Services](#services)
- [Models](#models)

# Setup & Configuration

## Supported Language Versions

This SDK is compatible with the following versions: `Python >= 3.9`

## Installation

To get started with the SDK, we recommend installing using `pip`:

```bash
pip install expense_tracker_api_sdk
```

If you are using Python 3, you can use `pip3` instead:

```bash
pip3 install expense_tracker_api_sdk
```

## Authentication

### Access Token Authentication

The ExpenseTrackerApiSdk API uses an Access Token for authentication.

This token must be provided to authenticate your requests to the API.

#### Setting the Access Token

When you initialize the SDK, you can set the access token as follows:

```py
ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    timeout=10
)
```

If you need to set or update the access token after initializing the SDK, you can use:

```py
sdk.set_access_token("YOUR_ACCESS_TOKEN")
```

## Setting a Custom Timeout

You can set a custom timeout for the SDK's HTTP requests as follows:

```py
from expense_tracker_api_sdk import ExpenseTrackerApiSdk

sdk = ExpenseTrackerApiSdk(timeout=10)
```

# Sample Usage

Below is a comprehensive example demonstrating how to authenticate and call a simple endpoint:

```py
from expense_tracker_api_sdk import ExpenseTrackerApiSdk, Environment

sdk = ExpenseTrackerApiSdk(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)

result = sdk.expense_tracker_api_sdk.list_categories(include_retired="false")

print(result)

```

# Async Usage

The SDK includes an Async Client for making asynchronous API requests. This is useful for applications that need non-blocking operations, like web servers or apps with a graphical user interface.

```py
import asyncio
from expense_tracker_api_sdk import ExpenseTrackerApiSdkAsync, Environment

sdk = ExpenseTrackerApiSdkAsync(
    access_token="YOUR_ACCESS_TOKEN",
    base_url=Environment.DEFAULT.value,
    timeout=10
)


async def main():
  result = await sdk.expense_tracker_api_sdk.list_categories(include_retired="false")
  print(result)

asyncio.run(main())
```

## Services

The SDK provides various services to interact with the API.

<details>
<summary>Below is a list of all available services with links to their detailed documentation:</summary>

| Name                                                                                 |
| :----------------------------------------------------------------------------------- |
| [ExpenseTrackerApiSdkService](documentation/services/ExpenseTrackerApiSdkService.md) |

</details>

## Models

The SDK includes several models that represent the data structures used in API requests and responses. These models help in organizing and managing the data efficiently.

<details>
<summary>Below is a list of all available models with links to their detailed documentation:</summary>

| Name                                                                   | Description |
| :--------------------------------------------------------------------- | :---------- |
| [CreateCategoryRequest](documentation/models/CreateCategoryRequest.md) |             |
| [UpdateCategoryRequest](documentation/models/UpdateCategoryRequest.md) |             |
| [CreateExpenseRequest](documentation/models/CreateExpenseRequest.md)   |             |
| [UpdateExpenseRequest](documentation/models/UpdateExpenseRequest.md)   |             |
| [UploadReceiptRequest](documentation/models/UploadReceiptRequest.md)   |             |
| [CreateClaimRequest](documentation/models/CreateClaimRequest.md)       |             |
| [RejectClaimRequest](documentation/models/RejectClaimRequest.md)       |             |

</details>

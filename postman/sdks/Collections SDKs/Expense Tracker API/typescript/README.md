# ExpenseTrackerApiSdk TypeScript SDK 1.0.0

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
- [Services](#services)
- [Models](#models)

# Setup & Configuration

## Supported Language Versions

This SDK is compatible with the following versions: `TypeScript >= 4.8.4`

## Installation

To get started with the SDK, we recommend installing using `npm` or `yarn`:

```bash
npm install expense-tracker-api-sdk
```

or

```bash
yarn add expense-tracker-api-sdk
```

## Authentication

### Access Token Authentication

The ExpenseTrackerApiSdk API uses an Access Token for authentication.

This token must be provided to authenticate your requests to the API.

#### Setting the Access Token

When you initialize the SDK, you can set the access token as follows:

```ts
const sdk = new ExpenseTrackerApiSdk({ token: 'YOUR_TOKEN' });
```

If you need to set or update the access token after initializing the SDK, you can use:

```ts
const sdk = new ExpenseTrackerApiSdk();
sdk.token = 'YOUR_TOKEN';
```

## Setting a Custom Timeout

You can set a custom timeout for the SDK's HTTP requests as follows:

```ts
const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({ timeout: 10000 });
```

# Sample Usage

Below is a comprehensive example demonstrating how to authenticate and call a simple endpoint:

```ts
import { ExpenseTrackerApiSdk } from 'expense-tracker-api-sdk';

(async () => {
  const expenseTrackerApiSdk = new ExpenseTrackerApiSdk({
    token: 'YOUR_TOKEN',
  });

  const data = await expenseTrackerApiSdk.expenseTrackerApiSdk.listCategories({
    includeRetired: 'false',
  });

  console.log(data);
})();
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

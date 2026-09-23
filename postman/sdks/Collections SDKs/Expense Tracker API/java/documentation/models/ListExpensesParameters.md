# ListExpensesParameters

**Properties**

| Name       | Type   | Required | Description                                                            |
| :--------- | :----- | :------- | :--------------------------------------------------------------------- |
| dateFrom   | String | ❌       | Filter expenses incurred on or after this date (ISO 8601).             |
| dateTo     | String | ❌       | Filter expenses incurred on or before this date (ISO 8601).            |
| categoryId | String | ❌       | Filter by category ID.                                                 |
| claimed    | String | ❌       | Filter by whether the expense has been added to a claim. true \| false |
| page       | String | ❌       | Page number (1-based).                                                 |
| pageSize   | String | ❌       | Number of results per page. Max 100.                                   |

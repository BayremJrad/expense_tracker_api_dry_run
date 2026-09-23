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

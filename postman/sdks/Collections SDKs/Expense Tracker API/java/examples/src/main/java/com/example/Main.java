package com.example;

import com.expensetrackerapisdk.ExpenseTrackerApiSdk;
import com.expensetrackerapisdk.config.ExpenseTrackerApiSdkConfig;
import com.expensetrackerapisdk.exceptions.ApiError;
import com.expensetrackerapisdk.models.ListCategoriesParameters;

public class Main {

  public static void main(String[] args) {
    ExpenseTrackerApiSdkConfig config = ExpenseTrackerApiSdkConfig.builder()
      .accessToken("YOUR_ACCESS_TOKEN")
      .build();

    ExpenseTrackerApiSdk expenseTrackerApiSdk = new ExpenseTrackerApiSdk(config);

    ListCategoriesParameters requestParameters = ListCategoriesParameters.builder()
      .includeRetired("false")
      .build();

    try {
      Object response = expenseTrackerApiSdk.expenseTrackerApiSdk.listCategories(requestParameters);

      System.out.println(response);
    } catch (ApiError e) {
      e.printStackTrace();
    }

    System.exit(0);
  }
}

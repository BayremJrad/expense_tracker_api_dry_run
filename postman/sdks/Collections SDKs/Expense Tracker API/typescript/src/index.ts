import { Environment } from './http/environment';
import { SdkConfig } from './http/types';
import { ExpenseTrackerApiSdkService } from './services/expense-tracker-api-sdk';

export * from './services/expense-tracker-api-sdk';

export * from './http';
export { Environment } from './http/environment';

export class ExpenseTrackerApiSdk {
  public readonly expenseTrackerApiSdk: ExpenseTrackerApiSdkService;

  constructor(public config: SdkConfig) {
    this.expenseTrackerApiSdk = new ExpenseTrackerApiSdkService(this.config);
  }

  set baseUrl(baseUrl: string) {
    this.expenseTrackerApiSdk.baseUrl = baseUrl;
  }

  set environment(environment: Environment) {
    this.expenseTrackerApiSdk.baseUrl = environment;
  }

  set timeoutMs(timeoutMs: number) {
    this.expenseTrackerApiSdk.timeoutMs = timeoutMs;
  }

  set token(token: string) {
    this.expenseTrackerApiSdk.token = token;
  }
}

// c029837e0e474b76bc487506e8799df5e3335891efe4fb02bda7a1441840310c

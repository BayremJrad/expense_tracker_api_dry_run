export interface ListCategoriesParams {
  includeRetired?: string;
}

export interface ListExpensesParams {
  dateFrom?: string;
  dateTo?: string;
  categoryId?: string;
  claimed?: string;
  page?: string;
  pageSize?: string;
}

export interface ListClaimsParams {
  status?: string;
  page?: string;
  pageSize?: string;
}

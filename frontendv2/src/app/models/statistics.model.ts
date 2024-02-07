export interface Statistics {
    sales_per_item: { [itemName: string]: number };
    rev_per_item: { [itemName: string]: number };
    rev_per_waiter: { [waiterName: string]: number };
    order_per_waiter: { [waiterName: string]: number };
  }
  
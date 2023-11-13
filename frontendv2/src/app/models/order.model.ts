import { Drink } from "./drink.model";
import { Meal } from "./meal.model";

export interface Order {
    id?: number;
    tableNumber: string;
    meals: Meal[];
    drinks: Drink[];
    dateTime?: Date;
  }
  
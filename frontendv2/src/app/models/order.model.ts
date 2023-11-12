import { Drink } from "./drink.model";
import { Meal } from "./meal.model";

export interface Order {
    tableNumber: string;
    meals: Meal[];
    drinks: Drink[];
  }
  
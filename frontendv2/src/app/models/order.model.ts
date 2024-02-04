import { Drink } from "./drink.model";
import { Meal } from "./meal.model";

export interface Order {
  id?: number;
  tableNumber: string;
  mealOrder: MealOrder;
  drinkOrder: DrinkOrder;
  dateTime?: Date;
  waiter?: string;
  specialInfo?: string;
}

export interface MealOrder {
  meals: Meal[];
  status?: string;
}

export interface DrinkOrder {
  drinks: Drink[];
  status?: string;
}

import { Drink } from "./drink.model";
import { Meal } from "./meal.model";

export interface Order {
  id?: number;
  tableNumber: string;
  mealOrder: MealOrder;
  drinkOrder: DrinkOrder;
  dateTime?: Date;
  waiter?: string;
}

export interface MealOrder {
  meals: Meal[];
  status?: string;
  specialInfo?: string;
}

export interface DrinkOrder {
  drinks: Drink[];
  status?: string;
  specialInfo?: string;
}

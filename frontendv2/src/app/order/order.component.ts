// order.component.ts
import { Component, OnInit } from '@angular/core';
import { Meal } from '../models/meal.model';
import { Order } from '../models/order.model';
import { Drink } from '../models/drink.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { HOST } from '../config';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faUpRightAndDownLeftFromCenter } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  showErrorMessage: boolean = false;
  order: Order = {
    tableNumber: '',
    mealOrder: {meals: []},
    drinkOrder: {drinks: []}
  };
  availableMeals: Meal[] = [];
  availableDrinks: Drink[] = [];
  subtotal: number = 0;
  distinctDrinkCategories: Set<string> = new Set<string>();
  categoryExpanded: Map<string, boolean> = new Map<string, boolean>();
  showMeals: boolean = false;
  expand = faUpRightAndDownLeftFromCenter;
  disabled: boolean = false;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.http.get<Meal[]>(HOST + "/order/list/meals").subscribe({
      next: meals => {
        this.availableMeals = meals;
        this.availableMeals.sort((a, b) => {
          if (a.identifier < b.identifier) {
            return -1;
          } else if (a.identifier > b.identifier) {
            return 1;
          } else {
            return 0;
          }
        });
      }
    });
  
    this.http.get<Drink[]>(HOST + "/order/list/drinks").subscribe({
      next: drinks => {
        this.availableDrinks = drinks;
        this.availableDrinks.sort((a, b) => {
          if (a.identifier < b.identifier) {
            return -1;
          } else if (a.identifier > b.identifier) {
            return 1;
          } else {
            return 0;
          }
        });

        this.availableDrinks.forEach(drink => {
          this.distinctDrinkCategories.add(drink.category);
        })
        this.distinctDrinkCategories.forEach(category => {
          this.categoryExpanded.set(category, false);
        })
      }
    });
  }

  getDrinksOfCategory(category: string) : Drink[]
 {
  return this.availableDrinks.filter(drink => drink.category == category)
 }

  addMeal(meal: Meal): void {
    meal.amount++;
    this.addToSubTotal(meal.price);
  }

  removeMeal(meal: Meal): void {
    if (meal.amount > 0) {
      meal.amount--;
      this.removeFromSubTotal(meal.price);
    }
  }

  addDrink(drink: Drink): void {
    drink.amount++;
    this.addToSubTotal(drink.price);
  }

  removeDrink(drink: Drink): void {
    if (drink.amount > 0) {
      drink.amount--;
      this.removeFromSubTotal(drink.price);
    }
  }

  submitOrder(): void {

    if (this.order.tableNumber !== '' && !Number.isNaN(Number(this.order.tableNumber)) && this.order.tableNumber !== null) {
      console.log(this.order.tableNumber)
      this.disabled = true;
      this.order.mealOrder.meals = this.availableMeals.filter(meal => meal.amount !== 0);
      this.order.drinkOrder.drinks = this.availableDrinks.filter(meal => meal.amount !== 0);
      this.order.waiter = localStorage.getItem("waiter_name")!;

      this.http.post<HttpResponse<String>>(HOST + "/order", this.order).subscribe({
        next: response => {
          if (typeof response === 'number') {
            this.router.navigate(['']);
          }

          this.disabled = false;
        }
      });
    } else {
      this.showErrorMessage = true;
      this.disabled = false;
    }
  }

  back() : void {
    this.router.navigate(['']);
  }

  getTotal() : number {
    let total = 0;

    // Calculate total for meals
    for (const meal of this.availableMeals) {
      total += meal.amount * meal.price!;
    }

    // Calculate total for drinks
    for (const drink of this.availableDrinks) {
      total += drink.amount * drink.price!;
    }

    return total;
  }

  addToSubTotal(price: number) : void {
    this.subtotal += price;
  }

  removeFromSubTotal(price: number) : void {
    this.subtotal -= price; //TODO check for overflow
  }

  clearSubTotal() : void {
    this.subtotal = 0;
  }
}

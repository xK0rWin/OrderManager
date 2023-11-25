// order.component.ts
import { Component, OnInit } from '@angular/core';
import { Meal } from '../models/meal.model';
import { Order } from '../models/order.model';
import { Drink } from '../models/drink.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { HOST } from '../config';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  showErrorMessage: boolean = false;
  order: Order = { tableNumber: '', meals: [], drinks: [] };
  availableMeals: Meal[] = [];
  availableDrinks: Drink[] = [];
  subtotal: number = 0;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.http.get<any>(HOST + "/order/list/meals").subscribe({
      next: meals => {
        const mealEntries = Object.entries(meals);
        for (let [meal, price] of mealEntries) {
          this.availableMeals.push({ identifier: meal, amount: 0, price: Number(price) });
        }
      }
    });
  
    this.http.get<any>(HOST + "/order/list/drinks").subscribe({
      next: drinks => {
        const drinkEntries = Object.entries(drinks);
        for (let [drink, price] of drinkEntries) {
          this.availableDrinks.push({ identifier: drink, amount: 0, price: Number(price) });
        }
        console.log(this.availableDrinks);
      }
    });
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

    if (this.order.tableNumber !== '') {
      this.order.meals = this.availableMeals.filter(meal => meal.amount !== 0);
      this.order.drinks = this.availableDrinks.filter(meal => meal.amount !== 0);
      this.order.waiter = localStorage.getItem("waiter_name")!;
      console.log('Submitted Order:', this.order);

      this.http.post<HttpResponse<String>>(HOST + "/order", this.order).subscribe({
        next: response => {
          if (typeof response === 'number') {
            this.router.navigate(['/order-confirm', response]);
          }

        }
      });
    } else {
      this.showErrorMessage = true;
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

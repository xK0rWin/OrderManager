// order.component.ts
import { Component } from '@angular/core';
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
export class OrderComponent {
  order: Order = { tableNumber: '', meals: [], drinks: [] };
  availableMeals: Meal[] = [
    { identifier: 'SausageSalad', amount: 0 },
    { identifier: 'Baguette', amount: 0 },
  ];
  availableDrinks: Drink[] = [
    { identifier: 'Coke', amount: 0 },
    { identifier: 'Fanta', amount: 0 },
  ];

  constructor(private http: HttpClient, private router: Router) {}

  addMeal(meal: Meal): void {
    meal.amount++;
  }

  removeMeal(meal: Meal): void {
    if (meal.amount > 0) {
      meal.amount--;
    }
  }

  addDrink(drink: Drink): void {
    drink.amount++;
  }

  removeDrink(drink: Drink): void {
    if (drink.amount > 0) {
      drink.amount--;
    }
  }

  submitOrder(): void {
    this.order.meals = this.availableMeals;
    this.order.drinks = this.availableDrinks;
    console.log('Submitted Order:', this.order);
    // You can send the order to a service or handle it as needed

    this.http.post<HttpResponse<String>>(HOST + "/order", this.order).subscribe(
      response => {
        if (response.status === 200) {
          console.log(response.body);
        }
      }
    );
  }
}

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
    { identifier: 'Wurstsalat', amount: 0 },
    { identifier: 'Kaesekrusti', amount: 0 },
    { identifier: 'Schinkenkrusti', amount: 0 },
  ];
  availableDrinks: Drink[] = [
    { identifier: 'Cola', amount: 0 },
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
    this.order.meals = this.availableMeals.filter(meal => meal.amount !== 0);
    this.order.drinks = this.availableDrinks.filter(meal => meal.amount !== 0);
    console.log('Submitted Order:', this.order);

    this.http.post<HttpResponse<String>>(HOST + "/order", this.order).subscribe({
      next: response => {
        if (typeof response === 'number') {
          this,this.router.navigate(['/order-confirm', response]);
        }

      }
    });
  }
}

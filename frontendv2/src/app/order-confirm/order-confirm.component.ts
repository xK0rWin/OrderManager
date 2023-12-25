// src/app/order-confirm/order-confirm.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, mapToCanActivate } from '@angular/router';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST } from '../config';
import { Drink } from '../models/drink.model';
import { Meal } from '../models/meal.model';

type Subtotal = {
  amount: number,
  meals: Map<string, number>,
  drinks: Map<string, number>
}

@Component({
  selector: 'app-order-confirm',
  templateUrl: './order-confirm.component.html',
  styleUrls: ['./order-confirm.component.scss']
})
export class OrderConfirmComponent {
  id!: string;
  loaded: boolean = false;
  order: Order = {
    tableNumber: '',
    mealOrder: {meals: []},
    drinkOrder: {drinks: []}
  };
  subtotal: Subtotal = {
    amount: 0,
    meals: new Map<string, number>(),
    drinks: new Map<string, number>()
  }

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe({
      next: params => {
        this.id = params.get('id')!;
  
        this.http.get<Order>(HOST + "/order/" + this.id).subscribe({
          next: order => {
            this.order = order;
          },
          complete: () => {
            this.loaded = true;
            this.order.mealOrder.meals.forEach(meal => {
              this.subtotal.meals.set(meal.identifier, 0);
            });
            this.order.drinkOrder.drinks.forEach(drink => {
              this.subtotal.drinks.set(drink.identifier, 0);
            });
          }
        });
      }
    });
  }

  getTotal() : number {
    let total = 0;

    // Calculate total for meals
    for (const meal of this.order.mealOrder.meals) {
      total += meal.amount * meal.price;
    }

    // Calculate total for drinks
    for (const drink of this.order.drinkOrder.drinks) {
      total += drink.amount * drink.price;
    }

    return total;
  }

  clearSubTotal() : void {
    this.subtotal.amount = 0;
    for (let [key, value] of this.subtotal.meals.entries()) {
      this.subtotal.meals.set(key, 0);
    }
    for (let [key, value] of this.subtotal.drinks.entries()) {
      this.subtotal.drinks.set(key, 0);
    }
  }

  subtotalAddDrink(drink: Drink) : void {
    this.subtotal.amount += drink.price;
    if (this.subtotal.drinks.has(drink.identifier)) {
      this.subtotal.drinks.set(drink.identifier, this.subtotal.drinks.get(drink.identifier)! + 1);
    }
  }

  subtotalAddMeal(meal: Meal) : void {
    this.subtotal.amount += meal.price;
    if (this.subtotal.meals.has(meal.identifier)) {
      this.subtotal.meals.set(meal.identifier, this.subtotal.meals.get(meal.identifier)! + 1);
    }
  }

  getDrinkByName(name: string) : number {
    return this.subtotal.drinks.get(name)!;
  }

  getMealByName(name : string) : number {
    return this.subtotal.meals.get(name)!;
  }
}

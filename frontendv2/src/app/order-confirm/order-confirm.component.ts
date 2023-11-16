// src/app/order-confirm/order-confirm.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST } from '../config';

@Component({
  selector: 'app-order-confirm',
  templateUrl: './order-confirm.component.html',
  styleUrls: ['./order-confirm.component.scss']
})
export class OrderConfirmComponent {
  id!: string;
  loaded: boolean = false;
  order: any;

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
          }
        });
      }
    });
  }

  getTotal() : number {
    let total = 0;

    // Calculate total for meals
    for (const meal of this.order.meals) {
      total += meal.amount * meal.price!;
    }

    // Calculate total for drinks
    for (const drink of this.order.drinks) {
      total += drink.amount * drink.price!;
    }

    return total;
  }
}

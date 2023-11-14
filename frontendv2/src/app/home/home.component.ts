import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST } from '../config';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  orders: Order[] = [];
  waiterName!: string;

  constructor(private router: Router, private http: HttpClient) {}

  ngOnInit(): void {
    let waiter = localStorage.getItem("waiter_name");
    if (waiter !== null) {
      this.waiterName = waiter;
    } else {
      this.waiterName = "";
    }
    this.http.get<Order[]>(HOST + "/order").subscribe({
      next: orders => {
        this.orders = orders;
      }
    });
  }

  saveWaiterInStorage() : void {
    localStorage.setItem("waiter_name", this.waiterName);
  }

  navigateToDetails(order: Order): void {
    this.router.navigate(['/order-confirm', order.id]);
  }

  createNewOrder(): void {
    this.saveWaiterInStorage();
    // Assuming you have a method to create a new order in your service
    this.router.navigate(['/order']);

  }
}

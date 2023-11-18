import { Component, Input, OnDestroy, OnInit, NgZone  } from '@angular/core';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST } from '../config';
import { SseService } from '../sse-service.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard-meal',
  templateUrl: './dashboard-meal.component.html',
  styleUrls: ['./dashboard-meal.component.scss'],
})
export class DashboardMealComponent implements OnInit, OnDestroy {
  orders: Order[] = [];
  rdyOrders: Order[] = [];
  mealOverview: Map<string, number> = new Map();

  constructor(private http: HttpClient, private sseService: SseService, private zone: NgZone) {
    const eventSource = this.sseService.openEventSource();
    console.log("Opened EventSource");
    eventSource.onmessage = (event) => {
      console.log('Received event:', event);
      this.zone.run(() => {
        this.http.get<Order[]>(HOST + "/order/mealonly/open").subscribe({
          next: orders => {
            this.orders = orders;
          }
        });
        this.http.get<Order[]>(HOST + "/order/mealonly/ready").subscribe({
          next: orders => {
            this.rdyOrders = orders;
          }
        });
        this.http.get<Map<string, number>>(HOST + "/order/meals").subscribe({
          next: result => {
            this.mealOverview = result;
          }
        });
      });
    }
  }

  ngOnInit(): void {
    this.http.get<Order[]>(HOST + "/order/mealonly/open").subscribe({
      next: orders => {
        this.orders = orders;
      }
    });
    this.http.get<Order[]>(HOST + "/order/mealonly/ready").subscribe({
      next: orders => {
        this.rdyOrders = orders;
      }
    });
    this.http.get<Map<string, number>>(HOST + "/order/meals").subscribe({
      next: result => {
        this.mealOverview = result;
      }
    });
  }

  ngOnDestroy() : void {
    console.log("Eventsource destroyed");
    this.sseService.closeEventSource();
  }

  setOrderStatus(order: Order, status: string) {
    order.status = status;
    this.http.put(HOST + "/order/" + order.id + "/" + order.status, {}).subscribe({});
  }

  getBgColor(order: Order) : string {
    switch (order.status) {
      case 'OPEN':
        return "white";
      case 'READY':
        return 'lightgreen';
      default:
        return 'white';
    }
  }

}

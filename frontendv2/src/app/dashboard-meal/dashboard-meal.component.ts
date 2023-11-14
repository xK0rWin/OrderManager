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
  mealOverview: Map<string, number> = new Map();

  constructor(private http: HttpClient, private sseService: SseService, private zone: NgZone) {}

  ngOnInit(): void {
    this.http.get<Order[]>(HOST + "/order/mealonly").subscribe({
      next: orders => {
        this.orders = orders;
      }
    });
    this.http.get<Map<string, number>>(HOST + "/order/meals").subscribe({
      next: result => {
        this.mealOverview = result;
      }
    });

    this.sseService.connect().subscribe(event => {
      console.log('Received event:', event);
      this.zone.run(() => {
        this.http.get<Order[]>(HOST + "/order/mealonly").subscribe({
          next: orders => {
            this.orders = orders;
          }
        });
        this.http.get<Map<string, number>>(HOST + "/order/meals").subscribe({
          next: result => {
            this.mealOverview = result;
          }
        });
      });
    });
  }

  ngOnDestroy() : void {
    this.sseService.disconnect();
  }

  setOrderStatus(order: Order, status: string) {
    order.status = status;
    //TODO http update order
    this.http.put(HOST + "/order/" + order.id + "/" + order.status, {}).subscribe({
      
    });
  }

}

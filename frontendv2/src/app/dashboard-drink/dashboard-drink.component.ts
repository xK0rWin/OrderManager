import { Component, NgZone, OnDestroy, OnInit } from '@angular/core';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST } from '../config';
import { SseService } from '../sse-service.service';

@Component({
  selector: 'app-dashboard-drink',
  templateUrl: './dashboard-drink.component.html',
  styleUrl: './dashboard-drink.component.scss'
})
export class DashboardDrinkComponent implements OnInit, OnDestroy {
  orders: Order[] = [];
  drinkOverview: Map<string, number> = new Map();

  constructor(private http: HttpClient, private sseService: SseService, private zone: NgZone) {
    const eventSource = this.sseService.openEventSource();
    console.log("Opened EventSource");
    eventSource.onmessage = (event) => {
      console.log('Received event:', event);
      this.zone.run(() => {
        this.http.get<Order[]>(HOST + "/order/drinkonly").subscribe({
          next: orders => {
            this.orders = orders;
          }
        });
        this.http.get<Map<string, number>>(HOST + "/order/drinks").subscribe({
          next: result => {
            this.drinkOverview = result;
          }
        });
      });
    }
  }

  ngOnInit(): void {
    this.http.get<Order[]>(HOST + "/order/drinkonly").subscribe({
      next: orders => {
        this.orders = orders;
      }
    });
    this.http.get<Map<string, number>>(HOST + "/order/drinks").subscribe({
      next: result => {
        this.drinkOverview = result;
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
}

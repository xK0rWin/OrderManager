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
  rdyOrders: Order[] = [];

  constructor(private http: HttpClient, private sseService: SseService, private zone: NgZone) {
    const eventSource = this.sseService.openEventSource();
    console.log("Opened EventSource");
    eventSource.onmessage = (event) => {
      console.log('Received event:', event);
      this.zone.run(() => {
        this.http.get<Order[]>(HOST + "/order/drinkonly/open").subscribe({
          next: orders => {
            this.orders = orders;
          }
        });
        this.http.get<Order[]>(HOST + "/order/drinkonly/ready").subscribe({
          next: orders => {
            this.rdyOrders = orders;
          }
        });
      });
    }
  }

  ngOnInit(): void {
    this.http.get<Order[]>(HOST + "/order/drinkonly/open").subscribe({
      next: orders => {
        this.orders = orders;
      }
    });
    this.http.get<Order[]>(HOST + "/order/drinkonly/ready").subscribe({
      next: orders => {
        this.rdyOrders = orders;
      }
    });
  }

  ngOnDestroy() : void {
    console.log("Eventsource destroyed");
    this.sseService.closeEventSource();
  }

  setOrderStatus(order: Order, status: string) {
    order.drinkOrder.status = status;
    this.http.put(HOST + "/order/" + order.id + "/drinkstatus/" + status, {}).subscribe({});
  }

  getBgColor(order: Order) : string {
    switch (order.drinkOrder.status) {
      case 'OPEN':
        return "white";
      case 'READY':
        return 'lightgreen';
      default:
        return 'white';
    }
  }

  setOrderToPrint(order: Order) {
    
  }
}

import { Component, NgZone, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST } from '../config';
import { SseService } from '../sse-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  orders: Order[] = [];
  deliveredOrders: Order[] = [];
  waiterName!: string;
  showDelivered: boolean = false;

  constructor(private router: Router, private http: HttpClient, private sseService: SseService, private zone: NgZone) {
    const eventSource = this.sseService.openEventSource();
    console.log("Opened EventSource");
    eventSource.onmessage = (event) => {
      console.log('Received event:', event);
      this.zone.run(() => {
        this.http.get<Order[]>(HOST + "/order/desc").subscribe({
          next: orders => {
            console.log(orders)
            this.orders = orders.sort((a, b) => {
              if (a.drinkOrder.status == 'READY' || a.mealOrder.status == 'READY') {
                return -1;
              } else if (b.drinkOrder.status == 'READY' || b.mealOrder.status == 'READY') {
                return 1;
              } else {
                return 0;
              }
            });
          }
        });
      });
      if (this.showDelivered) {
        this.refreshPassedOrders(); //todo provisional
      }
    }
  }

  ngOnInit(): void {
    let waiter = localStorage.getItem("waiter_name");
    if (waiter !== null) {
      this.waiterName = waiter;
    } else {
      this.waiterName = "";
    }
    this.http.get<Order[]>(HOST + "/order/desc").subscribe({
      next: orders => {
        this.orders = this.orders = orders.sort((a, b) => {
          if (a.drinkOrder.status == 'READY' || a.mealOrder.status == 'READY') {
            return -1;
          } else if (b.drinkOrder.status == 'READY' || b.mealOrder.status == 'READY') {
            return 1;
          } else {
            return 0;
          }
        });
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

  ngOnDestroy() : void {
    console.log("Eventsource destroyed");
    this.sseService.closeEventSource();
  }

  setOrderMealStatus(order: Order, status: string) {
    if (order.mealOrder.meals.length > 0) {
      order.mealOrder.status = status;
    this.http.put(HOST + "/order/" + order.id + "/mealstatus/" + status, {}).subscribe({});
    }
  }

  setOrderDrinkStatus(order: Order, status: string) {
    if (order.drinkOrder.drinks.length > 0) {
      order.drinkOrder.status = status;
    this.http.put(HOST + "/order/" + order.id + "/drinkstatus/" + status, {}).subscribe({});
    }
  }

  refreshPassedOrders() : void {
    this.showDelivered = true;
    this.zone.run(() => {
      this.http.get<Order[]>(HOST + "/order/delivered").subscribe({
        next: orders => {
          this.deliveredOrders = orders;
        }
      });
    });
  }

  getBgColor(order: Order) : string {
    switch (this.calcOrderStatus(order)) {
      case 'Getränke abholbereit':
        return 'lightgreen';
      case 'Essen abholbereit':
        return 'lightgreen';
        case 'Essen & Getränke abholbereit':
        return 'lightgreen';
      default:
        return 'white';
    }
  }

  calcOrderStatus(order: Order) : string {
    let status = "";

    if (order.drinkOrder.status == 'DELIVERED' && order.mealOrder.status == 'DELIVERED') {
      status = 'abgeschlossen';
    } else if (order.drinkOrder.status == 'READY' && order.mealOrder.status != 'READY') {
      status = 'Getränke abholbereit';
    } else if (order.drinkOrder.status != 'READY' && order.mealOrder.status == 'READY') {
      status = 'Essen abholbereit';
    } else if (order.drinkOrder.status == 'READY' && order.mealOrder.status == 'READY') {
      status = 'Essen & Getränke abholbereit';
    } else {
      status = 'Offen';
    }

    return status;
  }
}

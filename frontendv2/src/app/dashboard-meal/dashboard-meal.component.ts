import { Component, Input, OnDestroy, OnInit, NgZone  } from '@angular/core';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST } from '../config';
import { SseService } from '../sse-service.service';
import { Meal } from '../models/meal.model';

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
      if (event.data === 'new mealorder') {
        this.playSound();
      }
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

  playSound(): void {
    const audio = new Audio();
    audio.src = '../../../assets/sounds/discord-notification.mp3';
    audio.load();
    audio.play();
  }

  ngOnDestroy() : void {
    console.log("Eventsource destroyed");
    this.sseService.closeEventSource();
  }

  setOrderStatus(order: Order, status: string) {
    order.mealOrder.status = status;
    this.http.put(HOST + "/order/" + order.id + "/mealstatus/" + status, {}).subscribe({});
  }

  incOpenAmount(meal: Meal, order: Order) {
    this.http.put(HOST + "/order/" + order.id + "/mealorder/" + meal.identifier + "/" + (meal.amountOpen + 1), {}).subscribe({});
  }

  decOpenAmount(meal: Meal, order: Order) {
    this.http.put(HOST + "/order/" + order.id + "/mealorder/" + meal.identifier + "/" + (meal.amountOpen - 1), {}).subscribe({});
  }

  sortedMeals(order: Order) {
    return order.mealOrder.meals.sort((a: Meal, b: Meal) => a.identifier.localeCompare(b.identifier))
  }

  getBgColor(order: Order) : string {
    switch (order.mealOrder.status) {
      case 'OPEN':
        return "white";
      case 'READY':
        return 'lightgreen';
      default:
        return 'white';
    }
  }

}

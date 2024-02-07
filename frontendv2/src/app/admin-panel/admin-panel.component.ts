import { HttpClient } from '@angular/common/http';
import { Component, NgZone, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SseService } from '../sse-service.service';
import { Order } from '../models/order.model';
import { HOST } from '../config';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.scss'
})
export class AdminPanelComponent implements OnInit {
  orders!: Order[];

  constructor(private router: Router, private http: HttpClient, private sseService: SseService, private zone: NgZone) {
    const eventSource = this.sseService.openEventSource();
    console.log("Opened EventSource");
    eventSource.onmessage = (event) => {
      console.log('Received event:', event);
      this.zone.run(() => {
        this.http.get<Order[]>(HOST + "/order/allorders").subscribe({
          next: orders => {
            this.orders = orders;
          }
        });
      });
    }
  }

  ngOnInit(): void {
    this.http.get<Order[]>(HOST + "/order/allorders").subscribe({
      next: orders => {
        this.orders = orders;
      }
    });
  }

  deleteOrder(id: number): void {
    if (confirm("Löschen bestätigen")) {
      this.http.delete<Order>(HOST + "/order/" + id).subscribe({
        next: orders => {
        }
      });
    }
  }

  statView(): void {
    this.router.navigate(['statistics']);
  }
}
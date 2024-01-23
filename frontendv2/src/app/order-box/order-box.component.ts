import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST } from '../config';

@Component({
  selector: 'app-order-box',
  templateUrl: './order-box.component.html',
  styleUrl: './order-box.component.scss'
})
export class OrderBoxComponent {
  @Input() order!: Order;
  printView: boolean = false;

  constructor(private http: HttpClient) {
  }

  setOrderStatus(order: Order, status: string) {
    order.drinkOrder.status = status;
    this.http.put(HOST + "/order/" + order.id + "/drinkstatus/" + status, {}).subscribe({});
  }

  print(): void {
    let printContents, popupWin;
    printContents = document.getElementById(`print-section${this.order.id}`)!.innerHTML;
    popupWin = window.open('', '_blank', 'top=0,left=0,height=100%,width=auto')!;
    popupWin.document.open();
    popupWin.document.write(`
      <html>
        <head>
          <title>Print Order</title>
          <style>
            div {
              max-width: 800px;
            }
          </style>
        </head>
    <body onload="window.print();window.close()">${printContents}</body>
      </html>`
    );
    popupWin.document.close();
}
}

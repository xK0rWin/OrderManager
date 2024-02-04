import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST, PRINTER } from '../config';

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

  testPrint(): void {
    let request = '<epos-print xmlns="http://www.epson-pos.com/schemas/2011/03/epos-print">';
    request += '<text lang="en" smooth="true"/>';
    request += '<text font="font_a"/>';
    request += `<text width="3" height="3">Tisch ${this.order.tableNumber}&#13;&#10;</text>`;
    request += `<text width="2" height="2">ID ${this.order.id}&#13;&#10;</text>`;
    for (let drink of this.order.drinkOrder.drinks) {
<<<<<<< HEAD
      request += `<text width="2" height="2">${drink.amount + "x " + drink.identifier}&#13;&#10;</text>`;
=======
      request += `<text width="1.5" height="1.5">${drink.amount + "x " + drink.identifier}&#13;&#10;</text>`;
>>>>>>> aa877104b17d6eeca9a9a2abe5908ff5b6ccc84f
    }
    if (this.order.drinkOrder.drinks.length < 8) {
      for (let i = 0; i < (8 - this.order.drinkOrder.drinks.length); i++) {
        request += `<text width="1" height="1">&#13;&#10;</text>`;
      }
    }
    if (this.order.drinkOrder.specialInfo) {
      request += `<text width="2" height="2">Info: ${this.order.drinkOrder.specialInfo}&#13;&#10;</text>`;
    }
    request += '<cut type="feed"/>';
    request += '</epos-print>';

    //Create a SOAP envelop
    var soap = '<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">' + '<s:Body>' + request + '</s:Body></s:Envelope>';
    //Create an XMLHttpRequest object
    var xhr = new XMLHttpRequest();
    //Set the end point address
    var url = `${PRINTER}/cgi-bin/epos/service.cgi?devid=local_printer&timeout=10000`;
    //Open an XMLHttpRequest object
    xhr.open('POST', url, true);
    //<Header settings>
    xhr.setRequestHeader('Content-Type', 'text/xml; charset=utf-8');
    xhr.setRequestHeader('If-Modified-Since', 'Thu, 01 Jan 1970 00:00:00 GMT');
    xhr.setRequestHeader('SOAPAction', '""');
    // Send print document
    xhr.send(soap);
  }
}

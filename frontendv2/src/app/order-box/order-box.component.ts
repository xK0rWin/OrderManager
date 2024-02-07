import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Order } from '../models/order.model';
import { HttpClient } from '@angular/common/http';
import { HOST, PRINTER } from '../config';
import { PrintStateService } from '../print-state-service.service';

@Component({
  selector: 'app-order-box',
  templateUrl: './order-box.component.html',
  styleUrl: './order-box.component.scss',
})
export class OrderBoxComponent implements OnInit {
  @Input() order!: Order;
  blocked: boolean = false;

  constructor(private http: HttpClient, private printStateService: PrintStateService) {
  }

  ngOnInit(): void {
    this.blocked = this.printStateService.getState(this.order.id!.toString());
  }

  setOrderStatus(order: Order, status: string) {
    order.drinkOrder.status = status;
    this.http
      .put(HOST + '/order/' + order.id + '/drinkstatus/' + status, {})
      .subscribe({});
  }

  print(): void {
    if (!this.printStateService.getState(this.order.id!.toString())) {
      let request =
        '<epos-print xmlns="http://www.epson-pos.com/schemas/2011/03/epos-print">';
      request += '<text lang="en" smooth="true"/>';
      request += '<text font="font_a"/>';
      request += `<text width="3" height="3">Tisch ${this.order.tableNumber}&#13;&#10;</text>`;
      request += `<text width="2" height="2">ID ${this.order.id}&#13;&#10;</text>`;
      for (let drink of this.order.drinkOrder.drinks) {
        request += `<text width="2" height="2">${
          drink.amount + 'x ' + drink.identifier
        }&#13;&#10;</text>`;
      }
      if (this.order.drinkOrder.drinks.length < 8) {
        for (let i = 0; i < 8 - this.order.drinkOrder.drinks.length; i++) {
          request += `<text width="1" height="1">&#13;&#10;</text>`;
        }
      }
      if (this.order.drinkOrder.specialInfo) {
        request += `<text width="2" height="2">Info: ${this.order.drinkOrder.specialInfo}&#13;&#10;</text>`;
      }
      request += '<cut type="feed"/>';
      request += '</epos-print>';

      //Create a SOAP envelop
      var soap =
        '<s:Envelope xmlns:s="http://schemas.xmlsoap.org/soap/envelope/">' +
        '<s:Body>' +
        request +
        '</s:Body></s:Envelope>';
      //Create an XMLHttpRequest object
      var xhr = new XMLHttpRequest();
      //Set the end point address
      var url = `${PRINTER}/cgi-bin/epos/service.cgi?devid=local_printer&timeout=10000`;
      //Open an XMLHttpRequest object
      xhr.open('POST', url, true);
      //<Header settings>
      xhr.setRequestHeader('Content-Type', 'text/xml; charset=utf-8');
      xhr.setRequestHeader(
        'If-Modified-Since',
        'Thu, 01 Jan 1970 00:00:00 GMT'
      );
      xhr.setRequestHeader('SOAPAction', '""');
      // Send print document
      xhr.send(soap);
      this.printStateService.setState(this.order.id!.toString(), true);
      this.blocked = this.printStateService.getState(this.order.id!.toString());
    }
  }

  resetPrintState(): void {
    this.printStateService.setState(this.order.id!.toString(), false);
    this.blocked = this.printStateService.getState(this.order.id!.toString());
  }
}

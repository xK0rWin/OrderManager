import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HOST } from './config';

@Injectable({
    providedIn: 'root'
})
export class SseService {

    private eventSource: EventSource | undefined;

  constructor() {}

  openEventSource(): EventSource {
    if (!this.eventSource) {
      this.eventSource = new EventSource(HOST + "/order/sse");
    }

    return this.eventSource;
  }

  closeEventSource(): void {
    if (this.eventSource) {
      this.eventSource.close();
      this.eventSource = undefined;
    }
  }
}

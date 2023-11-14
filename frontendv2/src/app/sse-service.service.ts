import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HOST } from './config';

@Injectable({
    providedIn: 'root'
})
export class SseService {

    private eventSource!: EventSource;

    connect(): Observable<MessageEvent> {
        this.eventSource = new EventSource(HOST + '/order/sse');
        return new Observable(observer => {
            this.eventSource.onmessage = event => {
                // Ignore comments
                console.log(event.data);
                if (event.data === ': Keep-alive') {
                    return;
                }
                observer.next(event);
            };
        });
    }

    disconnect() {
        if (this.eventSource) {
            this.eventSource.close();
        }
    }
}

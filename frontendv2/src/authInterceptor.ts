import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    // Inject your authentication service here
    // private authService: AuthService
  ) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // Get the authentication token from your service
    // const authToken = this.authService.getAuthToken();

    // Replace 'YOUR_BEARER_TOKEN' with the actual token or use the commented line above


    // Clone the request and add the Bearer header
    const modifiedRequest = request.clone({
        setHeaders: {
          'ngrok-skip-browser-warning': 'true'
        }
      });

    // Pass the cloned request to the next handler
    return next.handle(modifiedRequest);
  }
}

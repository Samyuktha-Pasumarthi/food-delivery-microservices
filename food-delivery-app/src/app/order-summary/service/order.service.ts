import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { API_URL_Order } from '../../constants/url';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly apiUrl = API_URL_Order + '/order/placeOrder';

  constructor(private http: HttpClient) {}


  placeOrder(orderData: any): Observable<any> {
    return this.http.post(this.apiUrl, orderData)
    .pipe(
        catchError(this.handleError)
      )

  }

   private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Something went wrong. Please try again.';

    if (error.error instanceof ErrorEvent) {
      // Client-side error
      console.error('Client Error:', error.error.message);
      errorMessage = error.error.message;
    } else {
      // Server-side error
      console.error(`Server Error ${error.status}:`, error.error);
      errorMessage = error.error?.message || 'Server error occurred';
    }

    return throwError(() => new Error(errorMessage));
  }

  
}


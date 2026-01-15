import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { API_URL_FC } from '../../constants/url';
import { FoodCataloguePage } from '../../shared/models/FoodCataloguePage';


@Injectable({
  providedIn: 'root',
})
export class FoodItemService {

    private apiUrl =
    'http://localhost:9092/foodcatalogue/fetchRestaurantAndFoodItemsById';


  constructor(private http: HttpClient) {}

getFoodItemsByRestaurant(id: number): Observable<FoodCataloguePage> {
    const url = `${this.apiUrl}/${id}`;
    console.log('ANGULAR CALLING 👉', url);
    return this.http.get<FoodCataloguePage>(url);
  }
}



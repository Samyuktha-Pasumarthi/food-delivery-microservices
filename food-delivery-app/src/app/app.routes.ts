import { Routes } from '@angular/router';
import { RestaurantListing } from './restaurant-listing/components/restaurant-listing';
import { FoodCatalogue } from './food-catalogue/components/food-catalogue';
import { OrderSummaryComponent } from './order-summary/components/order-summary';

export const routes: Routes = [
  { path: '', redirectTo: 'restaurant-listing', pathMatch: 'full' },
  { path: 'restaurant-listing', component: RestaurantListing },
  { path: 'food-catalogue/:restaurantId', component: FoodCatalogue,  data: { renderMode: 'server' } },
  { path: 'order-summary', component: OrderSummaryComponent} 

];


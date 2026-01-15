import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule} from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';

import { FoodItemService } from '../service/fooditem.service';
import { FoodCataloguePage } from '../../shared/models/FoodCataloguePage';
import { FoodItem } from '../../shared/models/FoodItem';
@Component({
  selector: 'app-food-catalogue',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './food-catalogue.html',
  styleUrls: ['./food-catalogue.css'],
})
export class FoodCatalogue implements OnInit {

  restaurantId!: number;
  foodItemResponse!: FoodCataloguePage;
  foodItemCart: FoodItem[] = [];
  

  constructor(
    private route: ActivatedRoute,
    private foodItemService: FoodItemService,
    private router: Router,
    private cdr: ChangeDetectorRef) {
       

    }

ngOnInit(): void {
  this.route.paramMap.subscribe(params => {
    const idParam = params.get('restaurantId');

    if (!idParam) {
      console.error('restaurantId missing');
      return;
    }

    const id = Number(idParam);
    console.log('ORDER NOW CLICKED → restaurantId:', id);

    this.foodItemResponse = undefined as any;

    this.getFoodItemsByRestaurant(id);
  });
}

getFoodItemsByRestaurant(restaurantId: number): void {
    this.foodItemService.getFoodItemsByRestaurant(restaurantId).subscribe({
      next: (data) => {
       
        data.foodItemsList.forEach(item => {
          if (item.quantity == null) {
            item.quantity = 0;
          }
        });
        console.log('API RESPONSE 👉', data);
        this.foodItemResponse = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  increment(food: FoodItem): void {
    food.quantity!++;

    const index = this.foodItemCart.findIndex(
      item => item.id === food.id
    );

    if (index === -1) {
      this.foodItemCart.push({ ...food });
    } else {
      this.foodItemCart[index] = { ...food };
    }
  }

  decrement(food: FoodItem): void {
    if (food.quantity! > 0) {
      food.quantity!--;

      const index = this.foodItemCart.findIndex(
        item => item.id === food.id
      );

      if (food.quantity === 0 && index !== -1) {
        this.foodItemCart.splice(index, 1);
      } else if (index !== -1) {
        this.foodItemCart[index] = { ...food };
      }
    }
  }

  onCheckOut(): void {
    const orderSummary = {
      foodItemsList: this.foodItemCart,
      restaurant: this.foodItemResponse.restaurant
    };

    this.router.navigate(
      ['/order-summary'],
      { queryParams: { data: JSON.stringify(orderSummary) } }
    );
  }
}
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Restaurant } from '../../shared/models/restaurant';
import { RestaurantService } from '../service/restaurant-service';

console.log('RestaurantListing file loaded 📦');

@Component({
  selector: 'app-restaurant-listing',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './restaurant-listing.html',
  styleUrls: ['./restaurant-listing.css'],
})
export class RestaurantListing implements OnInit {

  restaurantList!: Restaurant[];

 constructor(
  private router: Router,
  private restaurantService: RestaurantService
) {
 
}


  ngOnInit(): void {
    this.getAllRestaurants(); 
  }

  getAllRestaurants(): void {
    this.restaurantService.getAllRestaurants().subscribe({
      next: (data) => {
        this.restaurantList = data;
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

onButtonClick(id: number | undefined): void {
  if (!id) return;

  console.log('Navigating to food catalogue:', id);
  this.router.navigate(['/food-catalogue', id]);
}

  getRandomImage(): string {
    const images = ['1.jpg', '2.jpg', '3.jpg','4.jpg','5.jpg','6.jpg','7.jpg','8.jpg'];
    return images[Math.floor(Math.random() * images.length)];
  }


}



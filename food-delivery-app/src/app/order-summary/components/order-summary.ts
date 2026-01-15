import { Component,OnInit  } from '@angular/core';
import { OrderService } from '../service/order.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { orderDTO } from '../models/orderDTO';


@Component({
  selector: 'app-order-summary',
  imports: [NgFor,NgIf,CommonModule],
  standalone: true,
  templateUrl: './order-summary.html',
  styleUrl: './order-summary.css',
})
export class OrderSummaryComponent implements OnInit {

  orderSummary!: orderDTO;
  orderItems: any[] = [];
  total = 0;
  showDialog: boolean = false;


  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService,
    private router: Router
  ) {}

 ngOnInit(): void {
    const data = this.route.snapshot.queryParamMap.get('data');

    if (!data) {
      console.error('No order data found');
      return;
    }

    this.orderSummary = JSON.parse(data);
    this.orderItems = this.orderSummary.foodItemsList ?? [];


    this.calculateTotal();
  }

  private calculateTotal(): void {
    this.total = this.orderItems.reduce((sum, item) =>
       sum + (item.price * item.quantity),
      0
    );
  }
   placeOrder() {
    this.orderService.placeOrder(this.orderSummary).subscribe({
      next: () => {
        this.showDialog = true;
      },
      error: (err) => {
        console.error('Order failed:', err);
      }
    });
  }

  closeDialog(): void {
    this.showDialog = false;
    this.router.navigate(['/']);
  }


}

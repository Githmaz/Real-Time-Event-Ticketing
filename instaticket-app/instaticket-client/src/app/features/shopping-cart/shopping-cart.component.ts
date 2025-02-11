import { CommonModule, DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { PaymentFormComponent } from "./components/payment-form/payment-form.component";
import { ItemCartComponent } from './components/item-cart/item-cart.component';
import { TicketCart } from '../../models/ticket-cart';
import { TicketPackage } from '../../models/ticket-package';

@Component({
  selector: 'app-shopping-cart',
  standalone: true,
  imports: [DatePipe, CommonModule, PaymentFormComponent,ItemCartComponent,PaymentFormComponent],
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.css'
})
export class ShoppingCartComponent {


  cartItems = [
    {
      packageId: 'E5P9',
      eventName: 'Rock Concert 2025',
      packageType: 'VIP - Front Row',
      eventDate: '2025-03-10T20:00:00',
      price: 150.0,
      quantity: 2,
      availableTickets: 50,
      eventImage: 'https://images.unsplash.com/photo-1541675154750-0444c7d51e8f',
    },
  ];

  discount: number = 100; // Value received from backend
  tax: number = 43.00; // Default tax, will update from backend













  get subtotal(): number {
    return this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
  }

  get total(): number {
    return this.subtotal - this.discount + this.tax;
  }

  increaseQuantity(item: any) {
    if (item.quantity < item.availableTickets) item.quantity++;
  }

  decreaseQuantity(item: any) {
    if (item.quantity > 1) item.quantity--;
  }

  removeFromCart(packageId: string) {
    this.cartItems = this.cartItems.filter((item) => item.packageId !== packageId);
  }

  // Simulate fetching discount & tax from backend
  fetchPaymentData() {
    // This would be replaced with a real API call
    setTimeout(() => {
      this.discount = 10; // Example discount from backend
      this.tax = 5.50; // Example tax from backend
    }, 1000);
  }

  ngOnInit() {
    this.fetchPaymentData(); // Fetch discount & tax on component load
  }
}

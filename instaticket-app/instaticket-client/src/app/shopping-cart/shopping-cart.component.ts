import { CommonModule, DatePipe } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-shopping-cart',
  standalone: true,
  imports: [DatePipe,CommonModule],
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
      availableTickets: 50, // Total available tickets for this package
      eventImage: 'https://images.unsplash.com/photo-1541675154750-0444c7d51e8f',
    },
    {
      packageId: 'E5P10',
      eventName: 'Tech Conference 2025',
      packageType: 'Standard Admission',
      eventDate: '2025-04-05T10:00:00',
      price: 75.0,
      quantity: 1,
      availableTickets: 20,
      eventImage: 'https://images.unsplash.com/photo-1516259762381-22954d7d3ad2',
    },
    {
      packageId: 'E5P11',
      eventName: 'EDM Festival 2025',
      packageType: 'Backstage Pass',
      eventDate: '2025-06-20T22:00:00',
      price: 200.0,
      quantity: 3,
      availableTickets: 5,
      eventImage: 'https://images.unsplash.com/photo-1533174072545-7a4b6ad7a6c3',
    },
  ];

  // Calculate Total Items in Cart
  get totalItems(): number {
    return this.cartItems.reduce((total, item) => total + item.quantity, 0);
  }

  // Calculate Subtotal Price
  get subtotal(): number {
    return this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
  }

  // Calculate Final Total (Can include tax/shipping logic later)
  get total(): number {
    return this.subtotal;
  }

  // Increase Quantity of a Package (Stops if no tickets left)
  increaseQuantity(packageItem: any): void {
    if (packageItem.quantity < packageItem.availableTickets) {
      packageItem.quantity++;
    }
  }

  // Decrease Quantity (Min: 1)
  decreaseQuantity(packageItem: any): void {
    if (packageItem.quantity > 1) {
      packageItem.quantity--;
    }
  }

  // Remove Item from Cart
  removeFromCart(packageId: string): void {
    this.cartItems = this.cartItems.filter((item) => item.packageId !== packageId);
  }
}

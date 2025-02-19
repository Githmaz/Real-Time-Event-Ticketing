import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { PaymentFormComponent } from "./components/payment-form/payment-form.component";
import { ItemCartComponent } from './components/item-cart/item-cart.component';
import { TicketCart } from '../../models/ticket-cart';

@Component({
  selector: 'app-shopping-cart',
  standalone: true,
  imports: [CommonModule, PaymentFormComponent,ItemCartComponent,PaymentFormComponent],
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.css'
})
export class ShoppingCartComponent {
  ticketCart:TicketCart | null = null; 

  cartItems = [
    {
      packageId: 'E2P1',
      eventName: 'Interstellar',
      packageType: 'IMAX Experience',
      eventDate: '2025-04-10T19:30:00',
      price: 100.0,
      quantity: 1,
      availableTickets: 100,
      eventImage: 'https://images.unsplash.com/photo-1517602302552-471fe67acf66', 
    }
  ];

  discount: number = 25; 
  tax: number = 20; 

  // Calculate subtotal (before discount and tax)
  get subtotal(): number {
    return this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
  }

  // Calculate discount amount based on subtotal
  get discountAmount(): number {
     const items = this.cartItems[0].quantity;
     console.log(items);
    return ( items*this.discount) ;
  }

  // Calculate tax amount based on subtotal (after discount)
  get taxAmount(): number {
    return (this.tax);
  }

  // Calculate total (subtotal - discount + tax)
  get total(): number {
    return this.subtotal - this.discountAmount + this.taxAmount;
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

  fetchPaymentData() {
  }

  ngOnInit() {
    this.fetchPaymentData(); 
  }
}

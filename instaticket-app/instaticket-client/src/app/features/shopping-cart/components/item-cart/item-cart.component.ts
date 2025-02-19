import { DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TicketPackage } from '../../../../models/ticket-package';

@Component({
  selector: 'app-item-cart',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './item-cart.component.html',
  styleUrl: './item-cart.component.css'
})
export class ItemCartComponent {
  @Input() cartItems: any[] = [];
  @Output() increaseQuantity = new EventEmitter<any>();
  @Output() decreaseQuantity = new EventEmitter<any>();
  @Output() removeFromCart = new EventEmitter<string>();

  // Emit quantity changes to parent
  onIncrease(item: any) {
    this.increaseQuantity.emit(item);
  }

  onDecrease(item: any) {
    this.decreaseQuantity.emit(item);
  }

  onRemove(packageId: string) {
    this.removeFromCart.emit(packageId);
  }
}

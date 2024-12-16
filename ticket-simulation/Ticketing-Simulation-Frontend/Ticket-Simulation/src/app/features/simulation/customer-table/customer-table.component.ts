import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Customer } from '../../../models/customer/customer.model';

@Component({
  selector: 'app-customer-table',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customer-table.component.html',
  styleUrl: './customer-table.component.css',
})
export class CustomerTableComponent {
  @Input() customerCount: number = 0; // Input value for customer count
  @Output() customerListChange = new EventEmitter<Customer[]>(); // Emit customer list updates
  customers: Customer[] = [];

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['customerCount'] && changes['customerCount'].currentValue > 0) {
      this.generateCustomers();
    }
  }

  private generateCustomers(): void {
    this.customers = [];
    for (let i = 0; i < this.customerCount; i++) {
      this.customers.push(new Customer(i)); // Generate default customers
    }
    this.customerListChange.emit(this.customers);
  }


  updateCustomer(index: number, field: 'customerName' | 'isVIP' | 'numberOfTickets', event: Event): void {
    const customer = this.customers[index];
    if (!customer) {
      console.warn(`Customer at index ${index} does not exist.`);
      return;
    }

    const inputValue = (event.target as HTMLInputElement).value; // Extract value from event

    if (field === 'customerName') {
      customer.setCustomerName(inputValue);
    } else if (field === 'isVIP') {
      customer.setIsVip(!customer.getIsVip()); // Toggle the VIP status
    } else if (field === 'numberOfTickets') {
      customer.setNumberOfTickets(Number(inputValue));
    }

    this.customerListChange.emit(this.customers); // Notify parent of changes
  }
}

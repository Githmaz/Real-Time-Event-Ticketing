import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Vendor } from '../../../models/vendor/vendor.model';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-vendor-table',
  standalone: true,
  imports: [CommonModule, FormsModule,HttpClientModule],
  templateUrl: './vendor-table.component.html',
  styleUrl: './vendor-table.component.css',
})
export class VendorTableComponent {
  @Input() vendorCount: number = 0; // Input value for vendor count
  @Output() vendorsChange = new EventEmitter<Vendor[]>(); // Emit vendor list updates
  vendors: Vendor[] = [];

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['vendorCount'] && changes['vendorCount'].currentValue > 0) {
      this.generateVendors();
    }
  }

  private generateVendors(): void {
    this.vendors = [];
    for (let i = 0; i < this.vendorCount; i++) {
      this.vendors.push(new Vendor(i+1)); // Generate default vendors
    }
    this.vendorsChange.emit(this.vendors);
  }

  updateVendor(index: number, field: 'vendorName' | 'totalTickets' | 'ticketPrice', event: Event): void {
    const vendor = this.vendors[index];
    if (!vendor) {
      console.warn(`Vendor at index ${index} does not exist.`);
      return;
    }
  
    const inputValue = (event.target as HTMLInputElement).value; // Extract value from event
  
    if (field === 'vendorName') {
      vendor.setVendorName(inputValue);
    } else if (field === 'totalTickets') {
      vendor.setTotalTickets(Number(inputValue));
    } else if (field === 'ticketPrice') {
      vendor.setTicketPrice(Number(inputValue));
    }
  
    this.vendorsChange.emit(this.vendors); // Notify parent of changes
  }
  
}

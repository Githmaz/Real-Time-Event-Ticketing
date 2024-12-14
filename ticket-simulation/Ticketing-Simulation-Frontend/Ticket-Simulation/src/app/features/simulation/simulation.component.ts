import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CustomerTableComponent } from "./customer-table/customer-table.component";
import { InitializeSimulationComponent } from "./initialize-simulation/initialize-simulation.component";
import { VendorTableComponent } from "./vendor-table/vendor-table.component";
import { Customer } from '../../models/customer/customer.model';
import { Vendor } from '../../models/vendor/vendor.model';

@Component({
  selector: 'app-simulation',
  standalone: true,
  imports: [FormsModule, CustomerTableComponent, InitializeSimulationComponent, VendorTableComponent],
  templateUrl: './simulation.component.html',
  styleUrl: './simulation.component.css'
})
export class SimulationComponent {
  isAutoGenerate: boolean = true;
  customerCount: number = 0;
  vendorCount: number = 0;
  customers: Customer[] = []; // Holds the last updated customers
  vendors: Vendor[] = []; // Holds the last updated vendors
  cdr: any;

  // Update the customers array when emitted from child
  handleCustomerListChange(updatedCustomers: Customer[]): void {
    setTimeout(() => {
      this.customers = updatedCustomers;
      console.log('Updated Customers:', this.customers);
    });
  }

  // Update the vendors array when emitted from child
  handleVendorListChange(updatedVendors: Vendor[]): void {
    setTimeout(() => {
      this.vendors = updatedVendors;
      console.log('Updated Vendors:', this.vendors);
    });
  }

  getCustomer() : any {
    return this.customers;
  }
  getVendor() : any {
    return this.vendors;
  }
}

import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Customer } from '../../../models/customer/customer.model';
import { LaunchSimulationService } from '../../../services/lauch-simulation/launch-simulation.service';
import { AlertService } from '../../../services/alert/alert.service';

@Component({
  selector: 'app-add-customer-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-customer-form.component.html',
  styleUrl: './add-customer-form.component.css',
})
export class AddCustomerFormComponent {
  customer: Customer;

  constructor(
    private simulationService: LaunchSimulationService,
    private alertService: AlertService
  ) {
    // Initialize with default customer
    this.customer = new Customer(0); // Pass an initial ID
  }

  onSubmit(): void {
    this.simulationService.addCustomer(this.customer).subscribe({
      next: (response: { success: any }) => {
        if (response.success) {
          this.alertService.showAlert('Customer added successfully!', 'success');
        } else {
          this.alertService.showAlert('Failed to add customer.', 'warning');
        }
      },
      error: (err) => {
        this.alertService.showAlert('Error adding customer.', 'error');
        console.error(err);
      },
    });
  }

  // Update customer name
  updateCustomerName(name: string): void {
    this.customer.setCustomerName(name);
  }

  // Update VIP status
  updateIsVip(isVip: boolean): void {
    this.customer.setIsVip(isVip);
  }

  // Update number of tickets
  updateNumberOfTickets(tickets: number): void {
    this.customer.setNumberOfTickets(tickets);
  }
}

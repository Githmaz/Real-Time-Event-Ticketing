import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Vendor } from '../../../models/vendor/vendor.model';
import { FormsModule } from '@angular/forms';
import { SimulationService } from '../../../services/simulation.service';
import { AlertService } from '../../../services/alert/alert.service';
import { LaunchSimulationComponent } from '../launch-simulation.component';
import { LaunchSimulationService } from '../../../services/lauch-simulation/launch-simulation.service';

@Component({
  selector: 'app-add-vendor-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-vendor-form.component.html',
  styleUrl: './add-vendor-form.component.css'
})
export class AddVendorFormComponent {
  vendor: Vendor;

  constructor(
    private simulationService: LaunchSimulationService,
    private alertService: AlertService
  ) {
    // Initialize with default vendor
    this.vendor = new Vendor(1); // Pass an initial ID
  }

  onSubmit(): void {
    this.simulationService.addVendor(this.vendor).subscribe({
      next: (response: { success: any; }) => {
        if (response.success) {
          this.alertService.showAlert('Vendor added successfully!', 'success');
        } else {
          this.alertService.showAlert('Failed to add vendor.', 'warning');
        }
      },
      error: (err) => {
        this.alertService.showAlert('Error adding vendor.', 'error');
        console.error(err);
      },
    });
  }

  // Methods to update vendor fields
  updateVendorName(name: string): void {
    this.vendor.setVendorName(name);
  }

  updateTotalTickets(tickets: number): void {
    this.vendor.setTotalTickets(tickets);
  }

  updateTicketPrice(price: number): void {
    this.vendor.setTicketPrice(price);
  }
}

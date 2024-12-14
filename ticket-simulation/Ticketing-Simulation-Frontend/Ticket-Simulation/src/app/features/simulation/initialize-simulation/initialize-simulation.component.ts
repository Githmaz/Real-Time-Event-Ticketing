import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SimulationService } from '../../../services/simulation.service';
import { AlertService } from '../../../services/alert/alert.service';
import { SimulationInitializedData } from '../../../models/simulation-initailized-data/simulation-initialized-data.model';
import { Customer } from '../../../models/customer/customer.model';
import { Vendor } from '../../../models/vendor/vendor.model';

@Component({
  selector: 'app-initialize-simulation',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './initialize-simulation.component.html',
  styleUrl: './initialize-simulation.component.css',
})
export class InitializeSimulationComponent {
  @Output() customerCountChange = new EventEmitter<number>();
  @Output() vendorCountChange = new EventEmitter<number>();
  @Output() isAutoGenerateChange = new EventEmitter<boolean>();
  @Input() customers: Customer[] = []; // Always get customers from SimulationComponent
  @Input() vendors: Vendor[] = []; 
  isAutoGenerate: boolean = true; // Track auto-generate toggle
  simulation: SimulationInitializedData = {
    customerCount: 0,
    vendorCount: 0,
    ticketsPerCustomer: 1,
    vipCustomerCount: 0,
  };

  constructor(
    private simulationService: SimulationService,
    private alertService: AlertService
  ) {}

  onSubmit(): void {
    if(this.simulation.customerCount<0){
      this.alertService.showAlert('Invalid Customer count!', 'error');
      return
    }
    if(this.simulation.ticketsPerCustomer<0){
      this.alertService.showAlert('Invalid Ticket per Customer Value','error')
      return;
    }
    if(this.simulation.vendorCount<0){
      this.alertService.showAlert('Invalid Vendor count','error')
      return;
    }

    if(this.simulation.vipCustomerCount<0 ||this.simulation.vipCustomerCount > this.simulation.customerCount ){
      this.alertService.showAlert('Invalid Vip count','error')
      return;
    }

    if (this.isAutoGenerate) {
      this.simulationService.startSimulation(this.simulation).subscribe({
        next: (response) => {
          if (response.success) {
            this.alertService.showAlert('Simulation started successfully!', 'success');
          } else {
            this.alertService.showAlert('Failed to start simulation.', 'error');
          }
        },
        error: (err) => {
          this.alertService.showAlert('Error starting simulation.', 'error');
          console.error(err);
        },
      });
    } else {
      const request = {
        customers: this.customers,
        vendors: this.vendors,
      };
      console.log(request);
      this.simulationService.startSimulationWithManualData(request).subscribe({
        next: (response) => {
          if (response.success) {
            this.alertService.showAlert(
              'Simulation started successfully with manual data!',
              'success'
            );
          } else {
            this.alertService.showAlert(
              'Failed to start simulation with manual data.',
              'warning'
            );
          }
        },
        error: (err) => {
          this.alertService.showAlert(
            'Error starting simulation with manual data.',
            'error'
          );
          console.error(err);
        },
      });
    }
  }

  updateCustomerCount(count: number): void {
    this.simulation.customerCount = count;
    this.customerCountChange.emit(count);
  }

  updateVendorCount(count: number): void {
    this.simulation.vendorCount = count;
    this.vendorCountChange.emit(count);
  }

  toggleAutoGenerate(isAutoGenerate: boolean): void {
    this.isAutoGenerate = isAutoGenerate;
    this.isAutoGenerateChange.emit(isAutoGenerate);
  }

 

}

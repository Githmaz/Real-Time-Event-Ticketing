import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { SimulationStatus } from '../../../models/simulation-status/simulation-status';
import { AlertService } from '../../../services/alert/alert.service';
import { LaunchSimulationService } from '../../../services/lauch-simulation/launch-simulation.service';

@Component({
  selector: 'app-simulation-control-panel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './simulation-control-panel.component.html',
  styleUrl: './simulation-control-panel.component.css'
})
export class SimulationControlPanelComponent {
  @Input() simulationStatus!: SimulationStatus;
  @Output() isAddCustomerActiveChange = new EventEmitter<boolean>();
  @Output() isAddVendorActiveChange = new EventEmitter<boolean>();
  @Output() isStartedChange = new EventEmitter<boolean>();

  isStarted = false;
  isAddCustomerActive = false; 
  isAddVendorActive = false;
  constructor(
    private simulationService: LaunchSimulationService,
    private alertService: AlertService
  ) {}
  
  toggleStartStop(): void {
    if (!this.isStarted) {
      // Call the startSimulation API
      this.simulationService.startSimulation().subscribe({
        next: (response) => {
          if (response.success) {
            this.isStarted = true;
            this.alertService.showAlert('Simulation started successfully!', 'success');
            this.isStartedChange.emit(this.isStarted);
          } else {
            this.alertService.showAlert('Failed to start simulation.', 'warning');
          }
        },
        error: (err) => {
          this.alertService.showAlert('Error starting simulation.', 'error');
          console.error(err);
        }
      });
    } else {
      // Call the stopSimulation API
      this.simulationService.stopSimulation().subscribe({
        next: (response) => {
          if (response.success) {
            this.isStarted = false;
            this.alertService.showAlert('Simulation stopped successfully!', 'success');
            this.isStartedChange.emit(this.isStarted);
          } else {
            this.alertService.showAlert('Failed to stop simulation.', 'warning');
          }
        },
        error: (err) => {
          this.alertService.showAlert('Error stopping simulation.', 'error');
          console.error(err);
        }
      });
    }
  }

  toggleAddCustomer(): void {
    this.isAddCustomerActive = !this.isAddCustomerActive;
    if (this.isAddCustomerActive) {
      this.isAddVendorActive = false; 
      this.isAddVendorActiveChange.emit(false);
    }
    this.isAddCustomerActiveChange.emit(this.isAddCustomerActive);
  }

  toggleAddVendor(): void {
    this.isAddVendorActive = !this.isAddVendorActive;
    if (this.isAddVendorActive) {
      this.isAddCustomerActive = false; 
      this.isAddCustomerActiveChange.emit(false);
    }
    this.isAddVendorActiveChange.emit(this.isAddVendorActive);
  }

}

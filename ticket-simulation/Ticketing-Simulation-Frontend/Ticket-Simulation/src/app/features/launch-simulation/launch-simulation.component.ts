import { Component } from '@angular/core';
import { ApiResponse } from '../../models/api-response/api-response.model';
import { Subscription } from 'rxjs';
import { LaunchSimulationService } from '../../services/lauch-simulation/launch-simulation.service';
import { NotFoundBannerComponent } from "../../shared/components/not-found-banner/not-found-banner.component";
import { SimulationControlPanelComponent } from "./simulation-control-panel/simulation-control-panel.component";
import { SimulationStatus } from '../../models/simulation-status/simulation-status';
import { RealTimeLogsComponent } from "./real-time-logs/real-time-logs.component";
import { AddVendorFormComponent } from "./add-vendor-form/add-vendor-form.component";
import { AddCustomerFormComponent } from "./add-customer-form/add-customer-form.component";

@Component({
  selector: 'app-launch-simulation',
  standalone: true,
  imports: [NotFoundBannerComponent, SimulationControlPanelComponent, RealTimeLogsComponent, AddVendorFormComponent, AddCustomerFormComponent],
  templateUrl: './launch-simulation.component.html',
  styleUrl: './launch-simulation.component.css'
})
export class LaunchSimulationComponent {
  isSimulationReady: boolean | null = null;
  errorMessage: string | null = null;
  simulationStatus: SimulationStatus | null = null;
  realTimeUpdates: any[] = [];
  isAddCustomerActive: boolean = false;
  isAddVendorActive: boolean = false;
  isStarted: boolean = false;

  constructor(private launchSimulationService: LaunchSimulationService) {}

  ngOnInit(): void {
    this.checkSimulationStatus();
  }

  handleStartStopToggle(isStarted: boolean): void {
    this.isStarted = isStarted;
    console.log('Simulation Started:', this.isStarted);
  }

  handleCustomerToggle(isActive: boolean): void {
    this.isAddCustomerActive = isActive;
    console.log('Customer Button Active:', this.isAddCustomerActive);
  }

  handleVendorToggle(isActive: boolean): void {
    this.isAddVendorActive = isActive;
    console.log('Vendor Button Active:', this.isAddVendorActive);
  }

  private checkSimulationStatus(): void {
    this.launchSimulationService.checkSimulationReady().subscribe({
      next: (response: ApiResponse<SimulationStatus>) => {
        if (response.success && response.data) {
          this.isSimulationReady = true;
          this.simulationStatus = response.data;
        } else {
          this.isSimulationReady = false;
          this.errorMessage = response.message || 'Simulation not ready.';
        }
      },
      error: (err: { message: string }) => {
        this.isSimulationReady = false;
        this.errorMessage = err.message || 'An error occurred.';
      },
    });
  }




 
}

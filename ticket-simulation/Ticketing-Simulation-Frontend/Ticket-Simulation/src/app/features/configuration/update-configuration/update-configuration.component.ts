import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SimulationConfig } from '../../../models/simulation-config/simulation-config.model';
import { ConfigurationService } from '../../../services/configuration.service';
import { AlertService } from '../../../services/alert/alert.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-update-configuration',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-configuration.component.html',
  styleUrl: './update-configuration.component.css'
})
export class UpdateConfigurationComponent {
  config: SimulationConfig = {
    totalTickets: 0,
    ticketReleaseRate: 0,
    customRetrievalRate: 0,
    maxTicketCapacity: 0
  };

  constructor(
    private configurationService: ConfigurationService,
    private alertService: AlertService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchConfiguration();
  }

  // Fetch existing configuration
  fetchConfiguration(): void {
    this.configurationService.getConfiguration().subscribe({
      next: (response) => {
        if (response.success && response.data) {
          this.config = response.data;
        } else {
      
        }
      },
      error: (err) => {
        this.alertService.showAlert('Error fetching configuration.', 'error');
        console.error(err);
      }
    });
  }

  // Update the configuration
  onSubmit(): void {
    let errorMessage = '';

    // Validate each configuration attribute
    if (this.config.totalTickets < 0) {
      errorMessage = 'Total Tickets cannot be negative.';
    } else if (this.config.ticketReleaseRate < 0) {
      errorMessage = 'Ticket Release Rate cannot be negative.';
    } else if (this.config.customRetrievalRate < 0) {
      errorMessage = 'Custom Retrieval Rate cannot be negative.';
    } else if (this.config.maxTicketCapacity < 0) {
      errorMessage = 'Max Ticket Capacity cannot be negative.';
    }
  
    // If there is an error, show the alert and return
    if (errorMessage) {
      this.alertService.showAlert(errorMessage, 'error');
      return;
    }
  
    // Proceed with updating the configuration
    this.configurationService.postConfiguration(this.config).subscribe({
      next: (response) => {
        if (response.success) {
          this.alertService.showAlert('Configuration updated successfully!', 'success');
          setTimeout(() => {
            window.location.reload();
          }, 2000);
        } else {
          this.alertService.showAlert('Failed to update configuration.', 'info');
        }
      },
      error: (err) => {
        this.alertService.showAlert('Error updating configuration.', 'info');
        console.error(err);
      }
    })
  }
}

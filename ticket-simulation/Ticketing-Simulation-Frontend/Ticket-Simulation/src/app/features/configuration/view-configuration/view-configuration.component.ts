import { Component, OnInit } from '@angular/core';
import { ApiResponse } from '../../../models/api-response/api-response.model';
import { SimulationConfig } from '../../../models/simulation-config/simulation-config.model';
import { ConfigurationService } from '../../../services/configuration.service';

@Component({
  selector: 'app-view-configuration',
  standalone: true,
  imports: [],
  templateUrl: './view-configuration.component.html',
  styleUrl: './view-configuration.component.css',
})
export class ViewConfigurationComponent implements OnInit {
  config: SimulationConfig = {
    totalTickets: 0,
    ticketReleaseRate: 0,
    customRetrievalRate: 0,
    maxTicketCapacity: 0,
  };

  constructor(private configurationService: ConfigurationService) {}

  ngOnInit(): void {
    this.fetchConfig();
  }

  fetchConfig(): void {
    this.configurationService.getConfiguration().subscribe({
      next: (response: ApiResponse<SimulationConfig>) => {
        if (response.success && response.data) {
          this.config = response.data;
          console.log('Fetched configuration:', this.config);
        } else {
          console.error('Failed to fetch configuration:', response.message);
        }
      },
      error: (err: any) => {
        console.error('Error fetching configuration:', err);
      },
    });
  }
}
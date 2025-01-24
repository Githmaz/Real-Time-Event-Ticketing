import { Component } from '@angular/core';
import { BannerComponent } from "../../shared/components/banner/banner.component";
import { SearchBarComponent } from '../../shared/components/search-bar/search-bar.component';
import { CustomerDashboardData } from '../models/customer-dashboard-data.model';
import { DashboardService } from '../service/dashboard.service';
import { CardGridComponent } from '../components/card-grid/card-grid.component';
import { Events } from '../../models/event.model';
import { NotFoundBannerComponent } from "../../shared/components/not-found-banner/not-found-banner.component";
import { UserStateService } from '../../services/user-state/user-state.service';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-customer-dashboard',
  standalone: true,
  imports: [CardGridComponent, BannerComponent, SearchBarComponent, NotFoundBannerComponent],
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.css']
})
export class CustomerDashboardComponent {
  dashboardData: CustomerDashboardData | null = null; 
  filteredEvents: Events[] = [];
  loading: boolean = true;
  error: string | null = null; 

  constructor(private readonly dashboardService: DashboardService,private readonly userStateService: UserStateService,private readonly tokenService:TokenService) {}

  ngOnInit() {
    this.loadDashboardData();
  }

  loadDashboardData() {
    this.dashboardService.getCustomerDashboardData().subscribe({
      next: (data) => {
        this.dashboardData = data;
        this.filteredEvents = data.eventList; 
        this.userStateService.setUser(data.user); 
        this.loading = false;
        this.error = null; 
      },
      error: (err) => {
        this.tokenService.clearToken();
        console.error('Error fetching dashboard data:', err);
        this.loading = false;
        if (err.status === 404) {
          this.error = '404 - Data Not Found';
        } else {
          this.error = 'An unexpected error occurred. Please try again later.';
        }
      },
    });
  }

  // Function to handle search events
  filterEvents(query: string) {
    this.filteredEvents = this.dashboardData?.eventList.filter(event =>
      event.eventName.toLowerCase().startsWith(query.toLowerCase())
    ) || [];
  }
}
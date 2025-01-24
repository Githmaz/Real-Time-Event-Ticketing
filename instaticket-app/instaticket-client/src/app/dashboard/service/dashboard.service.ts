import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApiService } from '../../services/api-service/api.service';
import { CustomerDashboardData } from '../models/customer-dashboard-data.model';
import { ApiResponse } from '../../models/api-response.model';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private readonly baseUrl = '/dashboard';

  constructor(private apiService: ApiService) {}

  // Fetch customer dashboard data
  getCustomerDashboardData(): Observable<CustomerDashboardData> {
    return this.apiService
      .get<ApiResponse<CustomerDashboardData>>(`${this.baseUrl}/customer`)
      .pipe(
        map((response) => {
          if (response.success && response.data) {
            return response.data;
          } else {
            console.log("yeah")
            throw new Error(response.message || 'Failed to fetch customer dashboard data');
          }
        })
      );
  }
}

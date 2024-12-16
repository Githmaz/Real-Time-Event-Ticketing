import { HttpClient,HttpClientModule} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerDashboardData } from '../models/customer-dashboard-data.model';
import { Observable, map } from 'rxjs';
import { ApiResponse } from '../../models/api-response.model';
import { sourceMapsEnabled } from 'process';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private apiUrl = 'http://localhost:8080/dashboard/customer'; // Replace with your actual backend URL

  constructor(private http: HttpClient) {}

  getCustomerDashboardData(): Observable<CustomerDashboardData> {
    return this.http.get<ApiResponse<CustomerDashboardData>>(this.apiUrl).pipe(
      map((response) => {
        if (response.success && response.data) {
          return response.data;
        } else {
          throw new Error(response.message || 'Failed to fetch data');
        }
      })
    );
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiResponse } from '../../models/api-response/api-response.model';
import { Observable } from 'rxjs';
import { SimulationStatus } from '../../models/simulation-status/simulation-status';
import { Vendor } from '../../models/vendor/vendor.model';
import { Customer } from '../../models/customer/customer.model';

@Injectable({
  providedIn: 'root'
})
export class LaunchSimulationService {
  private readonly apiBaseUrl = 'http://localhost:8080/simulation'; // Base API URL

  constructor(private http: HttpClient) {}

  /**
   * Check if the simulation is ready to start.
   * @returns Observable of ApiResponse<boolean>
   */
  checkSimulationReady(): Observable<ApiResponse<SimulationStatus>> {
    return this.http.get<ApiResponse<SimulationStatus>>(`${this.apiBaseUrl}/ready`);
  }

  /**
   * Start the simulation.
   * @returns Observable of ApiResponse<string>
   */
  startSimulation(): Observable<ApiResponse<string>> {
    return this.http.post<ApiResponse<string>>(`${this.apiBaseUrl}/start`, {});
  }

  /**
   * Stop the simulation.
   * @returns Observable of ApiResponse<string>
   */
  stopSimulation(): Observable<ApiResponse<string>> {
    return this.http.post<ApiResponse<string>>(`${this.apiBaseUrl}/stop`, {});
  }


  /**
   * Adds a new customer to the simulation.
   * @param customer The customer data to add.
   * @returns Observable of the API response.
   */
  addCustomer(customer: Customer): Observable<ApiResponse<string>> {
    return this.http.post<ApiResponse<string>>(`${this.apiBaseUrl}/add/customer`, customer);
  }

  /**
   * Adds a new vendor to the simulation.
   * @param vendor The vendor data to add.
   * @returns Observable of the API response.
   */
  addVendor(vendor: Vendor): Observable<ApiResponse<string>> {
    return this.http.post<ApiResponse<string>>(`${this.apiBaseUrl}/add/vendor`, vendor);
  }

}

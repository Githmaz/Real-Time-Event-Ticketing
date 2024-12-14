import { Injectable } from '@angular/core';
import { Customer } from '../models/customer/customer.model';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../models/api-response/api-response.model';
import { Observable } from 'rxjs';
import { SimulationInitializedData } from '../models/simulation-initailized-data/simulation-initialized-data.model';
import { Vendor } from '../models/vendor/vendor.model';

@Injectable({
  providedIn: 'root'
})
export class SimulationService {
  
  private readonly apiUrl = 'http://localhost:8080/simulation'; // Base URL for simulation API

  constructor(private http: HttpClient) {}

  // Start simulation with the given configuration
  startSimulation(payload: SimulationInitializedData): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(`${this.apiUrl}/initialize`, payload);
  }
  startSimulationWithManualData(request: {
    customers: Customer[];
    vendors: Vendor[];
  }): Observable<ApiResponse<string>> {
    return this.http.post<ApiResponse<string>>(`${this.apiUrl}/custom/initialize`, request);
  }
  
}

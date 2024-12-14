import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api-response/api-response.model';
import { SimulationConfig } from '../models/simulation-config/simulation-config.model';


@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  private readonly apiUrl = 'http://localhost:8080/simulation/config'; // API URL

  constructor(private http: HttpClient) {}

  
  // Fetch the configuration from the backend
  getConfiguration(): Observable<ApiResponse<SimulationConfig>> {
    return this.http.get<ApiResponse<SimulationConfig>>(this.apiUrl);
  }
  
  // Post simulation configuration to the backend
  postConfiguration(data: SimulationConfig): Observable<ApiResponse<SimulationConfig>> {
    return this.http.post<ApiResponse<SimulationConfig>>(this.apiUrl, data);
  }
 
}
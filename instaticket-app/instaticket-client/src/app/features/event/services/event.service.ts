import { Injectable } from '@angular/core';
import { ApiResponse } from '../../../models/api-response.model';
import { ApiService } from '../../../services/api-service/api.service';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private readonly baseUrl = '/event';
  constructor(private readonly apiService:ApiService) {
  }
  
// Fetch event details by ID
getEventById(eventId: string): Observable<any> {
  return this.apiService
    .get<ApiResponse<any>>(`${this.baseUrl}/${eventId}`)
    .pipe(
      map((response) => {
        if (response.success && response.data) {
          return response.data; 
        } else {
          console.error('Error fetching event:', response.message);
          throw new Error(response.message || 'Failed to fetch event details');
        }
      })
    );
}
}

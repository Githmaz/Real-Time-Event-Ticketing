import { Injectable } from '@angular/core';
import { TicketCart } from '../../../models/ticket-cart';
import { Router } from '@angular/router';
import { ApiService } from '../../../services/api-service/api.service';
import { Observable, map } from 'rxjs';
import { ApiResponse } from '../../../models/api-response.model';

@Injectable({
  providedIn: 'root'
})
export class ShorppingCartService {
  ticketCart:TicketCart | null = null; 
  private readonly baseUrl = '/Cart';

  constructor(private readonly router:Router,private readonly apiService: ApiService) { }

 
// Fetch event details by ID
getShoppingCartService(packageId : String): Observable<any> {
  return this.apiService
    .get<ApiResponse<any>>(`${this.baseUrl}/${packageId}`)
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

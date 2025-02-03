import { Injectable } from '@angular/core';
import { ApiResponse } from '../../../models/api-response.model';
import { ApiService } from '../../../services/api-service/api.service';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private readonly baseUrl = '/event';
  constructor(private readonly apiService:ApiService) {
  }
  // fetch event details
}

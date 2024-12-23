import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApiService } from '../../services/api-service/api.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {  
  private readonly loginEndpoint = '/auth/login';
  constructor(private readonly apiService: ApiService) {}

  login(credentials: { username: string; password: string }): Observable<string> {
    return this.apiService.post<{ success: boolean; message: string; data: string }>(
      this.loginEndpoint,
      credentials
    ).pipe(
      map((response) => {
        if (response.success && response.data) {
          return response.data; 
        } else {
          throw new Error(response.message || 'Login failed');
        }
      })
    );
  }
  
}
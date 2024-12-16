import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {  
  private loginUrl = 'http://localhost:8080/auth/login'; 

  constructor(private http: HttpClient) {}
  login(credentials: { username: string; password: string }): Observable<string> {
    return this.http.post<{ success: boolean; message: string; data: string }>(this.loginUrl, credentials).pipe(
      map((response) => {
        console.log(response.data)
        if (response.success && response.data) {
          return response.data; 
        } else {
          throw new Error(response.message || 'Login failed');
        }
      })
    );
  }
  
}
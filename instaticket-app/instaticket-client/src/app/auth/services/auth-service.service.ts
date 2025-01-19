import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ApiService } from '../../services/api-service/api.service';
import { User } from '../../models/user.model';

interface Payload {
  user: User;
}

type UserPayload = {
  user: {
    username: string;
    password: string;
    email: string;
    name: string;
    userRole: string;
  };
};


@Injectable({
  providedIn: 'root',
})


export class AuthService {  
  private readonly loginEndpoint = '/auth/login';
  private readonly checkEmailEndpoint = '/user/check-email';
  private readonly checkUsernameEndpoint = '/user/check-username';
  private readonly registerEndpoint = '/user/register';

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

 // Check Email Availability
 checkEmail(email: string): Observable<boolean> {
  const url = `${this.checkEmailEndpoint}?email=${email}`;
  return this.apiService.get<{ success: boolean; message: string; data: boolean }>(url).pipe(
    map((response) => {
      if (response.success) {
        return response.data; 
      } else {
        throw new Error(response.message || 'Error checking email availability');
      }
    })
  );
}

// Check Username Availability
checkUsername(username: string): Observable<boolean> {
  const url = `${this.checkUsernameEndpoint}?username=${username}`;
  return this.apiService.get<{ success: boolean; message: string; data: boolean }>(url).pipe(
    map((response) => {
      if (response.success) {
        return response.data; 
      } else {
        throw new Error(response.message || 'Error checking username availability');
      }
    })
  );
}

  // Register method
  register(user: Payload): Observable<boolean> {

    console.log('User object:',  user );
    return this.apiService.post<{ success: boolean; message: string; data:User }>(
      this.registerEndpoint,
      {user} 
    ).pipe(
      map((response) => {
        if (response.success) {
          return true; 
        } else {
          throw new Error(response.message || 'Registration failed');
        }
      })
    );
  }

}
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { TokenService } from '../services/token.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private tokenService: TokenService) {}

  canActivate(): boolean {
    const token = this.tokenService.getToken(); 
    if (token) {
      const userWantsToLogout = confirm('You are already logged in. Do you want to log out?');
      if (userWantsToLogout) {
        this.tokenService.clearToken(); 
        return true; 
      } else {
        this.router.navigate(['/dashboard']); 
        return false; 
      }
    }

    return true; 
  }
}
import { Component } from '@angular/core';
import { AuthService } from '../services/auth-service.service';
import { FormsModule } from '@angular/forms';
import { TokenService } from '../../services/token.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports:[FormsModule,RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  credentials = { username: '', password: '' };

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private router: Router 
  ) {}
  onSubmit() {
    this.authService.login(this.credentials).subscribe({
      next: (token) => {
        this.tokenService.saveToken(token);
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error('Login failed:', err.message);
      }
    });
  }
}

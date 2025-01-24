import { Component } from '@angular/core';
import { AuthService } from '../services/auth-service.service';
import { FormsModule } from '@angular/forms';
import { TokenService } from '../../services/token.service';
import { Router, RouterModule } from '@angular/router';
import { AlertService } from '../../services/alert-service/alert.service';

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
    private readonly authService: AuthService,
    private readonly tokenService: TokenService,
    private readonly router: Router,
    private readonly alertService: AlertService 
  ) {}


  onSubmit() {
    this.authService.login(this.credentials).subscribe({
      next: (token) => {
        this.tokenService.saveToken(token);
        this.alertService.showAlert('success', 'Login successful! Redirecting to dashboard...', 3000);
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.alertService.showAlert('error', 'Invalid username or password. Please try again.', 5000);
      }
    });
  }
}

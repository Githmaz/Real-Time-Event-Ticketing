import { Component } from '@angular/core';
import { NavbarComponent } from "./navbar/navbar.component";
import { LoginComponent } from "./auth/login/login.component";
import { RegisterComponent } from "./auth/register/register.component";
import { CustomerDashboardComponent } from './dashboard/customer-dashboard/customer-dashboard.component';
import { RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './services/auth.interceptor';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NavbarComponent,RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'ticketing-client';
}

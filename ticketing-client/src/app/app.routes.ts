import { Routes } from '@angular/router';
import { CustomerDashboardComponent } from './dashboard/customer-dashboard/customer-dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { registerDispatcher } from '@angular/core/primitives/event-dispatch';
import { RegisterComponent } from './auth/register/register.component';

export const routes: Routes = [
    { path: '', component: HomeComponent},
    { path: 'dashboard', component: CustomerDashboardComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },

];

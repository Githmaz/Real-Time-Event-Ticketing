import { Routes } from '@angular/router';
import { CustomerDashboardComponent } from './dashboard/customer-dashboard/customer-dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { registerDispatcher } from '@angular/core/primitives/event-dispatch';
import { RegisterComponent } from './auth/register/register.component';
import { AuthGuard } from './guards/auth.guard';
import { NotFoundPageComponent } from './not-found-page/not-found-page.component';
import { AlertMessageComponent } from './shared/components/alert-message/alert-message.component';

export const routes: Routes = [
    { path: '', component: NotFoundPageComponent},
    { path: 'dashboard', component: CustomerDashboardComponent },
    { path: 'login', component: LoginComponent, canActivate: [AuthGuard] },
    { path: 'register', component: RegisterComponent },
    { path: 'fkyou', component: AlertMessageComponent },
    { path: '**', component: NotFoundPageComponent },
];

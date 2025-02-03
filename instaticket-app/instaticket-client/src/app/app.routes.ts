import { Routes } from '@angular/router';
import { CustomerDashboardComponent } from './features/dashboard/customer-dashboard/customer-dashboard.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { AuthGuard } from './guards/auth.guard';
import { NotFoundPageComponent } from './features/not-found-page/not-found-page.component';
import { AlertMessageComponent } from './shared/components/alert-message/alert-message.component';
import { EventPageComponent } from './features/event/event-page/event-page.component';

export const routes: Routes = [
    { path: 'dashboard', component: CustomerDashboardComponent },
    { path: 'login', component: LoginComponent, canActivate: [AuthGuard] },
    { path: 'register', component: RegisterComponent },
    { path: 'fkyou', component: AlertMessageComponent },
    { path: 'event/:eventId', component: EventPageComponent },
    { path: '**', component: NotFoundPageComponent },
];

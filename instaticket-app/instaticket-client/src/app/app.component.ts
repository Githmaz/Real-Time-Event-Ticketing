import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./navbar/navbar.component";
import { AlertMessageComponent } from "./shared/components/alert-message/alert-message.component";
import { AlertService } from './services/alert-service/alert.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, AlertMessageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'instaticket-client';

  constructor(public alertService: AlertService) {}

  get alertType(): 'info' | 'error' | 'success' | 'warning' | 'dark' {
    return (this.alertService.alert?.type as 'info' | 'error' | 'success' | 'warning' | 'dark') || 'info'; // Default to 'info'
  }

  get alertMessage(): string {
    return this.alertService.alert?.message || ''; // Default to empty string
  }

  get hasAlert(): boolean {
    return !!this.alertService.alert; // Returns true if there is an alert
  }
 
}

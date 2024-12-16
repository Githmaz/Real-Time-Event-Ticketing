import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NotFoundBannerComponent } from './shared/components/not-found-banner/not-found-banner.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { ConfigurationComponent } from "./features/configuration/configuration.component";
import { AlertComponent } from "./shared/components/alert/alert.component";
import { SimulationControlPanelComponent } from "./features/launch-simulation/simulation-control-panel/simulation-control-panel.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NotFoundBannerComponent, NavbarComponent, ConfigurationComponent, AlertComponent, SimulationControlPanelComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Ticket-Simulation';
}

import { Routes } from '@angular/router';
import { ViewConfigurationComponent } from './features/configuration/view-configuration/view-configuration.component';
import { NotFoundPageComponent } from './features/not-found-page/not-found-page.component';
import { ConfigurationComponent } from './features/configuration/configuration.component';
import { HomeComponent } from './features/home/home.component';
import { SimulationComponent } from './features/simulation/simulation.component';
import { LaunchSimulationComponent } from './features/launch-simulation/launch-simulation.component';

export const routes: Routes = [
    { path: 'configuration', component: ConfigurationComponent },
    { path: 'home', component: HomeComponent},
    { path: 'simulation', component: SimulationComponent},
    { path: 'launch', component: LaunchSimulationComponent},
    { path: '**', component: NotFoundPageComponent },
];

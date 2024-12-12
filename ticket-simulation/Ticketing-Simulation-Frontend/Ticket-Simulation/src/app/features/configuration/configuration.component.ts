import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UpdateConfigurationComponent } from "./update-configuration/update-configuration.component";
import { ViewConfigurationComponent, } from "./view-configuration/view-configuration.component";

@Component({
  selector: 'app-configuration',
  standalone: true,
  imports: [FormsModule, UpdateConfigurationComponent, ViewConfigurationComponent],
  templateUrl: './configuration.component.html',
  styleUrl: './configuration.component.css'
})
export class ConfigurationComponent {

}

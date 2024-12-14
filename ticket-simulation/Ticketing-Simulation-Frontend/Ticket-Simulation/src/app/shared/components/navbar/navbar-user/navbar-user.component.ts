import { Component} from '@angular/core';
import { DarkModeService } from '../../../../services/dark-mode.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar-user',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './navbar-user.component.html',
  styleUrl: './navbar-user.component.css'
})
export class NavbarUserComponent {
  profilePreview :string = 'assets/img/defaultProfileImg.jpg';

  isDarkMode = false;

  constructor(private darkModeService: DarkModeService) {}

  toggleDarkMode(event: Event): void {
    const checkbox = event.target as HTMLInputElement;
    this.isDarkMode = checkbox.checked;
    this.darkModeService.toggleDarkMode(this.isDarkMode);
  }
}

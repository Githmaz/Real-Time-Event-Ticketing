import { Component,Output,Renderer2} from '@angular/core';
import { ThemeToggleComponent } from "../../components/theme-toggle/theme-toggle.component";
import { DarkModeService } from '../../services/dark-mode.service';

@Component({
  selector: 'app-navbar-user',
  standalone: true,
  imports: [],
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

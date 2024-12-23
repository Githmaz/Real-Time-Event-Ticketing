import { Component} from '@angular/core';
import { DarkModeService } from '../../services/dark-mode.service';
import { UserStateService } from '../../services/user-state/user-state.service';
import { User } from '../../models/user.model';
import { Router, RouterModule } from '@angular/router';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-navbar-user',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './navbar-user.component.html',
  styleUrl: './navbar-user.component.css'
})
export class NavbarUserComponent {
  profilePreview :string = 'assets/img/defaultProfileImg.jpg';
  user: User | null = null; 
  isDarkMode:boolean = false;
  isLogIn:boolean = false;

  constructor(
    private readonly darkModeService: DarkModeService,
    private readonly userStateService: UserStateService,
    private readonly tokenService: TokenService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.userStateService.user$.subscribe({
      next: (user) => {
        this.user = user; 
        this.isLogIn = this.isUserLogIn();
      },
    });
  }

  toggleDarkMode(event: Event): void {
    const checkbox = event.target as HTMLInputElement;
    this.isDarkMode = checkbox.checked;
    this.darkModeService.toggleDarkMode(this.isDarkMode);
  }

  logout():void{
    this.tokenService.clearToken();
    window.location.reload();
  }
  isUserLogIn():boolean{
    return this.tokenService.getToken() != null;
  }
}

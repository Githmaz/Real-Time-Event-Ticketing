import { Component } from '@angular/core';
import { ThemeToggleComponent } from "../../components/theme-toggle/theme-toggle.component";

@Component({
  selector: 'app-navbar-menu',
  standalone: true,
  imports: [ThemeToggleComponent],
  templateUrl: './navbar-menu.component.html',
  styleUrl: './navbar-menu.component.css'
})
export class NavbarMenuComponent {

}

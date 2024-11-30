import { Component } from '@angular/core';
import { NavbarMenuComponent } from "./navbar-menu/navbar-menu.component";
import { NavbarUserComponent } from "./navbar-user/navbar-user.component";
import { NavbarBrandComponent } from "./navbar-brand/navbar-brand.component";
import { NavbarSearchBarComponent } from "./navbar-search-bar/navbar-search-bar.component";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [NavbarMenuComponent, NavbarUserComponent, NavbarBrandComponent, NavbarSearchBarComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

}

import { Component } from '@angular/core';
import { ThemeToggleComponent } from "../../shared/components/theme-toggle/theme-toggle.component";
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar-menu',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './navbar-menu.component.html',
  styleUrl: './navbar-menu.component.css'
})
export class NavbarMenuComponent {
  activeMenu: string = 'home'; 
  setActiveMenu(menu: string): void {
    this.activeMenu = menu;
  }
}

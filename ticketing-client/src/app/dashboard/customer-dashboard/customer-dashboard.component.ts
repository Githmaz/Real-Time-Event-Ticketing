import { Component, Renderer2 } from '@angular/core';
import { DarkModeService } from '../../services/dark-mode.service';
import { CardGridComponent } from "../../components/card-grid/card-grid.component";
import { BannerComponent } from "../../components/banner/banner.component";

@Component({
  selector: 'app-customer-dashboard',
  standalone: true,
  imports: [CardGridComponent, BannerComponent],
  templateUrl: './customer-dashboard.component.html',
  styleUrl: './customer-dashboard.component.css'
})
export class CustomerDashboardComponent {
  cards = [
    {
      id: 1,
      name: 'Product 1',
      description: 'This is product 1 description',
      price: 10,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },
    {
      id: 2,
      name: 'Product 2',
      description: 'This is product 2 description',
      price: 20,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },{
      id: 1,
      name: 'Product 1',
      description: 'This is product 1 description',
      price: 10,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },
    {
      id: 2,
      name: 'Product 2',
      description: 'This is product 2 description',
      price: 20,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },{
      id: 1,
      name: 'Product 1',
      description: 'This is product 1 description',
      price: 10,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },
    {
      id: 2,
      name: 'Product 2',
      description: 'This is product 2 description',
      price: 20,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },{
      id: 1,
      name: 'Product 1',
      description: 'This is product 1 description',
      price: 10,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },
    {
      id: 2,
      name: 'Product 2',
      description: 'This is product 2 description',
      price: 20,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },{
      id: 1,
      name: 'Product 1',
      description: 'This is product 1 description',
      price: 10,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },
    {
      id: 2,
      name: 'Product 2',
      description: 'This is product 2 description',
      price: 20,
      imageUrl: 'https://readymadeui.com/images/product9.webp'
    },
    // Add more cards as needed
  ];
}

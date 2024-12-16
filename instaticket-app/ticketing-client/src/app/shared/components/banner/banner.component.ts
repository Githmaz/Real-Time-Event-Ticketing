import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-banner',
  standalone: true,
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent {
  @Input() imageSrc: string = ''; // Image source
  @Input() title: string = ''; // Title text
  @Input() description: string = ''; // Description text
  @Input() buttonText: string = ''; // Button text
  @Input() buttonLink: string = '#'; // Button link
}
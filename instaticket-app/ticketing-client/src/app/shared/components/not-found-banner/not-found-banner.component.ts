import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-not-found-banner',
  standalone: true,
  imports: [],
  templateUrl: './not-found-banner.component.html',
  styleUrl: './not-found-banner.component.css'
})
export class NotFoundBannerComponent {
  @Input() errorCode: string = '404'; // Default value
  @Input() title: string = "Something's missing.";
  @Input() message: string = "We couldn't find what you were looking for. Feel free to explore other options.";
  @Input() buttonText?: string; // Optional
  @Input() buttonLink?: string; // Optional
}
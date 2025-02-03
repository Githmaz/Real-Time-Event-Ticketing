import { Component } from '@angular/core';
import { NotFoundBannerComponent } from '../../shared/components/not-found-banner/not-found-banner.component';

@Component({
  selector: 'app-not-found-page',
  standalone: true,
  imports: [NotFoundBannerComponent],
  templateUrl: './not-found-page.component.html',
  styleUrl: './not-found-page.component.css'
})
export class NotFoundPageComponent {

}

import { Component, Input } from '@angular/core';
import { Events } from '../../../../models/event.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-event-banner',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './event-banner.component.html',
  styleUrl: './event-banner.component.css'
})
export class EventBannerComponent {
  @Input({ required: true }) event!: Events;
}


import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from '../services/event.service';
import { Events } from '../../../models/event.model';
import { EventBannerComponent } from "../components/event-banner/event-banner.component";
import { PackageTableComponent } from "../components/package-table/package-table.component";

@Component({
  selector: 'app-event-page',
  standalone: true,
  imports: [EventBannerComponent,PackageTableComponent],
  templateUrl: './event-page.component.html',
  styleUrl: './event-page.component.css'
})
export class EventPageComponent {
  event:Events = new Events();
  constructor(private readonly route:ActivatedRoute, private readonly eventService: EventService){}

  ngOnInit():void{
    this.route.params.subscribe(params => {
      this.fetchEventDetails(params['eventId']);
    })
  }

  fetchEventDetails(eventId: string): void {
    this.eventService.getEventById(eventId).subscribe({
      next: (event) => {
        this.event  = event;
        console.log(this.event)
      },
      error: (error) => {
        console.error('Error fetching event:', error.message);
      }
    });
  }

}

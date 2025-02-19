import { Component, Input, OnInit } from '@angular/core';
import { Events } from '../../../../models/event.model';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent  {
  @Input() event!: Events;
  
  constructor(private readonly router: Router) {}

 

  navigateToEvent() {
    this.router.navigate(['/event', this.event.eventId]); 
  }
}
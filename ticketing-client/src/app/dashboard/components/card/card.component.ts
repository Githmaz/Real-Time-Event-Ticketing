import { Component, Input, OnInit } from '@angular/core';
import { Events } from '../../../models/event.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css'
})
export class CardComponent implements OnInit {
  @Input() event!: Events;

  ngOnInit() {
    if (!this.event.eventImage) {
      this.event.eventImage = "assets/img/default-event-img.jpg";
    }
  }
}
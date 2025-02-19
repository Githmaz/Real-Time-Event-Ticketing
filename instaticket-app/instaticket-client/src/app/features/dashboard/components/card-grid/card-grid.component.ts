import { Component, Input, OnInit } from '@angular/core';
import { CardComponent } from "../card/card.component";
import { Events } from '../../../../models/event.model';

@Component({
  selector: 'app-card-grid',
  standalone: true,
  imports: [CardComponent],
  templateUrl: './card-grid.component.html',
  styleUrl: './card-grid.component.css'
})
export class CardGridComponent {
  @Input() events!: Events[];
}

import { Component } from '@angular/core';

@Component({
  selector: 'app-ticket-package-card',
  standalone: true,
  imports: [],
  templateUrl: './ticket-package-card.component.html',
  styleUrl: './ticket-package-card.component.css'
})
export class TicketPackageCardComponent {
  ticketPackage = {
    image: 'assets/img/default-event-img.jpg', // Placeholder image
    title: 'Gold Package', // Ticket package title
    availableTickets: 8, // Remaining tickets
    totalTickets: 50, // Total tickets in the package
    price: 150, // Price per ticket
  }; 
}

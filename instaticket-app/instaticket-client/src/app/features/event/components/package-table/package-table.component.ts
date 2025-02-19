import { Component, Input } from '@angular/core';
import { TicketPackage } from '../../../../models/ticket-package';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-package-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './package-table.component.html',
  styleUrl: './package-table.component.css'
})
export class PackageTableComponent {
 @Input({ required: true }) ticketPackage!: TicketPackage[];

  constructor(private readonly router:Router ,private  readonly route: ActivatedRoute){}

 bookTicket(packageId: string): void {
  this.router.navigate([`package`, packageId], { relativeTo: this.route });
  }

  getTicketColor(availableTickets: number): string {
    if (availableTickets < 10) return 'text-red-500'; // 🔴 Less than 10
    if (availableTickets >= 10 && availableTickets < 60) return 'text-yellow-500'; // 🟡 Between 10-59
    return 'text-green-500'; // 🟢 60 or more
  }
}

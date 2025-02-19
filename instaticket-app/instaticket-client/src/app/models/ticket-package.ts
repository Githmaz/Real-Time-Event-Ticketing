import { Ticket } from './ticket.model';

export class TicketPackage {
  packageId: string;
  packageType: string;
  price: number;
  ticketCount: number;
  availableTickets: number;
  tickets: Ticket[];

  constructor(data?: Partial<TicketPackage>) {
    this.packageId = data?.packageId ?? '';
    this.packageType = data?.packageType ?? 'General';
    this.price = data?.price ?? 0;
    this.ticketCount = data?.ticketCount ?? 0;
    this.availableTickets = data?.availableTickets ?? 0;
    this.tickets = data?.tickets?.map(ticket => new Ticket(ticket)) ?? [];
  }
}

import { TicketPackage } from "./ticket-package";

export class Events {
  id: number;
  eventId: string;
  eventName: string;
  eventDateTime: string;
  eventCreatedDate: string;
  location: string;
  vendorId: string;
  vendorName: string;
  ticketPackages: TicketPackage[];
  // ticketPackages: any | null;
  eventImage?: string;

  constructor(data?: Partial<Events>) {
    this.id = data?.id ?? 0;
    this.eventId = data?.eventId ?? '';
    this.eventName = data?.eventName ?? 'Untitled Event';
    this.vendorName = data?.vendorName ??'';
    this.eventDateTime = data?.eventDateTime ?? new Date().toISOString();
    this.eventCreatedDate = data?.eventCreatedDate ?? new Date().toISOString();
    this.location = data?.location ?? 'Unknown Location';
    this.vendorId = data?.vendorId ?? '';
    this.ticketPackages = data?.ticketPackages ?? [];
    this.eventImage = data?.eventImage ?? 'assets/img/default-event-img.jpg';
  }

  getFormattedDate(): string {
    return new Date(this.eventDateTime).toLocaleDateString();
  }

  getFormattedTime(): string {
    return new Date(this.eventDateTime).toLocaleTimeString();
  }
}

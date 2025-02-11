import { TicketPackage } from "./ticket-package";

export interface TicketCart {
    eventImg: string;  // Image URL of the event
    ticketPackage: TicketPackage; // Ticket package details
    quantity: number;  // Number of tickets
    tax: number;  // Tax amount
    discount: number; // Discount applied
    total: number; // Total price after tax and discount
}
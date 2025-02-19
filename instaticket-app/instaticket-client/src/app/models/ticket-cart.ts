import { TicketCartItem } from "./ticket-cart-item";

export interface TicketCart {
    eventImg: string;  // Image URL of the event
    ticketCartItem:TicketCartItem // Ticket package details
    quantity: number;  // Number of tickets
    tax: number;  // Tax amount
    discount: number; // Discount applied
    total: number; // Total price after tax and discount
}
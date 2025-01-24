
import { Customer } from './customer.model';
import { Events } from './event.model';

export interface Ticket {
  id: number;
  ticketId: string;
  ticketType: string;
  available: boolean;
  event: Events;
  customer: Customer;
  soldDate: string; // ISO format
}
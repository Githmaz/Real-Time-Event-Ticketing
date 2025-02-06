import { Customer } from './customer.model';
import { Events } from './event.model';

export class Ticket {
  id: number;
  ticketId: string;
  ticketType: string;
  available: boolean;
  event?: Events; // ✅ Optional to avoid circular dependency issues
  // customer?: Customer | null; // ✅ Can be null if unsold
  // soldDate?: string; // ✅ Can be undefined if not sold

  constructor(data?: Partial<Ticket>) {
    this.id = data?.id ?? 0;
    this.ticketId = data?.ticketId ?? '';
    this.ticketType = data?.ticketType ?? 'General';
    this.available = data?.available ?? true;
    this.event = data?.event ? new Events(data.event) : undefined;
  }
}

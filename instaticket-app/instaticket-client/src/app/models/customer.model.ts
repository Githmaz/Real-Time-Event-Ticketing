import { CustomerPlan } from "./customer-plan.model";
import { User } from "./user.model";
import { Ticket } from "./ticket.model";

export interface Customer extends User {
  purchasedTickets: Ticket[];
  customerPlan: CustomerPlan;
}
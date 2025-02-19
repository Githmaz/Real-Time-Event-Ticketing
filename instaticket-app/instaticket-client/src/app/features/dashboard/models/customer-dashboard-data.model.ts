import { Events } from "../../../models/event.model";
import { User } from "../../../models/user.model";

export interface CustomerDashboardData {
    user : User;
    eventList : Events[]
  }
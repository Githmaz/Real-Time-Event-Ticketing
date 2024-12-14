package edu.iit.ticketingservice.service;


import edu.iit.ticketingservice.dto.ticket.TicketResponse;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.CustomerPlan;

import java.util.List;

public interface CustomerService {
    List<TicketResponse> getBookedTicketsForCustomer();
    Customer getAuthenticatedCustomer();
    CustomerPlan getCustomerPlan();
    Customer setCustomerPlan(CustomerPlan customerPlan);
    List<CustomerPlan> getAllCustomerPlans(); // Retrieve all plans
}

package edu.iit.ticketingservice.service;


import edu.iit.ticketingservice.dto.ticket.TicketResponse;
import edu.iit.ticketingservice.dto.users.Customer;

import java.util.List;

public interface CustomerService {
    List<TicketResponse> getBookedTicketsForCustomer();
    Customer getAuthenticatedCustomer();
}

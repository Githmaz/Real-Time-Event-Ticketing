package edu.iit.ticketingservice.service;


import edu.iit.ticketingservice.dto.ticket.TicketResponse;

import java.util.List;

public interface CustomerService {
    List<TicketResponse> getBookedTicketsForCustomer();
}

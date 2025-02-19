package edu.iit.ticketingservice.service;


import edu.iit.ticketingservice.dto.shoppingCart.TicketCart;
import edu.iit.ticketingservice.dto.ticket.TicketResponse;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.CustomerPlan;

import java.util.List;

public interface TicketCartService {
    TicketCart getTicketCart(String ticketId);
}

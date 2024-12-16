package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.ticket.TicketRequest;
import edu.iit.ticketingservice.dto.ticket.TicketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    List<TicketResponse> bookTicket(TicketRequest request);

}

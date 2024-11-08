package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dao.TicketEntity;
import edu.iit.ticketingservice.dto.TicketRequest;
import edu.iit.ticketingservice.dto.TicketResponse;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {
//    List<Ticket> getAllTickets();
TicketResponse createTicket(TicketRequest ticket);

//    List<Ticket> getTicketsByPackageId(String packageId);
}

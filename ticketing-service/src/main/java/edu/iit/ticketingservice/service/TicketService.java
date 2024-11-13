package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dao.TicketEntity;
import edu.iit.ticketingservice.dto.TicketRequest;
import edu.iit.ticketingservice.dto.TicketResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    List<TicketResponse> bookTicket(TicketRequest request);

}

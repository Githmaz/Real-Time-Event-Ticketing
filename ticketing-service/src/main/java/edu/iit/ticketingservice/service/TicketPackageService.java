package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.TicketPackage;
import edu.iit.ticketingservice.dto.TicketPackageRequest;
import org.springframework.stereotype.Service;

@Service
public interface TicketPackageService {
    TicketPackage createTicketPackage(TicketPackageRequest ticketPackageRequest);
//    List<TicketPackage> getAllTicketPackagesByEvent(Event event);

}

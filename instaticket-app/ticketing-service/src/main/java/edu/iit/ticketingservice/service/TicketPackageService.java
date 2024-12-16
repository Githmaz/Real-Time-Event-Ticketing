package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.ticketPackage.TicketPackage;
import edu.iit.ticketingservice.dto.ticketPackage.TicketPackageRequest;
import org.springframework.stereotype.Service;

@Service
public interface TicketPackageService {
    TicketPackage createTicketPackage(TicketPackageRequest ticketPackageRequest);
//    List<TicketPackage> getAllTicketPackagesByEvent(Event event);

}

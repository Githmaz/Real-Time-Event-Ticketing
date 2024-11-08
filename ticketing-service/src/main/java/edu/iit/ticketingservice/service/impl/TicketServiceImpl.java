package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.TicketEntity;
import edu.iit.ticketingservice.dao.TicketPackageEntity;
import edu.iit.ticketingservice.dto.TicketRequest;
import edu.iit.ticketingservice.dto.TicketResponse;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.CustomerRepository;
import edu.iit.ticketingservice.repository.TicketPackageRepository;
import edu.iit.ticketingservice.repository.TicketRepository;
import edu.iit.ticketingservice.service.TicketService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketPackageRepository ticketPackageRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;  // Inject ModelMapper




    @Override
    public TicketResponse createTicket(TicketRequest request) {
        // Fetch associated package
        TicketPackageEntity ticketPackage = ticketPackageRepository.findByPackageId(request.getPackageId())
                .orElseThrow(() -> new BusinessException(ErrorType.PACKAGE_NOT_FOUND));

        // Fetch associated customer
        CustomerEntity customer = customerRepository.findByUserId(request.getCustomerId())
                .orElseThrow(() -> new BusinessException(ErrorType.CUSTOMER_NOT_FOUND));

        // Ensure tickets are available
        if (ticketPackage.getAvailableTickets() <= 0) {
            throw new BusinessException(ErrorType.TICKETS_SOLD_OUT);
        }

        // Create and populate new TicketEntity
        TicketEntity ticket = new TicketEntity();
        ticket.setTicketType(ticketPackage.getPackageType());
        ticket.setAvailable(false);  // Ticket is now booked
        ticket.setTicketPackage(ticketPackage);
        ticket.setEvent(ticketPackage.getEvent());
        ticket.setCustomer(customer);
        ticket.setSoldDate(LocalDateTime.now());
        ticket.generateEventId();

        // Save ticket and update available ticket count
        TicketEntity savedTicket = ticketRepository.save(ticket);
        ticketPackage.setAvailableTickets(ticketPackage.getAvailableTickets() - 1);
        ticketPackageRepository.save(ticketPackage);

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicketId(savedTicket.getTicketId());
        ticketResponse.setEventId(savedTicket.getEvent().getEventId());
        ticketResponse.setTicketType(savedTicket.getTicketType());
        ticketResponse.setCustomer(savedTicket.getCustomer());
        ticketResponse.setSoldDate(savedTicket.getSoldDate());
        ticketResponse.setTicketPackageId(savedTicket.getTicketPackage().getPackageId());

        // Convert TicketEntity to Ticket DTO
        return ticketResponse;
    }


//    // Persist buffered tickets without clearing them
//    @Scheduled(fixedRate = 60000) // Save every 60 seconds
//    public void persistBufferedTickets() {
//        ticketRepository.saveAll(ticketBuffer);
//        // Do not clear the buffer to keep all tickets in memory
//    }

    public List<TicketEntity> convertIterableToList(Iterable<TicketEntity> iterable) {
        List<TicketEntity> list = new ArrayList<>();
        for (TicketEntity ticket : iterable) {
            list.add(ticket);
        }
        return list;
    }
}


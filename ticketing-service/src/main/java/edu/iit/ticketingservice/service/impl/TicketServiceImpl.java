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
import edu.iit.ticketingservice.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
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
    private HttpServletRequest httpServletRequest;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ModelMapper modelMapper;  // Inject ModelMapper


    @Override
    public List<TicketResponse> bookTicket(TicketRequest request) {
        // Fetch ticket package
        TicketPackageEntity ticketPackage = ticketPackageRepository.findByPackageId(request.getPackageId())
                .orElseThrow(() -> new BusinessException(ErrorType.PACKAGE_NOT_FOUND));

        // Get token from Http Request
        String token = jwtUtil.getTokenFromRequest(this.httpServletRequest);

        // Extract userId from the token
        String userId = jwtUtil.extractUserId(token);

        // Fetch customer making the booking
        CustomerEntity customer = customerRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorType.CUSTOMER_NOT_FOUND));

        // Check ticket availability
        if (ticketPackage.getAvailableTickets() < request.getTicketCount()) {
            throw new BusinessException("Only " + ticketPackage.getAvailableTickets() + " tickets left for this package.");
        }


        List<TicketResponse> ticketResponses = new ArrayList<>();
        for (int i = 0; i < request.getTicketCount(); i++) {
            TicketEntity ticket = createTicketEntity(ticketPackage, customer);
            TicketEntity savedTicket = ticketRepository.save(ticket);
            ticketPackage.setAvailableTickets(ticketPackage.getAvailableTickets() - 1);
            ticketResponses.add(convertToResponse(savedTicket));
        }

        ticketPackageRepository.save(ticketPackage);
        return ticketResponses;
    }


    private TicketEntity createTicketEntity(TicketPackageEntity ticketPackage, CustomerEntity customer) {
        TicketEntity ticket = new TicketEntity();
        ticket.setTicketType(ticketPackage.getPackageType());
        ticket.setAvailable(false);
        ticket.setTicketPackage(ticketPackage);
        ticket.setEvent(ticketPackage.getEvent());
        ticket.setCustomer(customer);
        ticket.setSoldDate(LocalDateTime.now());
        ticket.generateEventId();
        return ticket;
    }


    private TicketResponse convertToResponse(TicketEntity ticket) {
        TicketResponse response = new TicketResponse();
        response.setTicketId(ticket.getTicketId());
        response.setEventId(ticket.getEvent().getEventId());
        response.setTicketType(ticket.getTicketType());
        response.setCustomer(ticket.getCustomer());
        response.setSoldDate(ticket.getSoldDate());
        response.setTicketPackageId(ticket.getTicketPackage().getPackageId());
        return response;
    }
}


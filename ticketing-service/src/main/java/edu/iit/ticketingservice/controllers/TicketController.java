package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.ticket.TicketRequest;
import edu.iit.ticketingservice.dto.ticket.TicketResponse;
import edu.iit.ticketingservice.service.TicketService;
import edu.iit.ticketingservice.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private JwtUtil jwtUtil;

    // Book a ticket - restricted to CUSTOMER role
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/book")
    public ResponseEntity<ApiResponse<List<TicketResponse>>> bookTicket(@Valid @RequestBody TicketRequest request) {
        List<TicketResponse> bookedTicket = ticketService.bookTicket(request);
        ApiResponse<List<TicketResponse>> response = new ApiResponse<>(true, "Ticket successfully booked", bookedTicket);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}

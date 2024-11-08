package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dao.TicketEntity;
import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.TicketRequest;
import edu.iit.ticketingservice.dto.TicketResponse;
import edu.iit.ticketingservice.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Create a ticket (Booking a ticket)
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TicketResponse>> createTicket(@Valid @RequestBody TicketRequest request) {
        TicketResponse createdTicket = ticketService.createTicket(request);
        ApiResponse<TicketResponse> response = new ApiResponse<>(true, "Ticket successfully booked", createdTicket);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}

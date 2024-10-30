package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.TicketPackage;
import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.TicketPackageRequest;
import edu.iit.ticketingservice.service.TicketPackageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket-packages")
public class TicketPackageController {

    @Autowired
    private TicketPackageService ticketPackageService;

    // Create a new ticket package with tickets
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TicketPackage>> createTicketPackage(@Valid @RequestBody TicketPackageRequest request) {
        TicketPackage createdPackage = ticketPackageService.createTicketPackage(request);
        ApiResponse<TicketPackage> response = new ApiResponse<>(true, "Ticket package created with tickets", createdPackage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}

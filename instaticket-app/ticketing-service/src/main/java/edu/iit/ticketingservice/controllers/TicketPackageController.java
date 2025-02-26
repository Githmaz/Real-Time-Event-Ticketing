package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ticketPackage.TicketPackage;
import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.ticketPackage.TicketPackageRequest;
import edu.iit.ticketingservice.service.TicketPackageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/ticket-packages")
public class TicketPackageController {

    @Autowired
    private TicketPackageService ticketPackageService;

    // Create a new ticket package with tickets
    @PreAuthorize("hasRole('VENDOR')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TicketPackage>> createTicketPackage(@Valid @RequestBody TicketPackageRequest request) {
        ticketPackageService.createTicketPackage(request);
        ApiResponse<TicketPackage> response = new ApiResponse<>(true, "Ticket package created with tickets", null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}

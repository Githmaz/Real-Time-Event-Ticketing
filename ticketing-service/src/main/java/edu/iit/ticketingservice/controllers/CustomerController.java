package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.ticket.TicketResponse;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.CustomerPlan;
import edu.iit.ticketingservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/tickets/booked")
    public ResponseEntity<ApiResponse<List<TicketResponse>>> getBookedTicketsForCustomer() {
        List<TicketResponse> bookedTickets = customerService.getBookedTicketsForCustomer();
        ApiResponse<List<TicketResponse>> response = new ApiResponse<>(true, "Tickets retrieved successfully", bookedTickets);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/plan")
    public ResponseEntity<ApiResponse<CustomerPlan>> getCustomerPlan() {
        CustomerPlan customerPlan = customerService.getCustomerPlan();
        ApiResponse<CustomerPlan> response = new ApiResponse<>(true, "Customer plan retrieved successfully", customerPlan);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/plan/all")
    public ResponseEntity<ApiResponse<List<CustomerPlan>>> getAllCustomerPlans() {
        List<CustomerPlan> plans = customerService.getAllCustomerPlans();
        ApiResponse<List<CustomerPlan>> response = new ApiResponse<>(true, "Customer plans retrieved successfully", plans);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/plan")
    public ResponseEntity<ApiResponse<Customer>> updateCustomerPlan(@RequestBody CustomerPlan newPlan) {
        Customer updatedCustomer = customerService.setCustomerPlan(newPlan);
        ApiResponse<Customer> response = new ApiResponse<>(true, "Customer plan updated successfully", updatedCustomer);
        return ResponseEntity.ok(response);
    }

}

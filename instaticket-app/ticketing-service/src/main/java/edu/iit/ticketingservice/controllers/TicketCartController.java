package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.dashboardData.CustomerDashboardData;
import edu.iit.ticketingservice.dto.shoppingCart.TicketCart;
import edu.iit.ticketingservice.service.DashboardService;
import edu.iit.ticketingservice.service.TicketCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/event/ticket/cart")
public class TicketCartController {

    private static final Logger logger = LoggerFactory.getLogger(TicketCartController.class);

    @Autowired
    private TicketCartService ticketCartService;

    // Endpoint for Ticket Cart
    @GetMapping()
    public ResponseEntity<ApiResponse<TicketCart>> getCustomerDashboardData(@RequestParam String packageId) {
        logger.info("Fetching data for the customer Ticket Cart");
        TicketCart ticketCart = ticketCartService.getTicketCart(packageId);
        ApiResponse<TicketCart> response = new ApiResponse<>(true, "Ticket Cart data retrieved successfully", ticketCart);
        return ResponseEntity.ok(response);
    }

}

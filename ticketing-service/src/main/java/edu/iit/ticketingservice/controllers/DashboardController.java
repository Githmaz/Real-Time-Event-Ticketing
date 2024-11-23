package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.dashboardData.CustomerDashboardData;
import edu.iit.ticketingservice.service.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private DashboardService dashboardService;

    // Endpoint for customer dashboard data
    @GetMapping("/customer")
    public ResponseEntity<ApiResponse<CustomerDashboardData>> getCustomerDashboardData() {
        logger.info("Fetching data for the customer dashboard");
        CustomerDashboardData customerData = dashboardService.getCustomerDashboardData();
        ApiResponse<CustomerDashboardData> response = new ApiResponse<>(true, "Customer dashboard data retrieved successfully", customerData);
        return ResponseEntity.ok(response);
    }




}

package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.service.TaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/taxRate")
public class TaxController {

    private static final Logger logger = LoggerFactory.getLogger(TaxController.class);

    @Autowired
    private TaxService taxService;

    @GetMapping()
    public ResponseEntity<ApiResponse<Map<String,Double>>> getCustomerDashboardData() {
        double taxRate = taxService.getTaxRate();
        ApiResponse<Map<String,Double>> response = new ApiResponse<>(true, "Customer dashboard data retrieved successfully", Collections.singletonMap("taxRate", taxRate) );
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/set")
    public ResponseEntity<ApiResponse<Map<String, Double>>> setTaxRate(@RequestParam double taxRate) {
        taxService.setTaxRate(taxRate);
        ApiResponse<Map<String, Double>> response = new ApiResponse<>(true, "Tax rate updated successfully", Collections.singletonMap("taxRate", taxRate));
        return ResponseEntity.ok(response);
    }


}

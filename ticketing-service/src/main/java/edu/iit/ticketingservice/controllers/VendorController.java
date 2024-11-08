package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.Vendor;
import edu.iit.ticketingservice.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    VendorService vendorService;

    // Save vendor and return appropriate response
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Vendor>> saveVendor(@Valid @RequestBody Vendor vendor) {
        Vendor savedVendor = vendorService.createVendor(vendor);
        ApiResponse<Vendor> response = new ApiResponse<>(true, "Vendor created successfully.", savedVendor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    // Login by email and password
    @PostMapping("/login-by-email")
    public ResponseEntity<ApiResponse<Vendor>> getVendorByEmailAndPassword(@RequestBody Vendor vendor) {
        Vendor authenticatedVendor = vendorService.getVendorByEmailAndPassword(vendor.getEmail(), vendor.getPassword());
        ApiResponse<Vendor> response = new ApiResponse<>(true, "Login successful.", authenticatedVendor);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    // Login by username and password
    @PostMapping("/login-by-username")
    public ResponseEntity<ApiResponse<Vendor>> getVendorByUsernameAndPassword(@RequestBody Vendor vendor) {
        Vendor authenticatedVendor = vendorService.getVendorByUsernameAndPassword(vendor.getUsername(), vendor.getPassword());
        ApiResponse<Vendor> response = new ApiResponse<>(true, "Login successful.", authenticatedVendor);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}


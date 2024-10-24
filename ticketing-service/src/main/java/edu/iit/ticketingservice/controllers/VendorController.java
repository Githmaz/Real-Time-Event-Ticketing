package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.Vendor;
import edu.iit.ticketingservice.service.VendorService;
import jakarta.validation.Valid;
import lombok.experimental.SuperBuilder;
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
    public ResponseEntity<ApiResponse<VendorEntity>> saveVendor(@Valid @RequestBody Vendor vendor) {
        VendorEntity savedVendor = vendorService.createVendor(vendor);
        ApiResponse<VendorEntity> response = new ApiResponse<>(true, "Vendor created successfully.", savedVendor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    // Login by email and password
    @PostMapping("/login-by-email")
    public ResponseEntity<ApiResponse<VendorEntity>> getVendorByEmailAndPassword(@RequestBody Vendor vendor) {
        VendorEntity vendorEntity = vendorService.getVendorByEmailAndPassword(vendor.getEmail(), vendor.getPassword());
        if (vendorEntity != null) {
            ApiResponse<VendorEntity> response = new ApiResponse<>(true, "Login successful.", vendorEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<VendorEntity> response = new ApiResponse<>(false, "Invalid email or password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    // Login by username and password
    @PostMapping("/login-by-username")
    public ResponseEntity<ApiResponse<VendorEntity>> getVendorByUsernameAndPassword(@RequestBody Vendor vendor) {
        VendorEntity vendorEntity = vendorService.getVendorByUsernameAndPassword(vendor.getUsername(), vendor.getPassword());
        if (vendorEntity != null) {
            ApiResponse<VendorEntity> response = new ApiResponse<>(true, "Login successful.", vendorEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<VendorEntity> response = new ApiResponse<>(false, "Invalid username or password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}

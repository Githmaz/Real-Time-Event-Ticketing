package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Vendor;
import edu.iit.ticketingservice.service.VendorService;
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
public class VendorController{

    @Autowired
    VendorService vendorService;

    // Save vendor and return appropriate response
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveVendor(@RequestBody Vendor vendor) {
        VendorEntity savedVendor = vendorService.createVendor(vendor);
        if (savedVendor != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "success", true,
                    "message", "Vendor created successfully.",
                    "vendorId", savedVendor.getId(),
                    "vendorObject", savedVendor
            ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "success", false,
                "message", "Failed to create vendor."
        ));
    }

    // Login by email and password
    @PostMapping("/login-by-email")
    public ResponseEntity<Map<String, Object>> getVendorByEmailAndPassword(@RequestBody Vendor vendor) {
        VendorEntity vendorEntity = vendorService.getVendorByEmailAndPassword(vendor.getEmail(), vendor.getPassword());
        if (vendorEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "success", true,
                    "message", "Login successful.",
                    "vendorId", vendorEntity.getId(),
                    "vendorObject", vendorEntity
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "success", false,
                "message", "Invalid email or password."
        ));
    }

    // Login by username and password
    @PostMapping("/login-by-username")
    public ResponseEntity<Map<String, Object>> getVendorByUsernameAndPassword(@RequestBody Vendor vendor) {
        VendorEntity vendorEntity = vendorService.getVendorByUsernameAndPassword(vendor.getUsername(), vendor.getPassword());
        if (vendorEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "success", true,
                    "message", "Login successful.",
                    "vendorId", vendorEntity.getId(),
                    "vendorObject", vendorEntity
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "success", false,
                "message", "Invalid username or password."
        ));
    }

}

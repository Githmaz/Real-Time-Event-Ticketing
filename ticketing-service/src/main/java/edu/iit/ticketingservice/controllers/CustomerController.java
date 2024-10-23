package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

   // Save customer and return appropriate response
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveCustomer(@RequestBody Customer customer) {
        CustomerEntity savedCustomer = customerService.createCustomer(customer);
        if (savedCustomer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "success", true,
                    "message", "Customer created successfully.",
                    "customerId", savedCustomer.getId(),
                    "customerObject", savedCustomer,
                    "status", HttpStatus.CREATED
            ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Failed to create customer."
        ));

    }

    // Login by email and password
    @PostMapping("/login-by-email")
    public ResponseEntity<Map<String, Object>> getCustomerByEmailAndPassword(@RequestBody Customer customer) {
        CustomerEntity customerEntity = customerService.GetCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword());
        if (customerEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "success", true,
                    "message", "Login successful.",
                    "customerId", customerEntity.getId(),
                    "status", HttpStatus.OK
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Invalid email or password.",
                    "status", HttpStatus.UNAUTHORIZED
            ));
        }
    }

    // Login by username and password
    @PostMapping("/login-by-username")
    public ResponseEntity<Map<String, Object>> getCustomerByUsernameAndPassword(@RequestBody Customer customer) {
        CustomerEntity customerEntity = customerService.GetCustomerByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (customerEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "success", true,
                    "message", "Login successful.",
                    "customerId", customerEntity.getId(),
                    "status", HttpStatus.OK
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Invalid username or password.",
                    "status", HttpStatus.UNAUTHORIZED
            ));
        }
    }

}

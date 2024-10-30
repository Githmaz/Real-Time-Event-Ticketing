package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    // Save customer and return appropriate response
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Customer>> saveCustomer(@Valid @RequestBody Customer customer) {
        logger.info("Received request to save a new customer with email: {}", customer.getEmail());
        Customer savedCustomer = customerService.createCustomer(customer);
        logger.info("Customer created successfully with user ID: {}", savedCustomer.getUserId());
        ApiResponse<Customer> response = new ApiResponse<>(true, "Customer created successfully.", savedCustomer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Login by email and password
    @PostMapping("/login-by-email")
    public ResponseEntity<ApiResponse<Customer>> getCustomerByEmailAndPassword(@RequestBody Customer customer) {
        logger.info("Login attempt by email: {}", customer.getEmail());
        Customer authenticatedCustomer = customerService.GetCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword());
        ApiResponse<Customer> response = new ApiResponse<>(true, "Login successful.", authenticatedCustomer);
        logger.info("Login successful for user ID: {}", authenticatedCustomer.getUserId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Login by username and password
    @PostMapping("/login-by-username")
    public ResponseEntity<ApiResponse<Customer>> getCustomerByUsernameAndPassword(@RequestBody Customer customer) {
        logger.info("Login attempt by username: {}", customer.getUsername());
        Customer authenticatedCustomer = customerService.GetCustomerByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        ApiResponse<Customer> response = new ApiResponse<>(true, "Login successful.", authenticatedCustomer);
        logger.info("Login successful for user ID: {}", authenticatedCustomer.getUserId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

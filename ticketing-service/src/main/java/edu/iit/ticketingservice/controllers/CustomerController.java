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


@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    CustomerService customerService;

   // Save customer and return appropriate response
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Customer>> saveCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        ApiResponse<Customer> response = new ApiResponse<>(true, "Customer created successfully.", savedCustomer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    // Login by email and password
    @PostMapping("/login-by-email")
    public ResponseEntity<ApiResponse<Customer>> getCustomerByEmailAndPassword(@RequestBody Customer customer) {
        Customer authenticatedCustomer = customerService.GetCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword());
        if (authenticatedCustomer != null) {
            ApiResponse<Customer> response = new ApiResponse<>(true, "Login successful.", authenticatedCustomer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Customer> response = new ApiResponse<>(false, "Invalid email or password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    // Login by username and password
    @PostMapping("/login-by-username")
    public ResponseEntity<ApiResponse<Customer>> getCustomerByUsernameAndPassword(@RequestBody Customer customer) {
        Customer authenticatedCustomer = customerService.GetCustomerByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (authenticatedCustomer != null) {
            ApiResponse<Customer> response = new ApiResponse<>(true, "Login successful.", authenticatedCustomer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Customer> response = new ApiResponse<>(false, "Invalid email or password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

}

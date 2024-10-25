package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.service.CustomerService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponse<CustomerEntity>> saveCustomer(@Valid @RequestBody Customer customer) {
        CustomerEntity savedCustomer = customerService.createCustomer(customer);
        ApiResponse<CustomerEntity> response = new ApiResponse<>(true, "Customer created successfully.", savedCustomer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    // Login by email and password
    @PostMapping("/login-by-email")
    public ResponseEntity<ApiResponse<CustomerEntity>> getCustomerByEmailAndPassword(@RequestBody Customer customer) {
        CustomerEntity customerEntity = customerService.GetCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword());
        if (customerEntity != null) {
            ApiResponse<CustomerEntity> response = new ApiResponse<>(true, "Login successful.", customerEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<CustomerEntity> response = new ApiResponse<>(false, "Invalid email or password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    // Login by username and password
    @PostMapping("/login-by-username")
    public ResponseEntity<ApiResponse<CustomerEntity>> getCustomerByUsernameAndPassword(@RequestBody Customer customer) {
        CustomerEntity customerEntity = customerService.GetCustomerByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if (customerEntity != null) {
            ApiResponse<CustomerEntity> response = new ApiResponse<>(true, "Login successful.", customerEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<CustomerEntity> response = new ApiResponse<>(false, "Invalid email or password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

}

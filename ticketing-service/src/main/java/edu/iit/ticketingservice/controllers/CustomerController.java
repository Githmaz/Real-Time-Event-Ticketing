package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.repository.CustomerRepository;
import edu.iit.ticketingservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/save")
    public CustomerEntity saveCustomer(@RequestBody Customer customer){
        return customerService.SaveCustomer(customer);
    }

    @PostMapping("/login-by-email")
    public CustomerEntity getCustomerByEmailAndPassword(@RequestBody Customer customer){
        return customerService.GetCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword());
    }
    @PostMapping("/login-by-username")
    public CustomerEntity getCustomerByUsernameAndPassword(@RequestBody Customer customer){
        return customerService.GetCustomerByUsernameAndPassword(customer.getUsername(), customer.getPassword());
    }

}

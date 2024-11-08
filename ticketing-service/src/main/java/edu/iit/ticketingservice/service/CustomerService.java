package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer GetCustomerByEmailAndPassword(String email, String password);
    Customer GetCustomerByUsernameAndPassword(String username, String password);
}

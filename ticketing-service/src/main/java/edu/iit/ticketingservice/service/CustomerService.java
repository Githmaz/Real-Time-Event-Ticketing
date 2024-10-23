package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.Customer;

public interface CustomerService {
    CustomerEntity createCustomer(Customer customer);
    CustomerEntity GetCustomerByEmail(String email);
    CustomerEntity GetCustomerByUsername(String username);
    CustomerEntity GetCustomerByUserId(String userId);
    CustomerEntity GetCustomerByEmailAndPassword(String email, String password);
    CustomerEntity GetCustomerByUsernameAndPassword(String username, String password);
}

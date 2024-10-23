package edu.iit.ticketingservice.service.impl;


import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.repository.CustomerRepository;
import edu.iit.ticketingservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerEntity createCustomer(Customer customer) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .username(customer.getUsername())
                .build();
        customerEntity.generateUserId();

        return customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity GetCustomerByEmail(String email) {
        return null;
    }

    @Override
    public CustomerEntity GetCustomerByUsername(String username) {
        return null;
    }

    @Override
    public CustomerEntity GetCustomerByUserId(String userId) {
        return null;
    }

    @Override
    public CustomerEntity GetCustomerByEmailAndPassword(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public CustomerEntity GetCustomerByUsernameAndPassword(String username, String password) {
        return customerRepository.findByUsernameAndPassword(username, password);
    }


}

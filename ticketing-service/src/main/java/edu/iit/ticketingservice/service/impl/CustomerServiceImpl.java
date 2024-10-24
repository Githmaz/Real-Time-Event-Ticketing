package edu.iit.ticketingservice.service.impl;


import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.CustomerRepository;
import edu.iit.ticketingservice.service.CustomerService;
import edu.iit.ticketingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserService userService;

    @Override
    public CustomerEntity createCustomer(Customer customer) {

        // Check if email is already in use
        if (!userService.checkUserEmail(customer.getEmail())) {
            throw new BusinessException(ErrorType.EMAIL_ALREADY_EXISTS);
        }

        // Check if username is already in use
        if (!userService.checkUsername(customer.getUsername())) {
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXISTS);
        }
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

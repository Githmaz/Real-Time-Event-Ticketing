package edu.iit.ticketingservice.service.impl;


import edu.iit.ticketingservice.config.EncryptionConfig;
import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.CustomerRepository;
import edu.iit.ticketingservice.service.CustomerService;
import edu.iit.ticketingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserService userService;

    @Override
    public Customer createCustomer(Customer customer) {

        // Check if email is already in use
        if (!userService.checkUserEmail(customer.getEmail())) {
            throw new BusinessException(ErrorType.EMAIL_ALREADY_EXISTS);
        }

        // Check if username is already in use
        if (!userService.checkUsername(customer.getUsername())) {
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXISTS);
        }


        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());

        // Hash the password before setting it
        String hashedPassword = passwordEncoder.encode(customer.getPassword());
        customerEntity.setPassword(hashedPassword);
        customerEntity.setUsername(customer.getUsername());
        customerEntity.generateUserId();
        return convertToDto(customerRepository.save(customerEntity));
    }


    @Override
    public Customer GetCustomerByEmailAndPassword(String email, String password) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email);
        if (customerEntity != null && passwordEncoder.matches(password, customerEntity.getPassword())) {
            return convertToDto(customerEntity);
        }
        return null;
    }

    @Override
    public Customer GetCustomerByUsernameAndPassword(String username, String password) {
        CustomerEntity customerEntity = customerRepository.findByUsername(username);
        if (customerEntity != null && passwordEncoder.matches(password, customerEntity.getPassword())) {
            return convertToDto(customerEntity);
        }
        return null;
    }

    // Conversion method to map CustomerEntity to Customer DTO
    private Customer convertToDto(CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setName(customerEntity.getName());
        customer.setEmail(customerEntity.getEmail());
        customer.setUsername(customerEntity.getUsername());
        customer.setUserId(customerEntity.getUserId());
        return customer;
    }

}

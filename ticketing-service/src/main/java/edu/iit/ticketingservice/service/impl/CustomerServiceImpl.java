package edu.iit.ticketingservice.service.impl;


import edu.iit.ticketingservice.config.EncryptionConfig;
import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.CustomerRepository;
import edu.iit.ticketingservice.service.CustomerService;
import edu.iit.ticketingservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserService userService;

    @Override
    public Customer createCustomer(Customer customer) {
        if (!userService.checkUserEmail(customer.getEmail())) {
            logger.warn("Email {} already in use", customer.getEmail());
            throw new BusinessException(ErrorType.EMAIL_ALREADY_EXISTS);
        }

        if (!userService.checkUsername(customer.getUsername())) {
            logger.warn("Username {} already in use", customer.getUsername());
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXISTS);
        }

        logger.info("Creating new customer with email: {}", customer.getEmail());
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerEntity.setUsername(customer.getUsername());
        customerEntity.generateUserId();
        Customer savedCustomer = convertToDto(customerRepository.save(customerEntity));
        logger.info("Customer created successfully with user ID: {}", savedCustomer.getUserId());
        return savedCustomer;
    }

    @Override
    public Customer GetCustomerByEmailAndPassword(String email, String password) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email);
        if (customerEntity != null && passwordEncoder.matches(password, customerEntity.getPassword())) {
            logger.info("Customer authenticated successfully with user ID: {}", customerEntity.getUserId());
            return convertToDto(customerEntity);
        }
        logger.warn("Invalid credentials for email: {}", email);
        throw new BusinessException(ErrorType.INVALID_CREDENTIALS);
    }

    @Override
    public Customer GetCustomerByUsernameAndPassword(String username, String password) {
        CustomerEntity customerEntity = customerRepository.findByUsername(username);
        if (customerEntity != null && passwordEncoder.matches(password, customerEntity.getPassword())) {
            logger.info("Customer authenticated successfully with user ID: {}", customerEntity.getUserId());
            return convertToDto(customerEntity);
        }
        logger.warn("Invalid credentials for username: {}", username);
        throw new BusinessException(ErrorType.INVALID_CREDENTIALS);
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

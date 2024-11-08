package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.UsersEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.dto.UserRole;
import edu.iit.ticketingservice.dto.Users;
import edu.iit.ticketingservice.dto.Vendor;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.CustomerRepository;
import edu.iit.ticketingservice.repository.UserRepository;
import edu.iit.ticketingservice.repository.VendorRepository;
import edu.iit.ticketingservice.service.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

//    @Autowired
//    private AdminRepository adminRepository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private ModelMapper modelMapper;

    public Users registerUser(Users user) {
        // Validate that username and email are unique across roles
        checkUserExistence(user);

        switch (user.getUserRole()) {
            case CUSTOMER:
                return registerCustomer(user);

            case VENDOR:
                return registerVendor(user);

//            case ADMIN:
//                return registerAdmin(user);

            default:
                throw new BusinessException(ErrorType.INVALID_USER_ROLE);
        }
    }

    private void checkUserExistence(Users user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.warn("Email {} already in use", user.getEmail());
            throw new BusinessException(ErrorType.EMAIL_ALREADY_EXISTS);
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            logger.warn("Username {} already in use", user.getUsername());
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXISTS);
        }
    }

    private Customer registerCustomer(Users user) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUsername(user.getUsername());
        customerEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        customerEntity.setEmail(user.getEmail());
        customerEntity.setName(user.getName());
        customerEntity.setUserRole(UserRole.CUSTOMER);
        customerEntity.generateUserId();

        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        return convertToDto(savedCustomer, Customer.class);
    }

    private Vendor registerVendor(Users user) {
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setUsername(user.getUsername());
        vendorEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        vendorEntity.setEmail(user.getEmail());
        vendorEntity.setName(user.getName());
        vendorEntity.setUserRole(UserRole.VENDOR);
        vendorEntity.generateUserId();

        VendorEntity savedVendor = vendorRepository.save(vendorEntity);
        return convertToDto(savedVendor, Vendor.class);
    }
//
//    private Admin registerAdmin(Users user) {
//        AdminEntity adminEntity = new AdminEntity();
//        adminEntity.setUsername(user.getUsername());
//        adminEntity.setPassword(passwordEncoder.encode(user.getPassword()));
//        adminEntity.setEmail(user.getEmail());
//        adminEntity.setName(user.getName());
//        adminEntity.setUserRole(UserRole.ADMIN);
//
//        AdminEntity savedAdmin = adminRepository.save(adminEntity);
//        return convertToDto(savedAdmin, Admin.class);
//    }

    // Generic conversion method using ModelMapper
    private <D> D convertToDto(UsersEntity entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
    @Override
    public boolean checkUserEmail(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkUsername(String username) {
        return !userRepository.existsByUsername(username);
    }


}

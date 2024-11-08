package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Vendor;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.VendorRepository;
import edu.iit.ticketingservice.service.UserService;
import edu.iit.ticketingservice.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    UserService userService;

    private BCryptPasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();



    // Conversion method to map VendorEntity to Vendor DTO
    private Vendor convertToDto(VendorEntity vendorEntity) {
        Vendor vendor = new Vendor();
        vendor.setName(vendorEntity.getName());
        vendor.setEmail(vendorEntity.getEmail());
        vendor.setUsername(vendorEntity.getUsername());
        vendor.setUserId(vendorEntity.getUserId());
        return vendor;
    }
}

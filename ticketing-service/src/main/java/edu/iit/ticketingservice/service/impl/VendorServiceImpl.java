package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Vendor;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.VendorRepository;
import edu.iit.ticketingservice.service.UserService;
import edu.iit.ticketingservice.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    UserService userService;

    @Override
    public VendorEntity createVendor(Vendor vendor) {
        // Check if email is already in use
        if (!userService.checkUserEmail(vendor.getEmail())) {
            throw new BusinessException(ErrorType.EMAIL_ALREADY_EXISTS);
        }

        // Check if username is already in use
        if (!userService.checkUsername(vendor.getUsername())) {
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXISTS);
        }

        VendorEntity vendorEntity = VendorEntity.builder()
                .username(vendor.getUsername())  // Map the username
                .password(vendor.getPassword())  // Map the password
                .email(vendor.getEmail())        // Map the email
                .name(vendor.getName())          // Map the name
                .build();
        vendorEntity.generateUserId();
        return  vendorRepository.save(vendorEntity) ;

    }

    @Override
    public VendorEntity getVendorByEmail(String email) {
        return null;
    }

    @Override
    public VendorEntity getVendorById(Integer id) {
        return null;
    }

    @Override
    public VendorEntity getVendorByUserId(Vendor vendor) {
        return null;
    }

    @Override
    public VendorEntity getVendorByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public VendorEntity getVendorByUsernameAndPassword(String username, String password) {
        return null;
    }
}

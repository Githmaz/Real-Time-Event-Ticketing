package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Vendor;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.VendorRepository;
import edu.iit.ticketingservice.service.UserService;
import edu.iit.ticketingservice.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Vendor createVendor(Vendor vendor) {
        // Check if email is already in use
        if (!userService.checkUserEmail(vendor.getEmail())) {
            throw new BusinessException(ErrorType.EMAIL_ALREADY_EXISTS);
        }

        // Check if username is already in use
        if (!userService.checkUsername(vendor.getUsername())) {
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXISTS);
        }

        // Map fields and hash password
        VendorEntity vendorEntity = new VendorEntity();
        vendorEntity.setName(vendor.getName());
        vendorEntity.setEmail(vendor.getEmail());
        vendorEntity.setUsername(vendor.getUsername());
        vendorEntity.setPassword(passwordEncoder.encode(vendor.getPassword()));
        vendorEntity.generateUserId();

        // Generate custom user ID after setting other fields
        vendorEntity.generateUserId();
        return  convertToDto(vendorRepository.save(vendorEntity)) ;

    }

    @Override
    public Vendor getVendorByUserId(Vendor vendor) {
        return null;
    }

    @Override
    public Vendor getVendorByEmailAndPassword(String email, String password) {
        VendorEntity vendorEntity = vendorRepository.findByEmail(email);
        if (vendorEntity != null && passwordEncoder.matches(password, vendorEntity.getPassword())) {
            return convertToDto(vendorEntity);
        }
        return null;
    }

    @Override
    public Vendor getVendorByUsernameAndPassword(String username, String password) {
        VendorEntity vendorEntity = vendorRepository.findByUsername(username);
        if (vendorEntity != null && passwordEncoder.matches(password, vendorEntity.getPassword())) {
            return convertToDto(vendorEntity);
        }
        return null;
    }
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

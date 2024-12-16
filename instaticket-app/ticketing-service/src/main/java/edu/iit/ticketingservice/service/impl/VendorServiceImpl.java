package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.Vendor;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.VendorRepository;
import edu.iit.ticketingservice.service.UserService;
import edu.iit.ticketingservice.service.VendorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Vendor getAuthenticatedVendor() {
        String vendorId = userService.getAuthenticatedUserId();

        VendorEntity vendorEntity = vendorRepository.findByUserId(vendorId)
                .orElseThrow(() -> new BusinessException(ErrorType.VENDOR_NOT_FOUND));

        return modelMapper.map(vendorEntity, Vendor.class);
    }

}

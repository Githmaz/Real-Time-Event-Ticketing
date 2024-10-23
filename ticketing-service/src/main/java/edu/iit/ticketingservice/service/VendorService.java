package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.dto.Vendor;

public interface VendorService {
   VendorEntity createVendor(Vendor vendor);
   VendorEntity getVendorByEmail(String email);
   VendorEntity getVendorById(Integer id);
   VendorEntity getVendorByUserId(Vendor vendor);
   VendorEntity getVendorByEmailAndPassword(String email, String password);
   VendorEntity getVendorByUsernameAndPassword(String username, String password);
}

package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Vendor;

public interface VendorService {
   Vendor createVendor(Vendor vendor);
   Vendor getVendorByUserId(Vendor vendor);
   Vendor getVendorByEmailAndPassword(String email, String password);
   Vendor getVendorByUsernameAndPassword(String username, String password);
}

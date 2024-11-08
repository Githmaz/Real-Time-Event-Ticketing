package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Vendor;

public interface VendorService {
   Vendor getVendorByEmailAndPassword(String email, String password);
   Vendor getVendorByUsernameAndPassword(String username, String password);
}

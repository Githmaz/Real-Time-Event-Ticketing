package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.Vendor;

public interface VendorService {
    Vendor getAuthenticatedVendor();
}

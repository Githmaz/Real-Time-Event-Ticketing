package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dao.VendorEntity;

public interface UserService {
    boolean checkUserEmail(String email);
    boolean checkUsername(String name);
}

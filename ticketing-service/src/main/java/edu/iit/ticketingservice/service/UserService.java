package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.Users;

public interface UserService {
    boolean checkUserEmail(String email);
    boolean checkUsername(String name);
    Users registerUser(Users user);
}

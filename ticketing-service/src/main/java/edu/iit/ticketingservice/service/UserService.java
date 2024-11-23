package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.users.Users;

public interface UserService {
    boolean checkUserEmail(String email);
    boolean checkUsername(String name);
    Users registerUser(Users user);
    String getAuthenticatedUserId();
}

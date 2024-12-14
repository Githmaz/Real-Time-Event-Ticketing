package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.users.Users;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    String login(Users user);
    void logout();
}

package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.users.Users;
import edu.iit.ticketingservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    // Login endpoint
    @PostMapping("/login")
    public String login(@RequestBody Users user) {
            String response = authenticationService.login(user);
                return response;
    }

    // Logout endpoint
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        authenticationService.logout(); // Implement logout logic in AuthenticationService if needed
        return new ResponseEntity<>(new ApiResponse<>(true, "Logout successful", null), HttpStatus.OK);
    }
}

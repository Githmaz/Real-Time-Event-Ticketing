package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.users.Users;
import edu.iit.ticketingservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody Users user) {
        try {
            String token = authenticationService.login(user);
            return new ResponseEntity<>(new ApiResponse<>(true, "Login successful", token), HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid credentials)
            return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null), HttpStatus.UNAUTHORIZED);
        }
    }

    // Logout endpoint
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        authenticationService.logout(); // Implement logout logic in AuthenticationService if needed
        return new ResponseEntity<>(new ApiResponse<>(true, "Logout successful", null), HttpStatus.OK);
    }
}

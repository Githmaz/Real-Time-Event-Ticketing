package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.users.UserReq;
import edu.iit.ticketingservice.dto.users.Users;
import edu.iit.ticketingservice.service.UserService;
import edu.iit.ticketingservice.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserService userService;

    // Single endpoint to handle registration based on user role
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Users>> registerUser(@Valid @RequestBody UserReq userReq) {
        System.out.println("fk me");
        logger.info("Registering new user with username: {} and role: {}", userReq.getUser().getUsername(), userReq.getUser().getUserRole());
        Users registeredUserDto = userService.registerUser(userReq.getUser());
        logger.info("User created successfully with user ID: {}", registeredUserDto.getUserId());
        return new ResponseEntity<>(new ApiResponse<>(true, "User registered successfully", registeredUserDto), HttpStatus.CREATED);
    }

    // Check if the email is available
    @GetMapping("/check-email")
    public ResponseEntity<ApiResponse<Boolean>> checkEmailAvailability(@RequestParam String email) {
        boolean isAvailable = userService.checkUserEmail(email);
        if (isAvailable) {
            return new ResponseEntity<>(new ApiResponse<>(true, "Email is available", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse<>(true, "Email is already in use", false), HttpStatus.OK);
        }
    }

    // Check if the username is available
    @GetMapping("/check-username")
    public ResponseEntity<ApiResponse<Boolean>> checkUsernameAvailability(@RequestParam String username) {
        boolean isAvailable = userService.checkUsername(username);
        if (isAvailable) {
            return new ResponseEntity<>(new ApiResponse<>(true, "Username is available", true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse<>(true, "Username is already in use", false), HttpStatus.OK);
        }
    }

}

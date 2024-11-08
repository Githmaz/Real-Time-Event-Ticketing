package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.dto.Users;
import edu.iit.ticketingservice.service.UserService;
import edu.iit.ticketingservice.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserService userService;

    // Single endpoint to handle registration based on user role
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerUser(@Valid @RequestBody Users user) {
        logger.info("Registering new user with username: {} and role: {}", user.getUsername(), user.getUserRole());
        Users registeredUserDto = userService.registerUser(user);
        logger.info("User created successfully with user ID: {}", registeredUserDto.getUserId());
        return new ResponseEntity<>(new ApiResponse<>(true, "User registered successfully", registeredUserDto), HttpStatus.CREATED);
    }



}

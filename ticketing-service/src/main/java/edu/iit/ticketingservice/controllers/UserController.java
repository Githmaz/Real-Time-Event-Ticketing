package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/check-email")
    public ResponseEntity<ApiResponse<Boolean>> checkUserEmail(@RequestParam String email) {
        boolean isValid = userService.checkUserEmail(email);
        ApiResponse<Boolean> response = new ApiResponse<>(isValid, isValid ? "Email is available." : "Email already exists.");
        return new ResponseEntity<>(response, isValid ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/check-username")
    public ResponseEntity<ApiResponse<Boolean>> checkUsername(@RequestParam String username) {
        boolean isValid = userService.checkUsername(username);
        ApiResponse<Boolean> response = new ApiResponse<>(isValid, isValid ? "Username is available." : "Username already exists.");
        return new ResponseEntity<>(response, isValid ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}

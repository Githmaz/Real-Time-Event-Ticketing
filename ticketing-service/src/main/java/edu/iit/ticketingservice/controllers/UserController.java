package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Object>> checkUserEmail(@RequestParam String email) {
        boolean isValid = userService.checkUserEmail(email);
        return ResponseEntity.status(isValid ? HttpStatus.OK : HttpStatus.CONFLICT).body(Map.of(
                "isValid", isValid,
                "message", isValid ? "Email is available." : "Email already exists."
        ));
    }

    @GetMapping("/check-username")
    public ResponseEntity<Map<String, Object>> checkUsername(@RequestParam String username) {
        boolean isValid = userService.checkUsername(username);
        return ResponseEntity.status(isValid ? HttpStatus.OK : HttpStatus.CONFLICT).body(Map.of(
                "isValid", isValid,
                "message", isValid ? "Username is available." : "Username already exists."

        ));
    }
}

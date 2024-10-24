package edu.iit.ticketingservice.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    EMAIL_ALREADY_EXISTS("Email already exists"),
    USERNAME_ALREADY_EXISTS("Username already exists");

    private final String message;

    ErrorType(String message) {
        this.message = message;
    }

}

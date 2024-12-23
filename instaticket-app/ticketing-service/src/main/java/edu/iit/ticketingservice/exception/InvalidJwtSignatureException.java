package edu.iit.ticketingservice.exception;

public class InvalidJwtSignatureException extends RuntimeException {
    public InvalidJwtSignatureException(String message) {
        super(message);
    }
}

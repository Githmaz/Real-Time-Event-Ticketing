package edu.iit.ticketingservice.exception;

public class BusinessException extends RuntimeException {
  public BusinessException(ErrorType errorType) {
    super(errorType.getMessage());
  }
}

package edu.iit.ticketingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
       return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
               "message", "Validation failed",
                "success", false,
                "error", ex.getMessage()
        ));
    }

    // Handle validation errors for @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),  // Field name
                        fieldError -> fieldError.getDefaultMessage()  // Error message
                ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message", "Validation failed",
                "success", false,
                "errors", errors
        ));
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "An unexpected error occurred.",
                "error", ex.getMessage()
        ));
    }
}

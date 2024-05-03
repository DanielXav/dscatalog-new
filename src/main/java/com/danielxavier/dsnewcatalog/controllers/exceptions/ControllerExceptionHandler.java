package com.danielxavier.dsnewcatalog.controllers.exceptions;

import com.danielxavier.dsnewcatalog.services.exceptions.DatabaseException;
import com.danielxavier.dsnewcatalog.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RStandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        /*StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());*/
        RStandardError err = new RStandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<RStandardError> database(ResourceNotFoundException e, HttpServletRequest request) {
        RStandardError err = new RStandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Database exception",
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}

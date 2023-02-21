package com.ibetar.capsulachallenge.handler;

import com.ibetar.capsulachallenge.exception.BankAccountBadRequestAccountsException;
import com.ibetar.capsulachallenge.exception.BankAccountInsufficientFondsException;
import com.ibetar.capsulachallenge.exception.BankAccountNotFoundException;
import com.ibetar.capsulachallenge.exception.BankAccountServiceExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class BankAccountServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
        BankAccountServiceExceptionResponse response = new BankAccountServiceExceptionResponse(LocalDateTime.now(), exception.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(BankAccountNotFoundException exception, WebRequest request) {
        BankAccountServiceExceptionResponse response = new BankAccountServiceExceptionResponse(LocalDateTime.now(), exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(BankAccountInsufficientFondsException.class)
    public ResponseEntity<Object> handleInsufficientFondsRequest(BankAccountInsufficientFondsException exception, WebRequest request) {
        BankAccountServiceExceptionResponse response = new BankAccountServiceExceptionResponse(LocalDateTime.now(), exception.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(BankAccountBadRequestAccountsException.class)
    public ResponseEntity<Object> handleBadRequests(BankAccountBadRequestAccountsException exception, WebRequest request) {
        BankAccountServiceExceptionResponse response = new BankAccountServiceExceptionResponse(LocalDateTime.now(), exception.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }
}

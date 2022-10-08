package com.ibetar.capsulachallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BankAccountInsufficientFondsException extends RuntimeException {
    public BankAccountInsufficientFondsException(String message){
        super(message);
    }
}

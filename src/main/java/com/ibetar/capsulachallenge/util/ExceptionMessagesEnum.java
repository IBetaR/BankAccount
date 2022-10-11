package com.ibetar.capsulachallenge.util;

public enum ExceptionMessagesEnum {
    ACCOUNT_NOT_FOUND("Account Not Found"),
    INCORRECT_REQUEST_EMPTY_TRANSACTION_REQUEST("Empty transactions are impossible to achieve");

    ExceptionMessagesEnum(String msg) {
        value = msg;
    }

    private final String value;

    public String getValue(){
        return value;
    }
}

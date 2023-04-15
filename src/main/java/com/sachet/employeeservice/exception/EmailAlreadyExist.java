package com.sachet.employeeservice.exception;

public class EmailAlreadyExist extends RuntimeException{

    private String message;

    public EmailAlreadyExist(String message) {
        super(message);
        this.message = message;
    }
}

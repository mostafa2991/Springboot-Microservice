package com.customers.sqllite.exceptions;

public class NotFoundException extends RuntimeException  {

    public NotFoundException(String message) {
        super(message);
    }

}

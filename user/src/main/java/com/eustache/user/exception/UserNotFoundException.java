package com.eustache.user.exception;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

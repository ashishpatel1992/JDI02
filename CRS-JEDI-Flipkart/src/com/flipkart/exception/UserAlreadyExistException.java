package com.flipkart.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}

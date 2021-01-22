package com.flipkart.exception;


/**
 * Custom exception class for user not found
 */
public class UserNotFoundException extends Exception{
    String userId;

    public UserNotFoundException(String userId){
        super();
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}

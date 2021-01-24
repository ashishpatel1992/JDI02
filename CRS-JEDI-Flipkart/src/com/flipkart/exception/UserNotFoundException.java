package com.flipkart.exception;


/**
 * Custom exception class for user not found
 */
public class UserNotFoundException extends Exception {
    public String message;

    /**
     * Constructor for class, initialises userId property
     */
    public UserNotFoundException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

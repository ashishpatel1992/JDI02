package com.flipkart.exception;


/**
 * Custom exception class for user not found
 */
public class UserNotFoundException extends Exception{
    String userId;

    /**
     * Constructor for class, initialises userId property
     * @param userId
     */
    public UserNotFoundException(String userId){
        super();
        this.userId = userId;
    }

    /**
     * Returns user id for which the exception is thrown
     * @return id of user for which exception is thrown
     */
    public String getUserId() {
        return userId;
    }
}

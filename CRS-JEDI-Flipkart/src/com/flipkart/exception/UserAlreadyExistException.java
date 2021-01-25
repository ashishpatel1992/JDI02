package com.flipkart.exception;

/**
 * Custom exception class for course not found
 *
 * @Author -  Team JEDI 02
 */
public class UserAlreadyExistException extends Exception {

    /**
     * Constructor for class, initialises courseId property
     *
     * @param errorMessage error message to be printed
     */
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}

package com.flipkart.restcontroller.beans;

/**
 * Bean class for Response message
 * @Author -  Team JEDI 02
 */
public class ResponseMessageRest {
    String message;

    /**
     * Constructor for the class which sets the message
     * @param message
     */
    public ResponseMessageRest(String message) {
        this.message = message;
    }

    /**
     * Gets the message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

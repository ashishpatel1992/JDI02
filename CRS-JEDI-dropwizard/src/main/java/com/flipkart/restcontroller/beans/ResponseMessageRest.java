package com.flipkart.restcontroller.beans;

public class ResponseMessageRest {
    String message;

    public ResponseMessageRest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

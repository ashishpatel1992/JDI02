package com.flipkart.service;

public interface UserInterface {
    /**
     * Verify credentials of the student
     * @param id
     * @param password
     * @return id of the user
     */
    public String verifyCredentials(String id, String password);

}

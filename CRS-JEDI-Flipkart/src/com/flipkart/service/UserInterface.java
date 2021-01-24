package com.flipkart.service;

public interface UserInterface {
    /**
     * Verifies credentials of the student
     * @param id user ID
     * @param password password of user
     * @return user ID
     */
    public String verifyCredentials(String id, String password);

}

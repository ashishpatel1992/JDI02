package com.flipkart.service;

public interface UserInterface {
    /**
     * Verify credentials of the student
     * @param id
     * @param hasedPassword
     * @return
     */
    public boolean verifyCredentials(String id, String hasedPassword);

}

package com.flipkart.service;

/**
 * Interface for user operations
 *
 * @Author -  Team JEDI 02
 */
public interface UserInterface {
    /**
     * Verifies credentials of the student
     *
     * @param id       user ID
     * @param password password of user
     * @return user ID
     */
    String verifyCredentials(String id, String password);

}

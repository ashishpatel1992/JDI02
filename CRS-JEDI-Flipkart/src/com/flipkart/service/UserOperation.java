package com.flipkart.service;

import org.apache.log4j.Logger;

/**
 * Class to handle user operations
 */
public class UserOperation implements UserInterface{
    private static Logger logger = Logger.getLogger(UserOperation.class);

    /**
     * Verifies credentials of the student
     * @param userId user ID
     * @param password password of user
     * @return user ID
     */
    @Override
    public String verifyCredentials(String userId, String password) {

        logger.info("Verify Credentials");
        logger.info(userId);
        // TODO User Authentication from doa
        return userId;
    }
}

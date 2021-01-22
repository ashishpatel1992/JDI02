package com.flipkart.service;

import org.apache.log4j.Logger;

public class UserOperation implements UserInterface{
    private static Logger logger = Logger.getLogger(UserOperation.class);

    /**
     * Verify credentials of the student
     * @param userId
     * @param password
     * @return
     */
    @Override
    public String verifyCredentials(String userId, String password) {

        logger.info("Verify Credentials");
        logger.info(userId);
        // TODO User Authentication from doa
        return userId;
    }
}

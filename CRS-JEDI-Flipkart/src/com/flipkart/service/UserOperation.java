package com.flipkart.service;

import org.apache.log4j.Logger;

public class UserOperation implements UserInterface{
    private static Logger logger = Logger.getLogger(UserOperation.class);

    /**
     * Verify credentials of the student
     * @param id
     * @param hasedPassword
     * @return
     */
    @Override
    public boolean verifyCredentials(String id, String hasedPassword) {

        logger.info("Verify Credentials");
        // TODO User Authentication
        return true;
    }
}

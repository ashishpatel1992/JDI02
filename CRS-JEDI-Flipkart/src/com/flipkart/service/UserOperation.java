package com.flipkart.service;

import org.apache.log4j.Logger;

public class UserOperation implements UserInterface{
    private static Logger logger = Logger.getLogger(UserOperation.class);
    @Override
    public boolean verifyCredentials(String id, String hasedPassword) {

        logger.info("Verify Credentials");
        // TODO User Authentication
        return true;
    }
}

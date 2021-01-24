package com.flipkart.service;

import org.apache.log4j.Logger;

/**
 * Class to handle notification operation
 */
public class NotificationOperation implements NotificationInterface{
    private static Logger logger = Logger.getLogger(NotificationOperation.class);

    /**
     * Sends notification to student
     */
    @Override
    public void notifyStudent() {
        logger.info("Notify student");

    }
}

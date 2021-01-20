package com.flipkart.service;

import org.apache.log4j.Logger;

public class NotificationOperation implements NotificationInterface{
    private static Logger logger = Logger.getLogger(NotificationOperation.class);
    @Override
    public void notifyStudent() {
        logger.info("Notify student");

    }
}

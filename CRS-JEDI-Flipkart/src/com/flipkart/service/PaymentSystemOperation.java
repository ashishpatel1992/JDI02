package com.flipkart.service;

import org.apache.log4j.Logger;

/**
 * Class to handle Payment System operations
 */
public class PaymentSystemOperation implements PaymentSystemInterface{
    private static Logger logger = Logger.getLogger(PaymentSystemOperation.class);

    /**
     * Calculates the fee
     * @return the fee
     */
    @Override
    public double calculateFee() {
        logger.info("Calculate Fee");
        return 0;
    }
}

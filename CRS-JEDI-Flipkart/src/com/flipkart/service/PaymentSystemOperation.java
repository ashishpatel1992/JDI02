package com.flipkart.service;

import org.apache.log4j.Logger;

public class PaymentSystemOperation implements PaymentSystemInterface{
    private static Logger logger = Logger.getLogger(PaymentSystemOperation.class);
    @Override
    public double calculateFee() {
        logger.info("Calculate Fee");
        return 0;
    }
}

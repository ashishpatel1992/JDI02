package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface PaymentSystemInterface {
    /**
     * Calculates the fee
     * @return the fee
     */
    public double calculateFee();
}

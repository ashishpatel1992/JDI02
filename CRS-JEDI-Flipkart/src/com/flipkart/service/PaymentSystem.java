package com.flipkart.service;

/**
 * Class of Payment System
 */
public class PaymentSystem
{
    String paymentMethod;
    double amount;
    String transactionNumber;

    /**
     * Gets payment method
     * @return payment method
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets Payment method
     * @param paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets amount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets amount
     * @param amount Amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets transaction Number
     * @return transaction number
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * Sets transaction Number
     * @param transactionNumber transaction Number
     */
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
}

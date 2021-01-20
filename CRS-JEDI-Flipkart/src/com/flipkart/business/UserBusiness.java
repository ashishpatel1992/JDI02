package com.flipkart.business;

public class UserBusiness implements UserInterface{

    @Override
    public boolean verifyCredentials(String id, String hasedPassword) {
        return false;
    }
}

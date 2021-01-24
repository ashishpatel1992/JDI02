package com.flipkart.service;

import com.flipkart.dao.LoginDaoImp;
import com.flipkart.dao.LoginDaoInterface;
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
        if(userId==null || password ==null){
            return null;
        }
        //logger.info("Verify Credentials");
        //logger.info(userId);
        LoginDaoInterface loginDaoInterface = LoginDaoImp.getInstance();
        boolean loginSuccess = loginDaoInterface.login(userId,password);
        if(loginSuccess){
            return userId;
        }
        return null;
    }
}

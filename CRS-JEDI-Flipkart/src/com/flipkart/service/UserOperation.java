package com.flipkart.service;

import com.flipkart.dao.LoginDaoImp;
import com.flipkart.dao.LoginDaoInterface;
import org.apache.log4j.Logger;

/**
 * Class to handle user operations
 *
 * @Author -  Team JEDI 02
 */
public class UserOperation implements UserInterface {
    private static Logger logger = Logger.getLogger(UserOperation.class);

    /**
     * Verifies credentials of the student
     *
     * @param userId   user ID
     * @param password password of user
     * @return user ID
     */
    @Override
    public String verifyCredentials(String userId, String password) {
        if (userId == null || password == null) {
            return null;
        }

        LoginDaoInterface loginDaoInterface = LoginDaoImp.getInstance();
        boolean loginSuccess = loginDaoInterface.login(userId, password);
        if (loginSuccess) {
            return userId;
        }
        return null;
    }
}

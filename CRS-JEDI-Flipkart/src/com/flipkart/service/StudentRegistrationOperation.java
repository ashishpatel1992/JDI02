package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.dao.LoginDaoImp;
import com.flipkart.dao.LoginDaoInterface;

/**
 * Class to handle Student registration operation
 *
 * @Author -  Team JEDI 02
 */
public class StudentRegistrationOperation implements StudentRegistrationInterface {

    /**
     * Checks if registration data is valid
     *
     * @param newStudent Student details
     * @return password
     */
    public String isRegistrationDataValid(Student newStudent, String password) {
        // TODO: Call DOA to check if student Enrollment number and Email exist
        LoginDaoInterface loginDaoInterface = LoginDaoImp.getInstance();
        String userId = loginDaoInterface.addStudent(newStudent, password);
        return userId;
    }
}

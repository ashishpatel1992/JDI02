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
     * Checks if student registration is valid and perform the registration
     *
     * @param newStudent Student details
     * @param password   Student Password
     * @return studentId
     */
    public String isRegistrationDataValid(Student newStudent, String password) {
        return LoginDaoImp.getInstance().addStudent(newStudent, password);
    }
}

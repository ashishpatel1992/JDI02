package com.flipkart.service;

import com.flipkart.bean.Student;

/**
 * Interface for student registration operations
 *
 * @Author -  Team JEDI 02
 */
public interface StudentRegistrationInterface {

    /**
     * Checks if registration data is valid
     *
     * @param newStudent Student details
     * @return password
     */
    String isRegistrationDataValid(Student newStudent, String password);
}

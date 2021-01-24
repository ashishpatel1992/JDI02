package com.flipkart.service;

import com.flipkart.bean.Student;

public interface StudentRegistrationInterface {
    /**
     * Checks if registration data is valid
     * @param newStudent Student details
     * @return password
     */
    public String isRegistrationDataValid(Student newStudent);
}

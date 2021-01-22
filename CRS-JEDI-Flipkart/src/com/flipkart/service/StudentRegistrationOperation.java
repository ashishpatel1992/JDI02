package com.flipkart.service;

import com.flipkart.bean.Student;

public class StudentRegistrationOperation implements StudentRegistrationInterface {
    public boolean isRegistrationDataValid(Student newStudent){
        // TODO: Call DOA to check if student Enrollment number and Email exist
        return true;
    }
}

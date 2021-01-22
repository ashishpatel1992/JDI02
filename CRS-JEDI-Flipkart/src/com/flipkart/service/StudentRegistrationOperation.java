package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.dao.LoginDaoImp;
import com.flipkart.dao.LoginDaoInterface;

public class StudentRegistrationOperation implements StudentRegistrationInterface {
    public String isRegistrationDataValid(Student newStudent){
        // TODO: Call DOA to check if student Enrollment number and Email exist
        LoginDaoInterface loginDaoInterface = new LoginDaoImp();
        String password = loginDaoInterface.addStudent(newStudent);
        return password;
    }
}

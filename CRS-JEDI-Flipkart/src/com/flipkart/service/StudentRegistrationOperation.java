package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.dao.LoginDaoImp;
import com.flipkart.dao.LoginDaoInterface;

public class StudentRegistrationOperation implements StudentRegistrationInterface {
    public String isRegistrationDataValid(Student newStudent,String password){
        // TODO: Call DOA to check if student Enrollment number and Email exist
        LoginDaoInterface loginDaoInterface = LoginDaoImp.getInstance();
        String userId = loginDaoInterface.addStudent(newStudent,password);
        return userId;
    }
}

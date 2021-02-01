package com.flipkart.restcontroller.beans;

import com.flipkart.bean.Student;

public class StudentRegisterRest extends Student {
    String password;

    StudentRegisterRest(Student student, String password){
        
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

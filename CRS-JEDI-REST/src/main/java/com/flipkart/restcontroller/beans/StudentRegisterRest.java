package com.flipkart.restcontroller.beans;

import com.flipkart.bean.Student;

/**
 * Bean Class for Student Registration
 * @Author -  Team JEDI 02
 */
public class StudentRegisterRest extends Student {
    String password;

    /**
     * Default Constructor for the class
     * @param student
     * @param password
     */
    StudentRegisterRest(Student student, String password){
        
    }

    /**
     * Gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

package com.flipkart.restcontroller.beans;

import com.flipkart.bean.Student;

import javax.validation.constraints.Size;

public class StudentRest extends Student{
    @Size(min = 1, max = 40, message = "The length of Password should be between 1 to 40")
    public String password;

    public StudentRest(){
        super();
    }
    /**
     * Constructor for student class, initialises id, name, email, role, branch, approved properties of Student class
     *
     * @param id       id(role number) of student
     * @param name     name of student
     * @param email    email of student
     * @param role     role of student
     * @param branch   branch of student
     * @param approved flag, stores status of whether student is approved by admin
     */
    public StudentRest(String id, String name, String email, String role, String branch, boolean approved, String password) {
        super(id, name, email, role, branch, approved);
        super.setApproved(false);
        this.password = password;
    }
}

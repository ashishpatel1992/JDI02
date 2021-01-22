package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

/**
 * Database access object class for login operations, performs the following operations
 * 1. Verify login credentials for a user
 * 2. Adds a student to database
 * 3. Adds a professor to database
 * 4. Approve a registered student
 */
public class LoginDaoImp implements LoginDaoInterface{

    @Override
    public String login(String userId, String password) {
        return null;
    }

    @Override
    public boolean addStudent(Student student, String password) {
        return false;
    }

    @Override
    public boolean addProfessor(Professor professor, String password) {
        return false;
    }

    @Override
    public boolean approveStudent(Student student) {
        return false;
    }

    String generateUserIdWithRole(String role){
        return "";
    }

}

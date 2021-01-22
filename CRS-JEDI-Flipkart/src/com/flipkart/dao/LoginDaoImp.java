package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

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

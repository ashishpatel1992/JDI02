package com.flipkart.restcontroller.beans;

import com.flipkart.bean.Professor;

public class ProfessorRest {
    private Professor professor;
    private String password;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.flipkart.restcontroller.beans;

import com.flipkart.bean.Professor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProfessorRest {
    @NotNull
    private Professor professor;
    @Size(min = 1, max = 40, message = "The length of Password should be between 1 to 40")
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

package com.flipkart.restcontroller.beans;

import com.flipkart.bean.Professor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Professor - bean class for professor
 * @Author -  Team JEDI 02
 */
public class ProfessorRest {
    @NotNull
    private Professor professor;
    @Size(min = 1, max = 40, message = "The length of Password should be between 1 to 40")
    private String password;

    /**
     * Gets Professor Deatils
     * @return Professor details
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Sets Professor details
     * @param professor
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
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

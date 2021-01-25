package com.flipkart.bean;

/**
 * Student - bean class for student
 * @Author -  Team JEDI 02
 */
public class Student extends User{

    /**
     * Stores Branch in which student is pursuing his course work
     */
    String branch;
    /**
     * Stores if student is approved by admin
     */
    boolean approved;

    /**
     * Constructor for student class, initialises id, name, email, role, branch, approved properties of Student class
     * @param id id(role number) of student
     * @param name name of student
     * @param email email of student
     * @param role role of student
     * @param branch branch of student
     * @param approved flag, stores status of whether student is approved by admin
     */
    public Student(String id, String name, String email, String role, String branch, boolean approved){
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.branch = branch;
        this.approved = approved;
    }

    /**
     * Returns branch name
     * @return branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets branch of student
     * @param branch branch of student
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Returns if student is approved by admin
     * @return true if student is approved by admin
     * @return false if student is not approved by admin
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Sets approved flag for student
     * @param approved true if student is approved by admin
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}

package com.flipkart.bean;

/**
 * Student class stores basic profile information for a student
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
     * Stores branch name
     * @return
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Assign a new branch to student
     * @param branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * check if student is approved by admin
     * @return
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * set approval status for student
     * @param approved
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}

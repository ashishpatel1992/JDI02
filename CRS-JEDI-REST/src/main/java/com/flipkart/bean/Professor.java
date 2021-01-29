package com.flipkart.bean;

/**
 * Professor - bean class for professor
 * @Author -  Team JEDI 02
 */
public class Professor extends User {
    String department;

    public Professor(){

    }
    /**
     * Constructor for professor class, initialises id, name, email,role and department
     *
     * @param id id of professor
     * @param name name of professor
     * @param email email of professor
     * @param role role of professor
     * @param department department of professor
     */
    public Professor(String id, String name, String email, String role, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.department = department;
    }

    /**
     *  Returns the department property of Professor class
     * @return department of professor
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets value of department property of Professor class
     * @param department department of professor
     */
    public void setDepartment(String department) {
        this.department = department;
    }
}

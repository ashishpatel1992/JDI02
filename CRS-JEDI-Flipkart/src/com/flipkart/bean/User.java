package com.flipkart.bean;

public class User {
    String id;
    String name;
    String email;
    String role;

    /**
     * Returns the student id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * assign a user id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name of the user
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of user
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the email of user
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * set the email of user
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns the role of student
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     * set the role of student
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}

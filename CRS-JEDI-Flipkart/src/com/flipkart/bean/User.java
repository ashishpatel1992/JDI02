package com.flipkart.bean;

/**
 * User - bean class for user
 */
public class User {
    String id;
    String name;
    String email;
    String role;

    /**
     * Returns the student id
     * @return id of student
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id property for User class
     * @param id id of user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name property of User class
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name property of User class
     * @param name name of user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email property of User class
     * @return email of user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email property of User class
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the role property of User class
     * @return role of user
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role property of User
     * @param role role of user
     */
    public void setRole(String role) {
        this.role = role;
    }
}

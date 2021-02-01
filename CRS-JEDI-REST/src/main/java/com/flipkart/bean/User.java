package com.flipkart.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * User - bean class for user
 * @Author -  Team JEDI 02
 */
public class User {
    @Size(min = 2, max = 15, message = "The length of Id should be between 1 to 15")
    String id;
    @Size(min = 1, max = 30, message = "The length of Name should be between 1 to 30")
    String name;
    @Email(message = "Invalid Email Address->" + "Valid emails : user@gmail.com or my.user@domain.com etc.")
    String email;
    @Size(min = 1, max = 10, message = "The length of Role should be between 1 to 10")
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

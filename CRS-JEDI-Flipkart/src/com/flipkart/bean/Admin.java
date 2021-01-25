package com.flipkart.bean;

/**
 * Admin - Bean class for admin
 * @Author -  Team JEDI 02
 */
public class Admin extends User{
    /**
     *
     * @param id Admin userID
     * @param name Admin Name
     * @param email Admin email address
     * @param role Admin Role
     */
    public Admin(String id, String name, String email, String role){
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}

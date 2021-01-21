package com.flipkart.bean;

public class Professor extends User{
    String department;

    public Professor(){

    }

    public Professor(String id, String name, String email, String role, String department){
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

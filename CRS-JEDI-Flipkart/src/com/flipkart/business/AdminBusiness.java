package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public class AdminBusiness implements AdminInterface{
    @Override
    public boolean addCourse() {
        return false;
    }

    @Override
    public boolean addProfessor() {
        return false;
    }

    @Override
    public boolean addStudent() {
        return false;
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        return null;
    }

    @Override
    public boolean approveStudent() {
        return false;
    }

    @Override
    public boolean generateReportCard() {
        return false;
    }
}

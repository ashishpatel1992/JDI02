package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public class StudentBusiness implements StudentInterface{

    @Override
    public boolean isFeePaid() {
        return false;
    }

    @Override
    public String getGrades() {
        return null;
    }

    @Override
    public boolean addCourse(String id) {
        return false;
    }

    @Override
    public boolean registerCourses() {
        return false;
    }

    @Override
    public ArrayList<Course> getRegisteredCourses() {
        return null;
    }

    @Override
    public boolean dropCourse() {
        return false;
    }
}

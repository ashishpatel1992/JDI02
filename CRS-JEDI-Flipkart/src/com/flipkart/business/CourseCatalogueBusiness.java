package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public class CourseCatalogueBusiness implements CourseCatalogueInterface {
    @Override
    public ArrayList<Course> viewCourses() {
        return null;
    }

    @Override
    public boolean addCourse() {
        return false;
    }

    @Override
    public boolean deleteCourse() {
        return false;
    }

    @Override
    public boolean assignProfessor() {
        return false;
    }
}

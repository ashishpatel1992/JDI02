package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface CourseCatalogueInterface {
    public ArrayList<Course> viewCourses();
    public boolean addCourse();
    public boolean deleteCourse();
    public boolean assignProfessor();
}

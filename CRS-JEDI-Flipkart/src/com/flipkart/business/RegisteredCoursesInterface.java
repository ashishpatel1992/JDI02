package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface RegisteredCoursesInterface {
    public boolean addCourse(String id);
    public boolean registerCourses();
    public ArrayList<Course> getRegisteredCourses();
}

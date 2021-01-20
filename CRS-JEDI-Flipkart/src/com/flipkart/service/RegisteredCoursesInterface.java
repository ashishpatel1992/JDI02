package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface RegisteredCoursesInterface {

    public boolean registerCourses(ArrayList<String> courseIdSelectionList);

    public ArrayList<Course> getRegisteredCourses();

    public boolean dropCourse(String courseId);

}

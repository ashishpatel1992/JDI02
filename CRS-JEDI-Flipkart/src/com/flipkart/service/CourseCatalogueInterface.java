package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface CourseCatalogueInterface {
    public ArrayList<Course> getCourseList();
    public boolean addCourse(String courseId, String courseName);
    public Course getCourse(String courseId);
    public boolean deleteCourse(String courseId);
    public boolean assignProfessor(String courseId);
}

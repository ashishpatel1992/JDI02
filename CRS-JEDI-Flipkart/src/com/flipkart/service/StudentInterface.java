package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface StudentInterface {
    public boolean addCourseToSelection(String courseId);
    public ArrayList<String>  getCourseSelection();
    public boolean removeCourseFromSelection(String courseId);
//    public boolean registerCourse();
    public ArrayList<Course> getRegisteredCourses();

    public boolean isFeePaid();
    public String getGrades();
    public boolean addCourse(String id);
    public boolean  registerCourses();
    public boolean dropCourse(String courseId);
}

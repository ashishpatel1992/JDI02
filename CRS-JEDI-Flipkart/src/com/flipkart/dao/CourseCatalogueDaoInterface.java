package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface CourseCatalogueDaoInterface {
    public ArrayList<Course> getAllCourses();
    public Course getCourseDetail(String courseId);
}

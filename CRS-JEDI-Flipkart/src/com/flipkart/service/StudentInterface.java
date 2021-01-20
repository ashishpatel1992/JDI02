package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface StudentInterface {
    public int menu();
    public boolean isFeePaid();
    public String getGrades();
    public boolean addCourse(String id);
    public boolean  registerCourses();
    public ArrayList<Course> getRegisteredCourses();
    public boolean dropCourse();
}

package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface AdminInterface {
    public boolean addCourse();
    public boolean addProfessor();
    public boolean addStudent();
    public ArrayList<Course> getAllCourses();
    public boolean approveStudent();
    public boolean generateReportCard();
}

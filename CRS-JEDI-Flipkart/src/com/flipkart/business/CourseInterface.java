package com.flipkart.business;

import com.flipkart.bean.Student;

import java.util.ArrayList;

public interface CourseInterface {
    public boolean addCourse();
    public boolean modifyCourse();
    public boolean deleteCourse();
    public ArrayList<Student> getStudentsEnrolled();
}

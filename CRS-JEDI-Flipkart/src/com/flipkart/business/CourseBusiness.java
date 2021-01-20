package com.flipkart.business;

import com.flipkart.bean.Student;

import java.util.ArrayList;

public class CourseBusiness implements CourseInterface{
    @Override
    public boolean addCourse() {
        return false;
    }

    @Override
    public boolean modifyCourse() {
        return false;
    }

    @Override
    public boolean deleteCourse() {
        return false;
    }

    @Override
    public ArrayList<Student> getStudentsEnrolled() {
        return null;
    }
}

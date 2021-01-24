package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

public interface StudentInterface {
    public boolean addCourseToSelection(String courseId);

    public ArrayList<String> getCourseSelection();

    public boolean dropCourseFromSelection(String courseId);

    public ArrayList<Course> getRegisteredCourses();

    public boolean isFeePaid();

    public HashMap<String, String> getGrades();

    public boolean addCourse(String id);

    public ArrayList<String> registerCourses();

    public Student getStudentProfile();
}

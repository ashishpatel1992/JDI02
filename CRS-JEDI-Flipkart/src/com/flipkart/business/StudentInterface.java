package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface StudentInterface {
    public boolean isFeePaid();
    public String getGrades();
    public boolean addCourse(String id);
    public boolean  registerCourses();
    public ArrayList<Course> getRegisteredCourses();
    public boolean dropCourse();
}

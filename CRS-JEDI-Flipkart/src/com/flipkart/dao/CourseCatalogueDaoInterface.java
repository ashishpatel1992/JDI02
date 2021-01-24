package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.CourseNotFoundException;

import java.util.ArrayList;

public interface CourseCatalogueDaoInterface {
    public ArrayList<Course> getAllCourses();
    public Course getCourseDetail(String courseId) throws CourseNotFoundException;
    public boolean assignProfessorToCourse(String professorId, String courseId);
    public ArrayList<Course> getUnAssignedCourses();
    public ArrayList<Professor> getUnAssignedProfessors();
}

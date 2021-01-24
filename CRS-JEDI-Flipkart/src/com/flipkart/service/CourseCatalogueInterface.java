package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public interface CourseCatalogueInterface {
    /**
     * returns the list of all courses
     * @return array list of Courses
     */
    public ArrayList<Course> getCourseList();

    /**
     * creates a new course and add to course list and further add to database
     * @param courseId course ID
     * @param courseName name of the course
     * @return true if course added successfully
     */
    public boolean addCourse(String courseId, String courseName,String professorId);

    /**
     * Returns the course along with its details
     * @param courseId course ID
     * @return course
     */
    public Course getCourse(String courseId);

    /**
     * Deletes a specific course
     * @param courseId course ID
     * @return true if course deleted successfully
     */
    public boolean deleteCourse(String courseId);

    /**
     * Assigns a professor to a course
     * @param courseId course ID
     * @return true if professor assigned
     */
    public boolean assignProfessor(String courseId);
}

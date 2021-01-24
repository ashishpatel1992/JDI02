package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.ArrayList;

public interface CourseCatalogueInterface {
    /**
     * Returns the list of all courses
     * @return array list containing courses
     */
    public ArrayList<Course> getCourseList();

    /**
     * creates a new course and add to course list and further add to database
     * @param courseId course ID
     * @param courseName name of the course
     * @return true if course added
     */
    public boolean addCourse(String courseId, String courseName, String professorId);

    /**
     * Returns the course along with its details
     * @param courseId course ID
     * @return Course
     */
    public Course getCourse(String courseId);

    /**
     * Delete a specific course
     * @param courseId course ID
     * @return true if course deleted
     */
    public boolean deleteCourse(String courseId);

    /**
     * Assigns professor to a course
     * @param courseId course ID
     * @return true if professor assigned
     */
    public boolean assignProfessor(String courseId);

    /**
     * Get list of unassigned Courses
     * @return array list of unassigned courses
     */
    public ArrayList<Course> getUnAssignedCourses();

    /**
     * Get list of unassigned Professors
     * @return array list of unassigned professors
     */
    public ArrayList<Professor> getUnAssignedProfessors();
}

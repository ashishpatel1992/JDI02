package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.ArrayList;

/**
 * Class to Handle the operation of CourseCatalogue
 *
 * @Author -  Team JEDI 02
 */
public interface CourseCatalogueInterface {
    /**
     * Returns the list of all courses
     *
     * @return array list containing courses
     */
    ArrayList<Course> getCourseList();

    /**
     * creates a new course and add to course list and further add to database
     *
     * @param courseId   course ID
     * @param courseName name of the course
     * @return true if course added
     */
    boolean addCourse(String courseId, String courseName, String professorId, double fee);

    /**
     * Returns the course along with its details
     *
     * @param courseId course ID
     * @return Course
     */
    Course getCourse(String courseId);

    /**
     * Delete a specific course
     *
     * @param courseId course ID
     * @return true if course deleted
     */
    boolean deleteCourse(String courseId);

    /**
     * Get list of unassigned Courses
     *
     * @return array list of unassigned courses
     */
    ArrayList<Course> getUnAssignedCourses();

    /**
     * Get list of unassigned Professors
     *
     * @return array list of unassigned professors
     */
    ArrayList<Professor> getUnAssignedProfessors();
}

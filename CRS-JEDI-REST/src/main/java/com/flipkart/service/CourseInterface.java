package com.flipkart.service;

import com.flipkart.bean.Student;

import java.util.ArrayList;

/**
 * Interface for course operations
 *
 * @Author -  Team JEDI 02
 */
public interface CourseInterface {

    /**
     * Adds a course
     *
     * @return false if course not added
     */
    boolean addCourse();

    /**
     * Modifies a course
     *
     * @return false if course not modified
     */
    boolean modifyCourse();

    /**
     * Deletes a course
     *
     * @return false if course not deleted
     */
    boolean deleteCourse();

    /**
     * Get the students enrolled in course
     *
     * @return array list of the students enrolled
     */
    ArrayList<Student> getStudentsEnrolled();
}

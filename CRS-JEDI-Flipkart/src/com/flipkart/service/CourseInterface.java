package com.flipkart.service;

import com.flipkart.bean.Student;

import java.util.ArrayList;

/**
 * Interface for course operations
 */
public interface CourseInterface {

    /**
     * Adds a course
     * @return false if course not added
     */
    public boolean addCourse();

    /**
     * Modifies a course
     * @return false if course not modified
     */
    public boolean modifyCourse();

    /**
     * Deletes a course
     * @return false if course not deleted
     */
    public boolean deleteCourse();

    /**
     * Get the students enrolled in course
     * @return array list of the students enrolled
     */
    public ArrayList<Student> getStudentsEnrolled();
}

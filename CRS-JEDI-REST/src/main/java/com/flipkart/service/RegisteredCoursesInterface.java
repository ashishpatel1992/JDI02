package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface to handle Registered Courses Operations
 *
 * @Author -  Team JEDI 02
 */
public interface RegisteredCoursesInterface {
    /**
     * Registers the student into the course by taking list of courseIds
     *
     * @param courseIdSelectionList
     * @return returns the courses successfully registered
     */

    public ArrayList<String> registerCourses(ArrayList<String> courseIdSelectionList);

    /**
     * Get list of all courses student is registered in.
     *
     * @return array list of courses
     */
    public ArrayList<Course> getRegisteredCourses();

    /**
     * Drops a course in which student is already enrolled in.
     * @return true if dropped successfully
     */

    public boolean isRegistered();
}

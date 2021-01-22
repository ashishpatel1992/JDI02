package com.flipkart.service;

import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.HashMap;

public interface RegisteredCoursesInterface {
    /**
     * Registers the student into the course by taking list of courseIds
     * @param courseIdSelectionList
     * @return
     */

    public ArrayList<String> registerCourses(ArrayList<String> courseIdSelectionList);

    /**
     * Get list of all courses student is registered in.
     * @return
     */
    public ArrayList<Course> getRegisteredCourses();

    /**
     * Drop a course in which student is already enrolled in.
     * @param courseId
     * @return
     */
//    public boolean dropCourse(String courseId);

}

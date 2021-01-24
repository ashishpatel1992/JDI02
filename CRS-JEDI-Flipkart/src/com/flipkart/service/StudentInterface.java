package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for Student Operations
 */
public interface StudentInterface {
    /**
     * Adds course to selection list
     * @param courseId course ID
     * @return true if added successfully
     */
    public boolean addCourseToSelection(String courseId);

    /**
     * Gets course selection list
     * @return array list of courses selected
     */
    public ArrayList<String> getCourseSelection();

    /**
     * Drops course from selection
     * @param courseId course ID
     * @return true if course dropped successfully
     */
    public boolean dropCourseFromSelection(String courseId);

    /**
     * Gets list of registered courses
     * @return array list of courses
     */
    public ArrayList<Course> getRegisteredCourses();

    /**
     * Checks if fee is paid
     * @return true if paid
     */
    public boolean isFeePaid();

    /**
     * Gets student grades
     * @return hashmap of grades
     */
    public HashMap<String, String> getGrades();

    /**
     * Adds course
     * @param id course ID
     * @return true if added successfully
     */
    public boolean addCourse(String id);

    /**
     * Registers the courses selected
     * @return array list of courses registered
     */
    public ArrayList<String> registerCourses();

    /**
     * Gets student Profile
     * @return Student Profile
     */
    public Student getStudentProfile();
}

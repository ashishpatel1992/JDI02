package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for Student Operations
 *
 * @Author -  Team JEDI 02
 */
public interface StudentInterface {
    /**
     * Adds course to selection list
     *
     * @param courseId course ID
     * @return true if added successfully
     */
    boolean addCourseToSelection(String courseId);

    /**
     * Gets course selection list
     *
     * @return array list of courses selected
     */
    ArrayList<String> getCourseSelection();

    /**
     * Drops course from selection
     *
     * @param courseId course ID
     * @return true if course dropped successfully
     */
    boolean dropCourseFromSelection(String courseId);

    /**
     * Gets list of registered courses
     *
     * @return array list of courses
     */
    ArrayList<Course> getRegisteredCourses();

    /**
     * Checks if fee is paid
     *
     * @return true if paid
     */
    boolean isFeePaid();

    /**
     * Gets student grades
     *
     * @return hashmap of grades
     */
    HashMap<String, String> getGrades();


    /**
     * Registers the courses selected
     *
     * @return array list of courses registered
     */
    ArrayList<String> registerCourses();

    /**
     * Gets student Profile
     *
     * @return Student Profile
     */
    Student getStudentProfile();

    /**
     * Calculate total fee for a student
     *
     * @return fee amount
     */
    int getTotalFee();

    /**
     * Make payment of fee for a student
     *
     * @param paymentMethod method selected for making payment
     * @param fees          fees to be payed
     */
    boolean makePayment(int paymentMethod, int fees);
}

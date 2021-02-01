package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface to perform the following professor operations
 * 1. get course details
 * 2. get list of enrolled students
 * 3. grade enrolled students
 *
 * @Author -  Team JEDI 02
 */
public interface ProfessorInterface {

    /**
     * Get Professor details
     *
     * @return Professor details
     */
    Professor getProfessor();

    /**
     * Returns the course details
     *
     * @return Course
     */
    Course getCourseDetail();

    /**
     * Gets list of enrolled students
     *
     * @return Array List of Students
     */
    HashMap<String, String> getEnrolledStudents();

    /**
     * Grade students
     *
     * @param gradesOfStudents Hashmap containing Student Id and grades.
     * @return true if graded successfully
     */
    boolean gradeStudent(HashMap<String, String> gradesOfStudents);
}

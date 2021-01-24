package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

public interface ProfessorInterface {

    /**
     * Get Professor details
     * @return Professor details
     */
    public Professor getProfessor();

    /**
     * Returns the course details
     * @return Course
     */
    public Course getCourseDetail();

    /**
     * Gets list of enrolled students
     * @return Array List of Students
     */
    public HashMap<String,String> getEnrolledStudents();

    /**
     * Grade students
     * @param gradesOfStudents Hashmap containing Student Id and grades.
     * @return true if graded successfully
     */
    public boolean gradeStudent(HashMap<String,String> gradesOfStudents);
}

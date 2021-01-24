package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

public interface ProfessorInterface {
    /**
     * Returns the course details
     * @return Course
     */
    public Course getCourseDetail();
    /**
     * Gets list of enrolled students
     * @return Array List of Students
     */
    public ArrayList<Student>getEnrolledStudents();
    /**
     * Grade students
     * @param gradesOfStudents Hashmap containing Student Id and grades.
     * @return true if graded successfully
     */
    public boolean gradeStudent(HashMap<String,String> gradesOfStudents);
}

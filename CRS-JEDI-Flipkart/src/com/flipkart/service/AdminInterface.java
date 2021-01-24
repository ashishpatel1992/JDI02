package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface of admin operations
 */
public interface AdminInterface {
    /**
     * Get admin profile
     * @return profile of Admin
     */
    public Admin getAdminProfile();

    /**
     * Get list of unapproved students
     * @return array list of unapproved students
     */
    public ArrayList<Student> getUnApprovedStudents();

    /**
     * Adds professor to database
     * @param professorId professor ID
     * @param professorName name of professor
     * @param professorEmail email of professor
     * @param professorDepartment department of professor
     * @return user ID
     */
    public String addProfessor(String professorId, String professorName, String professorEmail, String professorDepartment,String password);

    /**
     * Approves student
     * @param studentId student ID
     * @return true if student is approved
     */
    public boolean approveStudent(String studentId);

    /**
     * Generates report card for a student
     * @param studentId student ID
     * @return HashMap<String, String> containing student id and grades
     */
    public HashMap<String, String> generateReportCard(String studentId);

    /**
     * Assigns professor to a course
     * @param professorId professor ID
     * @param courseId course ID
     * @return true if professor assigned
     */
    public boolean assignProfessorToCourse(String professorId, String courseId);
}
package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface of admin operations
 *
 * @Author -  Team JEDI 02
 */
public interface AdminInterface {
    /**
     * Get admin profile
     *
     * @return profile of Admin
     */
    Admin getAdminProfile();

    /**
     * Get list of unapproved students
     *
     * @return array list of unapproved students
     */
    ArrayList<Student> getUnApprovedStudents();

    /**
     * Adds professor to database
     *
     * @param professorId         professor ID
     * @param professorName       name of professor
     * @param professorEmail      email of professor
     * @param professorDepartment department of professor
     * @return user ID
     */
    String addProfessor(String professorId, String professorName, String professorEmail, String professorDepartment, String password);

    /**
     * Approves student
     *
     * @param studentId student ID
     * @return true if student is approved
     */
    boolean approveStudent(String studentId);

    /**
     * Generates report card for a student
     *
     * @param studentId student ID
     * @return HashMap<String, String> containing student id and grades
     */
    HashMap<String, String> generateReportCard(String studentId);

    /**
     * Assigns professor to a course
     *
     * @param professorId professor ID
     * @param courseId    course ID
     * @return true if professor assigned
     */
    boolean assignProfessorToCourse(String professorId, String courseId);
}
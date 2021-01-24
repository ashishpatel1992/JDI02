package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;

/**
 * Interface for Admin Dao
 */
public interface AdminDaoInterface {
    /**
     * Adds new course to catalogue
     * @param course course to add
     * @return true if course is added successfully
     */
    boolean addCourse(Course course);

    /**
     * Returns list of unapproved students
     * @return array list of un approved students
     */
    ArrayList<String> getUnApprovedStudentsIds();

    /**
     * Approves a student for registration
     * @param studentId Id of student to be approved
     * @return true if the student was successfully approved
     */
    boolean approveStudent(String studentId);

    /**
     * Assigns a course to professor
     * @param professorId id of professor to assign
     * @param courseId id of course to assign
     * @return true if professor was successfully assigned the course
     */
    boolean assignProfessorToCourse(String professorId, String courseId);

    Admin getAdminProfile(String adminId);

}

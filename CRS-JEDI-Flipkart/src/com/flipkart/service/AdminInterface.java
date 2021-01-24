package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminInterface {
//    public boolean addCourse();
    /**
     * Adds professor to database
     * @param professorId professor ID
     * @param professorName professor Name
     * @param professorEmail professor Email
     * @param professorDepartment professor Department
     * @return true if professor added successfully
     */
    public String addProfessor(String professorId,String professorName,String professorEmail,String professorDepartment);
//    public void viewCourses();
    /**
     * Approves student
     * @param studentId student ID
     * @return true if student approved
     */
    public boolean approveStudent(String studentId);
    /**
     * Generates report card for a student
     * @param studentId student ID
     * @return HashMap<String,String> Student and their grades
     */
    public HashMap<String,String> generateReportCard(String studentId);
}
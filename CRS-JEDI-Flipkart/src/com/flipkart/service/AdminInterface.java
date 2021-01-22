package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminInterface {
//    public boolean addCourse();
    public String addProfessor(String professorId,String professorName,String professorEmail,String professorDepartment);
//    public void viewCourses();
    public boolean approveStudent(String studentId);
    public HashMap<String,String> generateReportCard(String studentId);
}
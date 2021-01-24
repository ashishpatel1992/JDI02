package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.AdminDaoImp;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.LoginDaoImp;
import com.flipkart.dao.LoginDaoInterface;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to handle the following admin operations
 * 1. add new professor
 * 2. add new course
 * 3. approve student
 * 4. generate report card for a student
 */
public class AdminOperation implements AdminInterface{
    private static Logger logger = Logger.getLogger(AdminOperation.class);

//    @Override
//    public boolean addCourse() {
//        logger.info("Add Course");
//        return false;
//    }

    /**
     * Adds professor to database
     * @param professorId professor ID
     * @param professorName professor Name
     * @param professorEmail professor Email
     * @param professorDepartment professor Department
     * @return true if professor added successfully
     */
    @Override
    public String addProfessor(String professorId,String professorName,String professorEmail,String professorDepartment) {
        //logger.info("Add Professor");
        Professor newProfessor = new Professor(professorId,professorName,professorEmail,"professor",professorDepartment);
        LoginDaoInterface loginDaoInterface = new LoginDaoImp();
        String password = loginDaoInterface.addProfessor(newProfessor);
        return password;
    }

//    @Override
//    public void viewCourses() {
//        logger.info("View Courses");
//
//    }

    /**
     * Approves student
     * @param studentId student ID
     * @return true if student approved
     */
    @Override
    public boolean approveStudent(String studentId) {
        try{
            AdminDaoInterface adminDaoInterface = new AdminDaoImp();
            return adminDaoInterface.approveStudent(studentId);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Generates report card for a student
     * @param studentId student ID
     * @return HashMap<String,String> Student and their grades
     */
    @Override
    public HashMap<String,String> generateReportCard(String studentId) {
        try {
            StudentOperation studentOperation = new StudentOperation(studentId);
            HashMap<String, String> studentGrades = studentOperation.getGrades();

            return studentGrades;
        }catch (Exception e){
            return null;
        }
    }
}
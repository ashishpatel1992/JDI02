package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.LoginDaoImp;
import com.flipkart.dao.LoginDaoInterface;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles the following admin operations
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
     * Add professor to database
     * @param professorId
     * @param professorName
     * @param professorEmail
     * @param professorDepartment
     * @return boolean
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
     * Approve student
     * @param studentId
     * @return boolean
     */
    @Override
    public boolean approveStudent(String studentId) {
        try{
            //TODO: dao - update approve bit of student
            //temp print
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Generate report card for a student
     * @param studentId
     * @return HashMap<String,String>
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
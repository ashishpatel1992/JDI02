package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.*;
import com.flipkart.exception.CourseNotFoundException;
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
public class AdminOperation implements AdminInterface {
    private static Logger logger = Logger.getLogger(AdminOperation.class);

//    @Override
//    public boolean addCourse() {
//        logger.info("Add Course");
//        return false;
//    }

    /**
     * Add professor to database
     *
     * @param professorId
     * @param professorName
     * @param professorEmail
     * @param professorDepartment
     * @return boolean
     */
    @Override
    public String addProfessor(String professorId, String professorName, String professorEmail, String professorDepartment) {
        //logger.info("Add Professor");
        Professor newProfessor = new Professor(professorId, professorName, professorEmail, "professor", professorDepartment);
        LoginDaoInterface loginDaoInterface = LoginDaoImp.getInstance();
        String password = loginDaoInterface.addProfessor(newProfessor);
        return password;
    }

//    @Override
//    public void viewCourses() {
//        logger.info("View Courses");
//
//    }

    public ArrayList<Student> getUnApprovedStudents() {
        // TODO: Get unapproved student list displayed

        ArrayList<Student> unApprovedStudentList = new ArrayList<>();
        ArrayList<String> unapprovedStudentIds = AdminDaoImp.getInstance().getUnApprovedStudentsIds();

        for (String unApprovedStudentId : unapprovedStudentIds) {
            logger.info("Inside getUnApprovedStudents()");
            Student student = StudentDaoImp.getInstance().getStudent(unApprovedStudentId);
            unApprovedStudentList.add(student);
        }
        return unApprovedStudentList;
    }

    /**
     * Approve student
     *
     * @param studentId
     * @return boolean
     */
    @Override
    public boolean approveStudent(String studentId) {
        return AdminDaoImp.getInstance().approveStudent(studentId);
    }

    /**
     * Generate report card for a student
     *
     * @param studentId
     * @return HashMap<String, String>
     */
    @Override
    public HashMap<String, String> generateReportCard(String studentId) {
        try {
            StudentOperation studentOperation = new StudentOperation(studentId);
            HashMap<String, String> studentGrades = studentOperation.getGrades();

            return studentGrades;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean assignProfessorToCourse(String professorId, String courseId) {


        Course course = CourseCatalogueDaoImp.getInstance().getCourseDetail(courseId);

        if (course != null && course.getProfessorId() == null && ProfessorDaoImp.getInstance().getProfessor(professorId) != null) {
            // TODO: Another if condition to check if professor is already assigned to a course
            AdminDaoImp.getInstance().assignProfessorToCourse(professorId, courseId);
            return true;
        } else {
            // TODO: Cannot assign professor to Course Exception
            return false;
        }


    }
}
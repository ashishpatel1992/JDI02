package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.*;
import com.flipkart.exception.CourseNotFoundException;
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
public class AdminOperation implements AdminInterface {
    private static Logger logger = Logger.getLogger(AdminOperation.class);

    String adminId;

    /**
     * Constructor of the class which sets admin ID
     *
     * @param adminId admin ID
     */
    public AdminOperation(String adminId) {
        this.adminId = adminId;
    }

    /**
     * Adds professor to database
     *
     * @param professorId         professor ID
     * @param professorName       name of professor
     * @param professorEmail      email of professor
     * @param professorDepartment department of professor
     * @return user ID
     */
    @Override
    public String addProfessor(String professorId, String professorName, String professorEmail, String professorDepartment, String password) {
        Professor newProfessor = new Professor(professorId, professorName, professorEmail, "professor", professorDepartment);
        return LoginDaoImp.getInstance().addProfessor(newProfessor, password);
    }

    /**
     * Get admin profile
     *
     * @return profile of Admin
     */
    @Override
    public Admin getAdminProfile() {
        return AdminDaoImp.getInstance().getAdminProfile(adminId);
    }

    /**
     * Get list of unapproved students
     *
     * @return array list of unapproved students
     */
    public ArrayList<Student> getUnApprovedStudents() {

        ArrayList<Student> unApprovedStudentList = new ArrayList<>();
        ArrayList<String> unapprovedStudentIds = AdminDaoImp.getInstance().getUnApprovedStudentsIds();

        for (String unApprovedStudentId : unapprovedStudentIds) {
            Student student = StudentDaoImp.getInstance().getStudent(unApprovedStudentId);
            unApprovedStudentList.add(student);
        }
        return unApprovedStudentList;
    }

    /**
     * Approves student
     *
     * @param studentId student ID
     * @return true if student is approved
     */
    @Override
    public boolean approveStudent(String studentId) {
        return AdminDaoImp.getInstance().approveStudent(studentId);
    }

    /**
     * Generates report card for a student
     *
     * @param studentId student ID
     * @return HashMap<String, String> containing student id and grades
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

    /**
     * Assigns professor to a course
     *
     * @param professorId professor ID
     * @param courseId    course ID
     * @return true if professor assigned
     */
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
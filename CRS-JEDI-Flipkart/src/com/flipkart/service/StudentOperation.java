package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class to handle student operations
 */
public class StudentOperation implements StudentInterface {

    String studentId;

    private static Logger logger = Logger.getLogger(StudentOperation.class);
    ArrayList<String> courseIdSelectionList = new ArrayList<String>();

    CourseCatalogueInterface courseCatalogueOperation = new CourseCatalogueOperation();
    RegisteredCoursesOperation registeredCoursesOperation;

    /**
     * Constructor of class which sets the student ID
     * @param studentId Student ID
     */
    public StudentOperation(String studentId) {
        this.studentId = studentId;
//        this.registeredCoursesOperation = new RegisteredCoursesOperation(studentId);
        this.registeredCoursesOperation = new RegisteredCoursesOperation(studentId);
    }

    /**
     * Gets student details
     * @return Student details
     */
    public Student getStudent() {
        // TODO: fetch student info from DAO
        Student student = new Student("101", "Anish", "anish@gmail.com", "student", "CS", false);
        return student;
    }

    /**
     * Adds course to selection list
     * @param courseId course ID
     * @return true if added successfully
     */
    @Override
    public boolean addCourseToSelection(String courseId) {
        boolean flag = false;
        Course c = courseCatalogueOperation.getCourse(courseId);
        logger.info("addCourseTOSelection " + c.getId());
        if (c != null) {
            courseIdSelectionList.add(courseId);
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * Gets course selection list
     * @return array list of courses selected
     */
    @Override
    public ArrayList<String> getCourseSelection() {

        return courseIdSelectionList;
    }

    /**
     * Drops course from selection
     * @param courseId course ID
     * @return true if course dropped successfully
     */
    @Override
    public boolean dropCourseFromSelection(String courseId) {
        boolean flag = false;
        if (courseIdSelectionList.contains(courseId)) {
            courseIdSelectionList.remove(courseId);
            flag = true;

        } else {
            flag = false;
            logger.info("Course not present in the Selection list");
        }
        return flag;
    }

    /**
     * Registers the courses selected
     * @return array list of courses registered
     */
    @Override
    public ArrayList<String> registerCourses() {
        logger.info("REGISTER COURSES");
        return registeredCoursesOperation.registerCourses(courseIdSelectionList);
//        boolean flag = false;
//        if (registeredCoursesOperation.registerCourses(courseIdSelectionList)) {
//            flag = true;
//        } else {
//            flag = false;
//        }
//        return flag;
    }

    /**
     * Checks if fee is paid
     * @return true if paid
     */
    @Override
    public boolean isFeePaid() {
        logger.debug("check if isFeePaid");
        return false;
    }


    //TODO : review return type

    /**
     * Gets student grades
     * @return hashmap of grades
     */
    @Override
    public HashMap<String, String> getGrades() {
        logger.info("get grades");
        return null;
    }

    /**
     * Adds course
     * @param id course ID
     * @return true if added successfully
     */
    @Override
    public boolean addCourse(String id) {
        // TODO: Add a course at later point in time (Will see if needed)
        logger.info("Add Course");
        return false;
    }

    /**
     * Gets list of registered courses
     * @return array list of courses
     */
    @Override
    public ArrayList<Course> getRegisteredCourses() {
//        logger.info("get List of Registered Courses");

        return registeredCoursesOperation.getRegisteredCourses();
    }
}

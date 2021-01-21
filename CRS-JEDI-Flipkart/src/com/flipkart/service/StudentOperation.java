package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentOperation implements StudentInterface {
    String studentId;

    private static Logger logger = Logger.getLogger(StudentOperation.class);
    ArrayList<String> courseIdSelectionList = new ArrayList<String>();;
    CourseCatalogueInterface courseCatalogueOperation = new CourseCatalogueOperation();
    RegisteredCoursesOperation registeredCoursesOperation;


    public StudentOperation(String studentId){
        this.studentId = studentId;
        this.registeredCoursesOperation = new RegisteredCoursesOperation(studentId);
    }
    public Student getStudent(){
        // TODO: fetch student info from DAO
       Student student = new Student("101","Anish","anish@gmail.com","student","CS",false);
       return student;
    }
    @Override
    public boolean addCourseToSelection(String courseId) {
        boolean flag = false;
        Course c = courseCatalogueOperation.getCourse(courseId);
        if (c != null) {
            courseIdSelectionList.add(courseId);
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    @Override
    public ArrayList<String> getCourseSelection() {

        return courseIdSelectionList;
    }

    @Override
    public boolean removeCourseFromSelection(String courseId) {
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

    @Override
    public boolean registerCourses() {
        boolean flag = false;
        if(registeredCoursesOperation.registerCourses(courseIdSelectionList)){
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean isFeePaid() {
        logger.debug("check if isFeePaid");
        return false;
    }

    @Override
    public String getGrades() {
        logger.info("get grades");
        return null;
    }

    @Override
    public boolean addCourse(String id) {
        // TODO: Add a course at later point in time (Will see if needed)
        logger.info("Add Course");
        return false;
    }



    @Override
    public ArrayList<Course> getRegisteredCourses() {
        logger.info("get List of Registered Courses");

        return registeredCoursesOperation.getRegisteredCourses();
    }

    @Override
    public boolean dropCourse(String courseId) {
        return registeredCoursesOperation.dropCourse(courseId);
    }
}

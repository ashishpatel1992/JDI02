package com.flipkart.service;

import com.flipkart.bean.Student;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class CourseOperation implements CourseInterface{
    private static Logger logger = Logger.getLogger(CourseOperation.class);
    @Override
    public boolean addCourse() {

        logger.info("Add Course");
        return false;
    }

    @Override
    public boolean modifyCourse() {
        logger.info("Modify Course");
        return false;
    }

    @Override
    public boolean deleteCourse() {
        logger.info("Delete Course");
        return false;
    }

    @Override
    public ArrayList<Student> getStudentsEnrolled() {

        logger.info("get students enrolled");
        return null;
    }
}

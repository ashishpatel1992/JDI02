package com.flipkart.service;

import com.flipkart.bean.Course;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class AdminOperation implements AdminInterface{
    private static Logger logger = Logger.getLogger(AdminOperation.class);
    @Override
    public boolean addCourse() {
        logger.info("Add Course");
        return false;
    }

    @Override
    public boolean addProfessor() {
        logger.info("Add Professor");
        return false;
    }

    @Override
    public boolean addStudent() {
        logger.info("Add Student");

        return false;
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        logger.info("getAllCourses");
        return null;
    }

    @Override
    public boolean approveStudent() {

        logger.info("Approve Student");
        return false;
    }

    @Override
    public boolean generateReportCard() {

        logger.info("Generate Report");
        return false;
    }
}

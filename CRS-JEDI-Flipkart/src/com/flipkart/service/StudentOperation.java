package com.flipkart.service;

import com.flipkart.bean.Course;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentOperation implements StudentInterface {

    private static Logger logger = Logger.getLogger(StudentOperation.class);

    @Override
    public int menu() {
        Scanner scanner = new Scanner(System.in);
        logger.info("==== Student Menu ====");
        logger.info("1. Add Course");
        logger.info("2. Drop Course");
        logger.info("3. Register Course");
        logger.info("4. Get Grades");
        logger.info("5. Pay Fees");
        logger.info("6. Logout");


        int choice = scanner.nextInt();
        return choice;
//        switch (choice) {
//            case 1:
//                if (addCourse("101")){
//                    logger.info("Course added Successfully");
//                }else {
//                    logger.info("Unable to add course");
//                }
//                    break;
////            case 2:
////                if(dropCourse())
//        }
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

        logger.info("Add Course");
        return false;
    }

    @Override
    public boolean registerCourses() {
        logger.info("Register Course");
        return false;
    }

    @Override
    public ArrayList<Course> getRegisteredCourses() {
        logger.info("get List of Registered Courses");
        return null;
    }

    @Override
    public boolean dropCourse() {
        logger.info("Drop a Course");
        return false;
    }
}

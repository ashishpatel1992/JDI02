package com.flipkart.service;

import com.flipkart.bean.Student;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Class to handle course operations
 */

public class CourseOperation implements CourseInterface{
    private static Logger logger = Logger.getLogger(CourseOperation.class);

    /**
     * Adds a course
     * @return false if course not added
     */
    @Override
    public boolean addCourse() {

        logger.info("Add Course");
        return false;
    }

    /**
     * Modifies a course
     * @return false if course not modified
     */
    @Override
    public boolean modifyCourse() {
        logger.info("Modify Course");
        return false;
    }

    /**
     * Deletes a course
     * @return false if course not deleted
     */
    @Override
    public boolean deleteCourse() {
        logger.info("Delete Course");
        return false;
    }

    /**
     * Get the students enrolled in course
     * @return array list of the students enrolled
     */
    @Override
    public ArrayList<Student> getStudentsEnrolled() {

        logger.info("get students enrolled");
        return null;
    }
}

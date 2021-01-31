package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.dao.CourseCatalogueDaoImp;
import com.flipkart.dao.CourseCatalogueDaoInterface;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Class to handle course operations
 *
 * @Author -  Team JEDI 02
 */
public class CourseOperation implements CourseInterface {
    private static final Logger logger = Logger.getLogger(CourseOperation.class);

    String courseId;

    public CourseOperation(String courseId) {
        this.courseId = courseId;
    }

    /**
     * Adds a course
     *
     * @return false if course not added
     */
    @Override
    public boolean addCourse() {

        logger.info("Add Course");
        return false;
    }

    /**
     * Modifies a course
     *
     * @return false if course not modified
     */
    @Override
    public boolean modifyCourse() {
        logger.info("Modify Course");
        return false;
    }

    /**
     * Deletes a course
     *
     * @return false if course not deleted
     */
    @Override
    public boolean deleteCourse() {
        logger.info("Delete Course");
        return false;
    }

    /**
     * Get the students enrolled in course
     *
     * @return array list of the students enrolled
     */
    @Override
    public ArrayList<Student> getStudentsEnrolled() {
        CourseCatalogueDaoInterface courseCatalogueDaoImp = new CourseCatalogueDaoImp();
        logger.info("get students enrolled");
        return courseCatalogueDaoImp.getStudentsEnrolled(courseId);
    }
}

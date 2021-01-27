package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.*;
import com.flipkart.exception.CourseNotFoundException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to Handle the operation of CourseCatalogue
 *
 * @Author -  Team JEDI 02
 */
public class CourseCatalogueOperation implements CourseCatalogueInterface {


    private static Logger logger = Logger.getLogger(CourseCatalogueOperation.class);

    public CourseCatalogueOperation() {
    }

    /**
     * Returns the list of all courses
     *
     * @return array list containing courses
     */
    @Override
    public ArrayList<Course> getCourseList() {
        return CourseCatalogueDaoImp.getInstance().getAllCourses();
    }


    /**
     * creates a new course and add to course list and further add to database
     *
     * @param courseId   course ID
     * @param courseName name of the course
     * @return true if course added
     */
    @Override
    public boolean addCourse(String courseId, String courseName, String professorId, double courseFee) {

        Course course = null;

        if (professorId == null) {
            course = new Course(courseId, courseName, courseFee);
            return AdminDaoImp.getInstance().addCourse(course);
        } else {
            Professor professor = ProfessorDaoImp.getInstance().getProfessor(professorId);
            course = new Course(courseId, courseName, professorId, courseFee);
            return AdminDaoImp.getInstance().addCourse(course);
        }

    }

    /**
     * Returns the course along with its details
     *
     * @param courseId course ID
     * @return Course
     */
    @Override
    public Course getCourse(String courseId) {

        return CourseCatalogueDaoImp.getInstance().getCourseDetail(courseId);
    }

    /**
     * Delete a specific course
     *
     * @param courseId course ID
     * @return true if course deleted
     */
    @Override
    public boolean deleteCourse(String courseId) {
        // fetch object of that course id
        boolean flag = false;
        // TODO: Implement deleteCourse
//        for (int i = 0; i < courseList.size(); i++) {
//            if (courseList.get(i).getId() == courseId) {
//                courseList.remove(i);
//                flag = true;
//                break;
//            }
//        }

        logger.info("Delete Course");
        return flag;
    }

    /**
     * Get list of unassigned Courses
     *
     * @return array list of unassigned courses
     */
    @Override
    public ArrayList<Course> getUnAssignedCourses() {
        return CourseCatalogueDaoImp.getInstance().getUnAssignedCourses();
    }

    /**
     * Get list of unassigned Professors
     *
     * @return array list of unassigned professors
     */
    @Override
    public ArrayList<Professor> getUnAssignedProfessors() {
        return CourseCatalogueDaoImp.getInstance().getUnAssignedProfessors();
    }
}

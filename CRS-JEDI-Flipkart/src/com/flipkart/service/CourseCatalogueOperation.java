package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.CourseCatalogueDaoImp;
import com.flipkart.dao.CourseCatalogueDaoInterface;
import com.flipkart.dao.AdminDaoImp;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.exception.CourseNotFoundException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to Handle the operation of CourseCatalogue
 */
public class CourseCatalogueOperation implements CourseCatalogueInterface {


    private static Logger logger = Logger.getLogger(CourseCatalogueOperation.class);

//    ArrayList<Course> courseList = new ArrayList<Course>();

    public CourseCatalogueOperation() {
//        CourseCatalogueDaoInterface courseCatalogueDaoInterface = new CourseCatalogueDaoImp();
//        CourseCatalogueDaoInterface courseCatalogueDaoInterface = CourseCatalogueDaoImp.getInstance().getAllCourses();
//        courseList = courseCatalogueDaoInterface.getAllCourses();

        // TODO: Remove Dummy Data once connected to DB

//        ArrayList<String> courseName = new ArrayList<String>(){
//            {
//                add("OS");
//                add("DBMS");
//                add("ML");
//                add("AI");
//            }
//        };
//        for(int i=0;i<courseName.size();i++){
//            Course course = new Course(String.valueOf(200+i+1), courseName.get(i));
//            courseList.add(course);
//
//        }
    }

    /**
     * Returns the list of all courses
     * @return array list containing courses
     */
    @Override
    public ArrayList<Course> getCourseList() {
        logger.info("ViewCourses");
        return CourseCatalogueDaoImp.getInstance().getAllCourses();
    }


    /**
     * creates a new course and add to course list and further add to database
     * @param courseId course ID
     * @param courseName name of the course
     * @return true if course added
     */
    @Override
    public boolean addCourse(String courseId, String courseName, String professorId) {
        logger.info("Add Course");
        Course course = new Course(courseId, courseName, professorId);
//        courseList.add(course);
        AdminDaoInterface adminDaoInterface = AdminDaoImp.getInstance();
        return adminDaoInterface.addCourse(course);
        //TODO: add course list to database
        // TODO: We need to create professor before assigning him a course
        // TODO: a course will be hidden if no professor is assigned
        //return true;
    }

    /**
     * Returns the course along with its details
     * @param courseId course ID
     * @return Course
     */
    @Override
    public Course getCourse(String courseId) {

        return CourseCatalogueDaoImp.getInstance().getCourseDetail(courseId);
    }

    /**
     * Delete a specific course
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
     * Assigns professor to a course
     * @param courseId course ID
     * @return true if professor assigned
     */
    @Override
    public boolean assignProfessor(String courseId) {
        // TODO: do Assign Professor to course
//        Course course = null;
//        for(int i=0;i<courseList.size();i++){
//            if(courseList.get(i).getId() == courseId){
//
//                course = courseList.get(i);
//                break;
//            }
//        }
//        logger.info("Assign Professor");
//        return course;
        return true;
    }

    /**
     * Get list of unassigned Courses
     * @return array list of unassigned courses
     */
    @Override
    public ArrayList<Course> getUnAssignedCourses() {
        return CourseCatalogueDaoImp.getInstance().getUnAssignedCourses();
    }

    /**
     * Get list of unassigned Professors
     * @return array list of unassigned professors
     */
    @Override
    public ArrayList<Professor> getUnAssignedProfessors() {
        return CourseCatalogueDaoImp.getInstance().getUnAssignedProfessors();
    }
}

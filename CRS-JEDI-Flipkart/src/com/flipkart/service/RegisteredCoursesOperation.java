package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.constants.CRSConstants;
import com.flipkart.dao.NotificationDaoImp;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.RegisteredCoursesDaoImp;
import com.flipkart.dao.RegisteredCoursesDaoInterface;
import org.apache.log4j.Logger;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to handle Registered Courses Operations
 *
 * @Author -  Team JEDI 02
 */
public class RegisteredCoursesOperation implements RegisteredCoursesInterface {

    String studentId;


    private static Logger logger = Logger.getLogger(RegisteredCoursesOperation.class);

    public HashMap<String, HashMap<String, String>> studentCourseGradesMap = new HashMap<>();

    public RegisteredCoursesOperation() {
    }

    public RegisteredCoursesOperation(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentCourseGradesMap() {

    }

    public HashMap<String, HashMap<String, String>> getStudentCourseGradesMap() {
        return studentCourseGradesMap;
    }

    /**
     * Updates to database and also update local variable
     *
     * @param studentCourseGradesMap Map containing student's courses and grades
     */
    public void updateStudentCourseGradesMap(HashMap<String, HashMap<String, String>> studentCourseGradesMap) {

        this.studentCourseGradesMap = studentCourseGradesMap;
    }


    /**
     * Registers the student into the course by taking list of courseIds
     *
     * @param courseIdSelectionList List of selected courses
     * @return array list of registered courses
     */
    @Override
    public ArrayList<String> registerCourses(ArrayList<String> courseIdSelectionList) {
        ArrayList<String> registerdCourseList = null;
        if (courseIdSelectionList.size() != CRSConstants.MIN_COURSE_REQUIREMENT) {
            return null;
        }
        registerdCourseList = RegisteredCoursesDaoImp.getInstance().getCourseIdsForStudent(studentId);
        /**
         * if registerdCourseList is null then register the courses
         */
        if (registerdCourseList.size() < 1) {
            return RegisteredCoursesDaoImp.getInstance().doStudentRegistration(studentId, courseIdSelectionList);

        } else {
            return RegisteredCoursesDaoImp.getInstance().getCourseIdsForStudent(studentId);
        }
    }

    /**
     * Get list of all courses student is registered in.
     *
     * @return array list of courses
     */
    @Override
    public ArrayList<Course> getRegisteredCourses() {
        ArrayList<Course> registeredCourseList = new ArrayList<>();
        ArrayList<String> registeredCourseIdList = null;
        registeredCourseIdList = RegisteredCoursesDaoImp.getInstance().getCourseIdsForStudent(studentId);

        for (String registeredCourseId : registeredCourseIdList) {

            CourseCatalogueOperation courseCatalogueOperation = new CourseCatalogueOperation();
            Course registeredCourse = courseCatalogueOperation.getCourse(registeredCourseId);

            registeredCourseList.add(registeredCourse);
        }
        return registeredCourseList;
    }

    /**
     * Drops a course in which student is already enrolled in.
     *
     * @return true if dropped successfully
     */
    @Override
    public boolean isRegistered() {
        if (getRegisteredCourses().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
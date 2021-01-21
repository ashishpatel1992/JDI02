package com.flipkart.service;

import com.flipkart.bean.Course;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;


public class RegisteredCoursesOperation implements RegisteredCoursesInterface {
    /**
     * Handles the course registration Operations for student
     */

    String studentId;
    private static Logger logger = Logger.getLogger(RegisteredCoursesOperation.class);
    public HashMap<String, ArrayList<String>> registeredCourseIdList = new HashMap<String, ArrayList<String>>();
    public HashMap<String,HashMap<String,String>> gradesOfStudents = new HashMap<>();

    public RegisteredCoursesOperation() {
        //TODO Initialise registeredCourseIdList variable
    }
    public RegisteredCoursesOperation(String studentId) {

        this.studentId = studentId;
    }


    /**
     * Registers the student into the course by taking list of courseIds
     * @param courseIdSelectionList
     * @return
     */
    @Override
    public boolean registerCourses(ArrayList<String> courseIdSelectionList) {
        boolean flag = false;
        ArrayList<String> studentCourses = registeredCourseIdList.get(studentId);

        // TODO: set limit for number of courses 3>= no of course <=10

        if (studentCourses == null) {
            logger.info("Register Course");
            registeredCourseIdList.put(studentId, courseIdSelectionList);
            flag = true;
        } else {

            flag = false;
        }

        return flag;
    }

    /**
     * Get list of all courses student is registered in.
     * @return
     */
    @Override
    public ArrayList<Course> getRegisteredCourses() {
        ArrayList<Course> courseArrayList = new ArrayList<Course>();
        CourseCatalogueOperation courseCatalogueOperation = new CourseCatalogueOperation();
        if (registeredCourseIdList.containsKey(studentId)) {
            for (String courseId : registeredCourseIdList.get(studentId)) {
                Course c = courseCatalogueOperation.getCourse(courseId);
                courseArrayList.add(c);

            }
        }
        return courseArrayList;
    }

    /**
     * Drop a course in which student is already enrolled in.
     * @param courseId
     * @return
     */
    @Override
    public boolean dropCourse(String courseId) {
//        logger.info("DropCourse");
        ArrayList<String> studentCourseIdList= registeredCourseIdList.get(studentId);
        if(studentCourseIdList != null){
            for (String cid : studentCourseIdList) {
//            logger.info("Inside Loop: " + cid);
                if (cid.equalsIgnoreCase(courseId)) {
//                logger.info("courseDropped");
                    ArrayList<String> studentCourseIds = registeredCourseIdList.get(studentId);
                    studentCourseIds.remove(courseId);
                    registeredCourseIdList.put(studentId, studentCourseIds);
                    return true;
                }
            }
        }

        return false;
    }


}

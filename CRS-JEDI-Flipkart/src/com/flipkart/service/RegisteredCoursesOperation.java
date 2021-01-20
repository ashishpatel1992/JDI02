package com.flipkart.service;

import com.flipkart.bean.Course;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisteredCoursesOperation implements RegisteredCoursesInterface {
    String studentId;

    public RegisteredCoursesOperation(String studentId) {
        this.studentId = studentId;
    }

    private static Logger logger = Logger.getLogger(RegisteredCoursesOperation.class);
    public HashMap<String, ArrayList<String>> registeredCourseIdList = new HashMap<String, ArrayList<String>>();

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

    @Override
    public boolean dropCourse(String courseId) {
        logger.info("DropCourse");
        for (String cid : registeredCourseIdList.get(studentId)) {
            logger.info("Inside Loop: " + cid);
            if (cid.equalsIgnoreCase(courseId)) {
                logger.info("courseDropped");
                ArrayList<String> studentCourseIds = registeredCourseIdList.get(studentId);
                studentCourseIds.remove(courseId);
                registeredCourseIdList.put(studentId, studentCourseIds);
                return true;
            }
        }
        return false;
    }


}

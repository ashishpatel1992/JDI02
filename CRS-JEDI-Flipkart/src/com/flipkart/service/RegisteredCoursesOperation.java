package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.constants.CRSConstants;
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

    // TODO: Create method to write gradeOfStudents into database and fetch whenever object is created
    public HashMap<String, HashMap<String, String>> studentCourseGradesMap = new HashMap<>();

    public RegisteredCoursesOperation() {
    }

    public RegisteredCoursesOperation(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentCourseGradesMap() {
        // TODO: Fetch Values from Database and assign
//        studentCourseGradesMap = DatabaseMetaData();

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
        // TODO: Save to database studentCourseGradeMap variable
//        RegisterCourseDOA.update(studentCourseGradesMap);
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
//        boolean flag = false;
//        ArrayList<String> studentCoursesIdList = registeredCourseIdList.get(studentId);
//
//        // TODO: set limit for number of courses 3>= no of course <=10
//
//        if (studentCoursesIdList == null) {
//            logger.info("Register Course");
//            registeredCourseIdList.put(studentId, courseIdSelectionList);
//            flag = true;
//        } else {
//
//            flag = false;
//        }
//
//        return flag;
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
//        logger.info(registeredCourseIdList);

        for (String registeredCourseId : registeredCourseIdList) {
            // TODO: Not able to fetch course details
//            logger.info(registeredCourseId);
            CourseCatalogueOperation courseCatalogueOperation = new CourseCatalogueOperation();
            Course registeredCourse = courseCatalogueOperation.getCourse(registeredCourseId);
//            logger.info(registeredCourse.getName());
            registeredCourseList.add(registeredCourse);
        }

//        CourseCatalogueOperation courseCatalogueOperation = new CourseCatalogueOperation();
//        if (registeredCourseIdList.containsKey(studentId)) {
//            for (String courseId : registeredCourseIdList.get(studentId)) {
//                Course c = courseCatalogueOperation.getCourse(courseId);
//                courseArrayList.add(c);
//
//            }
//        }
        logger.info(registeredCourseIdList.size());
        return registeredCourseList;
    }

    /**
     * Drops a course in which student is already enrolled in.
     * @param courseId course ID
     * @return true if dropped successfully
     */
//    @Override
//    public boolean dropCourse(String courseId) {
////        logger.info("DropCourse");
//        ArrayList<String> studentCourseIdList = registeredCourseIdList.get(studentId);
//        if (studentCourseIdList != null) {
//            for (String cid : studentCourseIdList) {
////            logger.info("Inside Loop: " + cid);
//                if (cid.equalsIgnoreCase(courseId)) {
////                logger.info("courseDropped");
//                    ArrayList<String> studentCourseIds = registeredCourseIdList.get(studentId);
//                    studentCourseIds.remove(courseId);
//                    registeredCourseIdList.put(studentId, studentCourseIds);
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }


}

package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoImp;
import com.flipkart.dao.ProfessorDaoInterface;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Perform following professor operations
 * 1. get course details
 * 2. get list of enrolled students
 * 3. grade enrolled students
 */
public class ProfessorOperation implements ProfessorInterface {
    private static Logger logger = Logger.getLogger(ProfessorOperation.class);
    String professorId;
    ProfessorDaoInterface professorDaoInterface;
    /**
     * Constructor to set professor id
     *
     * @param professorId
     */
    public ProfessorOperation(String professorId) {
        this.professorId = professorId;
        professorDaoInterface = new ProfessorDaoImp();
    }


    /**
     * Returns the course details
     *
     * @return Course
     */
    @Override
    public Course getCourseDetail() {
        //logger.info("Get Course Detail");
        try {
            ArrayList<Course> courseArrayList = new CourseCatalogueOperation().getCourseList();

            boolean success = false;
            for (Course course : courseArrayList) {
                if (course.getProfessorId().equals(professorId)) {

                    return course;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * Gets list of enrolled students
     *
     * @return ArrayList<Student>
     */
    @Override
    public HashMap<String,String> getEnrolledStudents() {
        //logger.info("Get list of Enrolled Students");
        /*ArrayList<Student> studentsEnrolled = new ArrayList<>();
        try {
            String courseId = getCourseDetail().getId();
//            RegisteredCoursesOperation registeredCoursesOperation = new RegisteredCoursesOperation();
            // TODO: getter setter for registeredCourseIdList
//            HashMap<String, ArrayList<String>> registeredCourseIdList = registeredCoursesOperation.registeredCourseIdList;
            // TODO: Professor will break here
            HashMap<String, ArrayList<String>> registeredCourseIdList = null;


            for (Map.Entry<String, ArrayList<String>> studentHashCourseList : registeredCourseIdList.entrySet()) {
                if (studentHashCourseList.getValue().contains(courseId)) {

                    studentsEnrolled.add(new StudentOperation(studentHashCourseList.getKey()).getStudent());
                }
            }
            return studentsEnrolled;
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return studentsEnrolled;*/
        String courseId = getCourseDetail().getId();
        HashMap<String,String> studentsEnrolledList = professorDaoInterface.getEnrolledStudentsForCourse(courseId);
        return studentsEnrolledList;
    }

    /**
     * Grade students
     *
     * @param gradesOfStudents
     * @return boolean
     */
    @Override
    public boolean gradeStudent(HashMap<String, String> gradesOfStudents) {
        /*logger.info("Grade a student");
        try {
            RegisteredCoursesOperation registeredCoursesOperation = new RegisteredCoursesOperation();

            String courseId = getCourseDetail().getId();
            for (Map.Entry<String, String> studentGradeMap : gradesOfStudents.entrySet()) {
                String studentId = studentGradeMap.getKey();
                String studentGrade = studentGradeMap.getValue();
                HashMap<String,HashMap<String,String>> studentCourseGradesMap= registeredCoursesOperation.getStudentCourseGradesMap();
                HashMap<String,String> studentCourseGradeMap = studentCourseGradesMap.get(studentId);
                studentCourseGradeMap.put(courseId, studentGrade);
            }

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }*/
        String courseId = getCourseDetail().getId();
        return professorDaoInterface.enterGradesOfStudents(gradesOfStudents,courseId);
    }
}

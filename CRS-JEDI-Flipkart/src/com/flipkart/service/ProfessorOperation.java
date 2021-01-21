package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
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
public class ProfessorOperation implements ProfessorInterface{
    private static Logger logger = Logger.getLogger(ProfessorOperation.class);
    String professorId;

    /**
     * Constructor to set professor id
     * @param professorId
     */
    public ProfessorOperation(String professorId){
        this.professorId = professorId;

    }


    /**
     * Returns the course details
     * @return Course
     */
    @Override
    public Course getCourseDetail() {
        //logger.info("Get Course Detail");
        try{
            ArrayList<Course> courseArrayList = new CourseCatalogueOperation().getCourseList();
            boolean success = false;
            for(Course course:courseArrayList){
                if(course.getProfessorId().equals(professorId)){
                    return course;
                }
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    /**
     * Gets list of enrolled students
     * @return ArrayList<Student>
     */
    @Override
    public ArrayList<Student> getEnrolledStudents() {
        //logger.info("Get list of Enrolled Students");
        try {
            String courseId = getCourseDetail().getId();
            RegisteredCoursesOperation registeredCoursesOperation = new RegisteredCoursesOperation();
            HashMap<String, ArrayList<String>> registeredCourseIdList = registeredCoursesOperation.registeredCourseIdList;
            ArrayList<Student> studentsEnrolled = new ArrayList<>();

            for (Map.Entry<String, ArrayList<String>> student : registeredCourseIdList.entrySet()) {
                if (student.getValue().contains(courseId)) {

                    studentsEnrolled.add(new StudentOperation(student.getKey()).getStudent());
                }
            }
            return studentsEnrolled;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Grade students
     * @param gradesOfStudents
     * @return boolean
     */
    @Override
    public boolean gradeStudent(HashMap<String,String> gradesOfStudents) {
        //logger.info("Grade a student");
        try {
            RegisteredCoursesOperation registeredCoursesOperation = new RegisteredCoursesOperation();

            String courseId = getCourseDetail().getId();
            for (Map.Entry<String, String> grade : gradesOfStudents.entrySet()) {
                registeredCoursesOperation.gradesOfStudents.get(grade.getKey()).put(courseId, grade.getValue());
            }

            return true;
        }catch (Exception e){
            return false;
        }
    }
}

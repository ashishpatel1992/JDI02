package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ProfessorOperation implements ProfessorInterface{
    private static Logger logger = Logger.getLogger(ProfessorOperation.class);
    @Override
    public Course getCourseDetail() {
        logger.info("Get Course Detail");
        return null;
    }

    @Override
    public ArrayList<Student> getEnrolledStudents() {
        logger.info("Get list of Enrolled Students");
        return null;
    }

    @Override
    public String gradeStudent(Student student) {

        logger.info("Grade a student");
        return null;
    }
}

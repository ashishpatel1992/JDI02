package com.flipkart.service;

import com.flipkart.bean.CourseGradeCard;
import com.flipkart.dao.RegisteredCoursesDaoImp;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Class to handle Report Card Operations
 *
 * @Author -  Team JEDI 02
 */
public class ReportCardOperation implements ReportCardInterface {
    private static final Logger logger = Logger.getLogger(ReportCardOperation.class);
    private final String studentId;

    /**
     * Constructor for the class which sets the student ID
     *
     * @param studentId student ID
     */
    public ReportCardOperation(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Get grades of student
     *
     * @return array list containing grades
     */
    @Override
    public ArrayList<CourseGradeCard> getGrades() {
        return RegisteredCoursesDaoImp.getInstance().getCourseGradeCardForStudent(studentId);
    }
}

package com.flipkart.service;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Class to handle Report Card Operations
 *
 * @Author -  Team JEDI 02
 */
public class ReportCardOperation implements ReportCardInterface {
    private static Logger logger = Logger.getLogger(ReportCardOperation.class);
    private String studentId;

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
    public ArrayList<String> getGrades() {
        // TODO: Get Grades Implementation
        return null;
    }
}

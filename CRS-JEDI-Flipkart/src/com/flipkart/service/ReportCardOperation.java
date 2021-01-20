package com.flipkart.service;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ReportCardOperation implements ReportCardInterface {
    private static Logger logger = Logger.getLogger(ReportCardOperation.class);
    private String studentId;

    public ReportCardOperation(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public ArrayList<String> getGrades() {
        logger.info(studentId);
        logger.info("Get Grades for student");
        return null;
    }
}

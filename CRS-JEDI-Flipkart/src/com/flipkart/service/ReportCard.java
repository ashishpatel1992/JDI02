package com.flipkart.service;

import java.util.ArrayList;

/**
 * Class for Report Card
 */
public class ReportCard {
    String studentId;
    ArrayList<String> courseId;
    String grade;

    /**
     * Gets Student ID
     * @return student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets Student ID
     * @param studentId Student ID
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets Course ID
     * @return Course ID
     */
    public ArrayList<String> getCourseId() {
        return courseId;
    }

    /**
     * Sets Course ID
     * @param courseId Course ID
     */
    public void setCourseId(ArrayList<String> courseId) {
        this.courseId = courseId;
    }

    /**
     * Gets Grade
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets Grade
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }
}

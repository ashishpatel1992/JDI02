package com.flipkart.service;

import java.util.ArrayList;

public class ReportCard {
    String studentId;
    ArrayList<String> courseId;
    String grade;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public ArrayList<String> getCourseId() {
        return courseId;
    }

    public void setCourseId(ArrayList<String> courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

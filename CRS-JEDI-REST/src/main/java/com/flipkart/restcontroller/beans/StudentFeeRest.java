package com.flipkart.restcontroller.beans;

import com.flipkart.bean.Course;

import java.util.ArrayList;

public class StudentFeeRest {
    String studentId;
    double totalFees;

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    ArrayList<Course> courses;

    public StudentFeeRest(String studentId, double totalFees) {
        this.studentId = studentId;
        this.totalFees = totalFees;
        this.courses = null;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }
}

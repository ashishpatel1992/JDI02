package com.flipkart.restcontroller.beans;

import com.flipkart.bean.Course;

import java.util.ArrayList;

/**
 * Bean class for Student Fee
 * @Author -  Team JEDI 02
 */
public class StudentFeeRest {
    String studentId;
    double totalFees;

    /**
     * Get all courses
     * @return courses list
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Set the courses
     * @param courses
     */
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    ArrayList<Course> courses;

    /**
     * Constructor for the class
     * @param studentId
     * @param totalFees
     */
    public StudentFeeRest(String studentId, double totalFees) {
        this.studentId = studentId;
        this.totalFees = totalFees;
        this.courses = null;
    }

    /**
     * Gets student ID
     * @return student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets student ID
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the total fees
     * @return total fess
     */
    public double getTotalFees() {
        return totalFees;
    }

    /**
     * Sets the total fees
     * @param totalFees
     */
    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }
}

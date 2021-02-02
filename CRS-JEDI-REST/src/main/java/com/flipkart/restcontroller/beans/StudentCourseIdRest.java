package com.flipkart.restcontroller.beans;

import java.util.ArrayList;

/**
 * Bean class for Student Course IDs
 * @Author -  Team JEDI 02
 */
public class StudentCourseIdRest {
    ArrayList<String> studentCourseIdList;

    /**
     * Get the student course ID List
     * @return Student Course ID list
     */
    public ArrayList<String> getStudentCourseIdList() {
        return studentCourseIdList;
    }

    /**
     * Sets the Student Course ID list
     * @param studentCourseIdList
     */
    public void setStudentCourseIdList(ArrayList<String> studentCourseIdList) {
        this.studentCourseIdList = studentCourseIdList;
    }
}

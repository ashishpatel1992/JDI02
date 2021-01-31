package com.flipkart.service;

import com.flipkart.bean.CourseGradeCard;

import java.util.ArrayList;

/**
 * Interface for Report Card Operations
 *
 * @Author -  Team JEDI 02
 */
public interface ReportCardInterface {

    /**
     * Get grades of student
     *
     * @return array list containing grades
     */
    ArrayList<CourseGradeCard> getGrades();
}

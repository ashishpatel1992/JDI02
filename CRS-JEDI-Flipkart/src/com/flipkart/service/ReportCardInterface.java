package com.flipkart.service;

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
    public ArrayList<String> getGrades();
}

package com.flipkart.dao;

import java.util.ArrayList;

/**
 * Interface for registered course dao
 */
public interface RegisteredCoursesDaoInterface {
    /**
     * Returns list of course ids a student is enrolled in
     * @param studentId id of student for which the list of courses is required
     * @return arraylist of courseids the student is enrolled in
     */
    public ArrayList<String> getCourseIdsForStudent(String studentId);

    /**
     * Enrolls student in selected courses
     * @param studentId id of the student for which the courses have to be enrolled
     * @param courseIdSelectionList list of courseids which have to be enrolled for student
     * @return arraylist of courseids in which the student was enrolled
     */
    public ArrayList<String> doStudentRegistration(String studentId, ArrayList<String> courseIdSelectionList);

}

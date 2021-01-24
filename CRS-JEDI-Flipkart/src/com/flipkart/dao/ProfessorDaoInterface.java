package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface for professor dao
 */
public interface ProfessorDaoInterface {

    /**
     * Get details of professor with professor id
     * @param professorId if of professor for which the details are returned
     * @return details of professor in Professor object
     */
    Professor getProfessor(String professorId);

    /**
     * Returns list of students enrolled in a course
     * @param courseid if of course for which the list of enrolled students is returned
     * @return map of studentid, student name enrolled for course
     */
    HashMap<String,String> getEnrolledStudentsForCourse(String courseid);

    /**
     * Enters grades of student for a course in database
     * @param gradesOfStudents map of studentId, grade of students
     * @param courseId id of course for which grades have to be entered
     * @return true if grades were successfully entered else false
     */
    boolean enterGradesOfStudents(HashMap<String, String> gradesOfStudents,String courseId);

}

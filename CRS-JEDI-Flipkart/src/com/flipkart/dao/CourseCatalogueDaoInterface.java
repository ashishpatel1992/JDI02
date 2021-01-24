package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.CourseNotFoundException;

import java.util.ArrayList;

/**
 * Interface for Course Catalogue
 */
public interface CourseCatalogueDaoInterface {

    /**
     * Returns list of all courses from catalogue
     * @return arraylist of courses
     */
    public ArrayList<Course> getAllCourses();

    /**
     * Returns course details for a course
     * @param courseId id of course for which details will be returned
     * @return course details in Course object
     * @throws CourseNotFoundException thrown in case no course is found with provided courseid
     */
    public Course getCourseDetail(String courseId) throws CourseNotFoundException;

    /**
     * Assigns a professor to a course
     * @param professorId id of professor which has to be assigned to course
     * @param courseId id of course to which professor will be assigned
     * @return true if professor was successfully assigned to the course
     */
    public boolean assignProfessorToCourse(String professorId, String courseId);
    public ArrayList<Course> getUnAssignedCourses();
    public ArrayList<Professor> getUnAssignedProfessors();
}

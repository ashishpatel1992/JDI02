package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.ArrayList;

public interface CourseCatalogueInterface {
    /**
     * returns the list of all courses
     *
     * @return
     */
    public ArrayList<Course> getCourseList();

    /**
     * creates a new course
     *
     * @param courseId
     * @param courseName
     * @return
     */
    public boolean addCourse(String courseId, String courseName, String professorId);

    /**
     * Returns the course along with its details
     *
     * @param courseId
     * @return
     */
    public Course getCourse(String courseId);

    /**
     * Delete a specific course
     *
     * @param courseId
     * @return
     */
    public boolean deleteCourse(String courseId);

    /**
     * Assign a professor to a course
     *
     * @param courseId
     * @return
     */
    public boolean assignProfessor(String courseId);

    public ArrayList<Course> getUnAssignedCourses();

    public ArrayList<Professor> getUnAssignedProfessors();
}

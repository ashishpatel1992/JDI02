package com.flipkart.restcontroller.beans;

/**
 * Professor - bean class for Assign professor
 * @Author -  Team JEDI 02
 */
public class AssignProfessorRest {
    String professorId;
    String courseId;

    /**
     * Gets the professor ID
     * @return Professor ID
     */
    public String getProfessorId() {
        return professorId;
    }

    /**
     * Sets the Professor ID
     * @param professorId
     */
    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    /**
     * Gets the course ID
     * @return course ID
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Sets the course ID
     * @param courseId
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

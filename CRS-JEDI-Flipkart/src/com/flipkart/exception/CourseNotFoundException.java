package com.flipkart.exception;

/**
 * Custom exception class for course not found
 *
 * @Author -  Team JEDI 02
 */
public class CourseNotFoundException extends Exception {
    String courseId;

    /**
     * Constructor for class, initialises courseId property
     *
     * @param courseId
     */
    public CourseNotFoundException(String courseId) {
        super();
        this.courseId = courseId;
    }

    /**
     * Returns course id for which the exception is thrown
     *
     * @return id of course for which exception is thrown
     */
    public String getCourseId() {
        return courseId;
    }
}

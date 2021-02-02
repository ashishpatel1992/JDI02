package com.flipkart.exception;

public class CourseAlreadyExistException extends Exception {
    String courseId;

    /**
     * Constructor for class, initialises message
     *
     * @param courseId
     */
    public CourseAlreadyExistException(String courseId) {
        super();
        this.courseId = courseId;
    }
}

package com.flipkart.exception;

public class CourseDeleteException extends Exception {
    String courseId;

    /**
     * Constructor for class, initialises courseId property
     *
     * @param courseId
     */
    public CourseDeleteException(String courseId) {
        super();
        this.courseId = courseId;
    }
    // TODO: Allot Exception
}

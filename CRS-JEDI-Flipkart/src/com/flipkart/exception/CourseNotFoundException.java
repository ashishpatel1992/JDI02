package com.flipkart.exception;

/**
 * Custom exception class for course not found
 */
public class CourseNotFoundException extends Exception{
    String courseId;

    public CourseNotFoundException(String courseId){
        super();
        this.courseId=courseId;
    }

    public String getCourseId() {
        return courseId;
    }
}

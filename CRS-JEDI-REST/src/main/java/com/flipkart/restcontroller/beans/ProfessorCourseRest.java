package com.flipkart.restcontroller.beans;

import javax.validation.constraints.Size;

public class ProfessorCourseRest {
    @Size(min = 1, max = 15, message = "The length of Professor Id should be between 1 to 15")
    private String professorId;
    @Size(min = 1, max = 10, message = "The length of Course Id should be between 1 to 10")
    private String courseId;

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

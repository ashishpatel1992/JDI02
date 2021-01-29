package com.flipkart.restcontroller.beans;

import javax.validation.constraints.Size;

public class StudentIdRest {
    @Size(min = 1, max = 15, message = "The length of Student Id should be between 1 to 15")
    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}

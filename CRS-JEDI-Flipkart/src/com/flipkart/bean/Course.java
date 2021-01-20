package com.flipkart.bean;

import java.util.ArrayList;

public class Course {
    String id;
    String name;
    String professorId; // Professor Object
    ArrayList<Student> studentsEnrolled;
    public Course(String courseId, String courseName){
        id = courseId;
        name = courseName;
        professorId = null;
//        studentsEnrolled = null;
    }
    public ArrayList<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(ArrayList<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }


}

package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;

public interface ProfessorInterface {
    public Course getCourseDetail();
    public ArrayList<Student>getEnrolledStudents();
    public String gradeStudent(Student student);
}

package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

public interface ProfessorInterface {
    public Course getCourseDetail();
    public ArrayList<Student>getEnrolledStudents();
    public boolean gradeStudent(HashMap<String,String> gradesOfStudents);
}

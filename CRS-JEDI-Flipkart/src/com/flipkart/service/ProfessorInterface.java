package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

public interface ProfessorInterface {
    public Professor getProfessor();
    public Course getCourseDetail();
    public HashMap<String,String> getEnrolledStudents();
    public boolean gradeStudent(HashMap<String,String> gradesOfStudents);
}

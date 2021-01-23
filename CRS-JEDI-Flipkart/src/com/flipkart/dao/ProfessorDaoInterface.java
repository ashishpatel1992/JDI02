package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.HashMap;

public interface ProfessorDaoInterface {

    HashMap<String,String> getEnrolledStudentsForCourse(String courseid);

    boolean enterGradesOfStudents(HashMap<String, String> gradesOfStudents,String courseId);

}

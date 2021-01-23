package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.ArrayList;

public interface AdminDaoInterface {
    boolean addCourse(Course course);
    ArrayList<String> getUnApprovedStudentsIds();
    boolean approveStudent(String studentid);
    boolean assignProfessorToCourse(String professorId, String courseId);

}

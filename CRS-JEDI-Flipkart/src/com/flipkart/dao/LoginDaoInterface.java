package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface LoginDaoInterface {

    String login(String userId,String password);

    String addStudent(Student student);

    boolean addProfessor(Professor professor);

    boolean approveStudent(Student student);

}

package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface LoginDaoInterface {

    String login(String userId,String password);

    boolean addStudent(Student student,String password);

    boolean addProfessor(Professor professor,String password);

    boolean approveStudent(Student student);

}

package com.flipkart.dao;

import com.flipkart.bean.Student;

public interface StudentDaoInterface {
    public Student getStudent(String studentId);
    public boolean getApprovalStatus(String studentId);
    public boolean approveStudent(String studentId);
}

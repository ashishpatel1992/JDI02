package com.flipkart.dao;

import com.flipkart.bean.Course;

public interface AdminDaoInterface {
    boolean addCourse(Course course);
    boolean approveStudent(String studentid);

}

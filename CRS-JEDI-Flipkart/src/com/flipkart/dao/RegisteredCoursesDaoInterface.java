package com.flipkart.dao;

import java.util.ArrayList;

public interface RegisteredCoursesDaoInterface {
    public ArrayList<String> getCourseIdsForStudent(String studentId);
    public ArrayList<String> doStudentRegistration(String studentId, ArrayList<String> courseIdSelectionList);

}

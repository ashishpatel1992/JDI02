package com.flipkart.dao;

import com.flipkart.bean.Student;

/**
 * Interface for student dao
 */
public interface StudentDaoInterface {
    /**
     * Gets details of student by student id
     * @param studentId id of student for which details are returned
     * @return details of student in Student object
     */
    public Student getStudent(String studentId);

    /**
     * Gets approved status for a student by student id
     * @param studentId id of student for which the approved status is required
     * @return true if student is approved, else false
     */
    public boolean getApprovalStatus(String studentId);

    /**
     * Sets the approved status to 1(true) by student id
     * @param studentId id of student for which the status has to be set
     * @return true if it was successfully set, else false
     */
    public boolean approveStudent(String studentId);
}

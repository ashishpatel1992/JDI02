package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.util.HashMap;

/**
 * Interface for student dao
 *
 * @Author -  Team JEDI 02
 */
public interface StudentDaoInterface {
    /**
     * Gets details of student by student id
     *
     * @param studentId id of student for which details are returned
     * @return details of student in Student object
     */
    Student getStudent(String studentId);

    /**
     * Gets approved status for a student by student id
     *
     * @param studentId id of student for which the approved status is required
     * @return true if student is approved, else false
     */
    boolean getApprovalStatus(String studentId);

    /**
     * Sets the approved status to 1(true) by student id
     *
     * @param studentId id of student for which the status has to be set
     * @return true if it was successfully set, else false
     */
    boolean approveStudent(String studentId);

    /**
     * Calculate total fee for a student from database
     *
     * @return fee amount
     */
    int getTotalFee(String studentId);

    /**
     * Make payment of fee for a student
     *
     * @param studentId     if of student for which payment has to be made
     * @param paymentMethod method selected for making payment
     * @param fees          fees to be payed
     */
    boolean makePayment(String studentId, int paymentMethod, int fees);

}

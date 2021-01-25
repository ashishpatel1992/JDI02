package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

/**
 * Interface for login dao
 *
 * @Author -  Team JEDI 02
 */
public interface LoginDaoInterface {

    /**
     * Verify credentials and returns login status
     *
     * @param userId   id of user to login
     * @param password password of user
     * @return true if provided userid and password are correct
     */
    boolean login(String userId, String password);

    /**
     * Adds a new student to database
     *
     * @param student  details of student in Student object
     * @param password password of student
     * @return null if adding student failed
     */
    String addStudent(Student student, String password);

    /**
     * Adds a new professor to database
     *
     * @param professor details of professor in Professor object
     * @param password  password of professor
     * @return null if adding professor failed
     */
    String addProfessor(Professor professor, String password);

    /**
     * Check if a user already exists in database
     *
     * @param userId id of user to check
     * @return true if user already exists
     */
    boolean checkIfUserAlreadyExist(String userId);


}

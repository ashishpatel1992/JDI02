package com.flipkart.client;

import com.flipkart.bean.Student;
import com.flipkart.exception.UserAlreadyExistException;
import com.flipkart.service.StudentRegistrationOperation;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Class to handle Student Registration frontend operations
 *
 * @Author -  Team JEDI 02
 */
public class StudentRegistrationCRSClient {
    private static final Logger logger = Logger.getLogger(StudentRegistrationCRSClient.class);
    String studentId;
    String studentName;
    String studentEmail;
    String studentRole;
    String studentPassword;
    boolean studentApproved;
    String studentBranch;

    StudentRegistrationOperation studentRegistrationOperation;

    /**
     * Constructor of the class
     */
    StudentRegistrationCRSClient() {
        studentRole = "student";
        studentApproved = false;
        studentRegistrationOperation = new StudentRegistrationOperation();
    }


    public boolean isAlreadyExist() {
        return true;
    }

    /**
     * Registration Menu of student
     */
    public void registrationMenu() {
        Scanner scanner = new Scanner(System.in);

        logger.info("+-----------------------------+");
        logger.info(String.format("| %-28s|", "Student Registration "));
        logger.info("+-----------------------------+");
        logger.info("Enter your RollNumber (this will also be your userID) >");
        studentId = scanner.next();
        logger.info("Enter your Name >");
        studentName = scanner.next();
        logger.info("Enter your email >");
        studentEmail = scanner.next();
        logger.info("Enter your Branch >");
        studentBranch = scanner.next();
        logger.info("Enter your password >");
        studentPassword = scanner.next();
        // TODO: Display and Confirm information
        Student student = new Student(studentId, studentName, studentEmail, studentRole, studentBranch, studentApproved);
        String userId = studentRegistrationOperation.isRegistrationDataValid(student, studentPassword);
        if (userId != null) {
            logger.info(studentName + " Successfully registered with userId " + studentId + ", pending for admin approval.");
        } else {
            logger.info("Student Registration Unsuccessfull");
        }
    }
}

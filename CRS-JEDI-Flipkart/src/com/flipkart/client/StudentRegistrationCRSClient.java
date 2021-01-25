package com.flipkart.client;

import com.flipkart.bean.Student;
import com.flipkart.service.StudentRegistrationOperation;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Class to handle Student Registration frontend operations
 *
 * @Author -  Team JEDI 02
 */
public class StudentRegistrationCRSClient {
    private static Logger logger = Logger.getLogger(StudentRegistrationCRSClient.class);
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

        logger.info("=== Student Registration MENU ===");
        logger.info("Enter your RollNumber (this will also be your userID): ");
        studentId = scanner.next();
        logger.info("Enter your Name");
        studentName = scanner.next();
        logger.info("Enter your email");
        studentEmail = scanner.next();
        logger.info("Enter your Branch");
        studentBranch = scanner.next();
        logger.info("Enter your password");
        studentPassword = scanner.next();
        Student student = new Student(studentId, studentName, studentEmail, studentRole, studentBranch, studentApproved);
        String userId = studentRegistrationOperation.isRegistrationDataValid(student, studentPassword);
        if (userId != null) {
            logger.info(studentName + " Successfully registered, pending for admin approval");
            logger.info("Student Id : " + studentId);

        } else {
//            logger.info("Student ID "+studentId+" already exist");
            logger.info("Student Registration Unsuccessfull");
        }

    }
}

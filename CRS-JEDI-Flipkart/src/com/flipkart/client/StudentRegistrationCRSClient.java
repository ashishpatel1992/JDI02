package com.flipkart.client;

import com.flipkart.bean.Student;
import com.flipkart.service.StudentRegistrationOperation;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class StudentRegistrationCRSClient {
    private static Logger logger = Logger.getLogger(StudentRegistrationCRSClient.class);
    String studentId;
    String studentPassword;
    String studentName;
    String studentEmail;
    String studentRole;
    boolean studentApproved;
    String studentBranch;

    StudentRegistrationOperation studentRegistrationOperation;
    StudentRegistrationCRSClient(){
        studentRole = "student";
        studentApproved = false;
        studentRegistrationOperation = new StudentRegistrationOperation();
    }
    public boolean isAlreadyExist(){
        return true;
    }
    public void registrationMenu(){
        Scanner scanner = new Scanner(System.in);

        logger.info("=== Student Registration MENU ===");
        logger.info("Enter your RollNumber (this will also be your userID): ");
        studentId = scanner.next();
        logger.info("Enter your password ");
        studentPassword = scanner.next();
        // TODO: input password two times and compare later on to check if both passwords are same
        logger.info("Enter your Name");
        studentName = scanner.next();
        logger.info("Enter your email");
        studentEmail = scanner.next();
        logger.info("Enter your Branch");
        studentBranch = scanner.next();
        Student student = new Student(studentId,studentName,studentEmail,studentRole,studentBranch,studentApproved);
        if(studentRegistrationOperation.isRegistrationDataValid(student)){
            logger.info("Successfully registered, pending for admin approval");
        }else{
            logger.info("Student ID "+studentId+" already exist");
        }

    }
}

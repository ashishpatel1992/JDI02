package com.flipkart.client;

import com.flipkart.constants.CRSConstants;
import com.flipkart.service.*;
import org.apache.log4j.Logger;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;

/**
 * Main Course Registration System class containing the Menu
 *
 * @Author -  Team JEDI 02
 */
public class CRSApplication {
    private static Logger logger = Logger.getLogger(CRSApplication.class);
    public static boolean loginStatus = false;

    /**
     * Displays the main login menu for all users
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        try {
            while (true) {
                logger.info("+-----------------------------+");
                logger.info(String.format("| %-28s|", "Login MENU "));
                logger.info("+-----------------------------+");
                logger.info(String.format("| 1. %-24s |", "Student Registration"));
                logger.info(String.format("| 2. %-24s |", "Student"));
                logger.info(String.format("| 3. %-24s |", "Professor"));
                logger.info(String.format("| 4. %-24s |", "Admin"));
                logger.info(String.format("| 5. %-24s |", "Exit"));
                logger.info("+-----------------------------+");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        StudentRegistrationCRSClient studentRegistrationCRSClient = new StudentRegistrationCRSClient();
                        studentRegistrationCRSClient.registrationMenu();
                        break;
                    case 2:

                        logger.info("Please Login to proceed further");
                        String studentId = CRSApplication.authenticateUser();

                        if (studentId != null) {
                            printLoginTime();
                            StudentCRSClient studentCRSClient = new StudentCRSClient(studentId);
                            studentCRSClient.studentMenu();
                        } else {
                            logger.info(CRSConstants.INVALID_USERID_OR_PASSWORD);
                        }
                        break;
                    case 3:
                        String professorId = CRSApplication.authenticateUser();
                        if (professorId != null) {
                            printLoginTime();
                            ProfessorCRSClient professorCRSClient = new ProfessorCRSClient(professorId);
                            professorCRSClient.professorMenu();
                        } else {
                            logger.info(CRSConstants.INVALID_USERID_OR_PASSWORD);
                        }
                        break;
                    case 4:
                        String adminId = CRSApplication.authenticateUser();
                        if (adminId != null) {
                            printLoginTime();
                            AdminCRSClient adminCRSClient = new AdminCRSClient(adminId);
                            adminCRSClient.adminMenu();
                        } else {
                            logger.info("Invalid User Id or Password!");
                        }
                        break;
                    case 5:
                        logger.info("Exit is selected");
                        System.exit(0);
                        return;

                    default:
                        logger.info("Invalid Choice");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            scanner.close();
        }

    }

    public static void printLoginTime() {
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        ZonedDateTime date = ZonedDateTime.now(zoneId);
        logger.info("Login Successful at " + date.format(CRSConstants.dateTimeFormatter));
    }

    /**
     * Prompts user to enter his credentials and verify them.
     *
     * @return userId of the successfully logged in user
     */
    public static String authenticateUser() {
        String userId;
        String userPassword = null;
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter User ID >");
        userId = scanner.next();
        logger.info("Enter User Password >");
        userPassword = scanner.next();
        UserInterface userInterface = new UserOperation();
        // TODO: try catch can be done for handling authentication error
        return userInterface.verifyCredentials(userId, userPassword);
    }
}

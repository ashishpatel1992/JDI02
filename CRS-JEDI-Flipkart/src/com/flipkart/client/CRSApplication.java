package com.flipkart.client;

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
                logger.info("=== Login MENU ===");
                logger.info("Login As:-");
                logger.info("1. Student Registration");
                logger.info("2. Student");
                logger.info("3. Professor");
                logger.info("4. Admin");
                logger.info("5. Exit");

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
                            ZoneId zoneId = ZoneId.of("Asia/Kolkata");
                            ZonedDateTime date = ZonedDateTime.now(zoneId);
                            logger.info("Login Successful at : "+date);

                            StudentCRSClient studentCRSClient = new StudentCRSClient(studentId);
                            studentCRSClient.studentMenu();
                        } else {
                            logger.info("Invalid User Id or Password!");
                        }
                        break;
                    case 3:
                        String professorId = CRSApplication.authenticateUser();
                        if (professorId != null) {
                            ZoneId zoneId = ZoneId.of("Asia/Kolkata");
                            ZonedDateTime date = ZonedDateTime.now(zoneId);
                            logger.info("Login Successful at : "+date);
                            ProfessorCRSClient professorCRSClient = new ProfessorCRSClient(professorId);
                            professorCRSClient.professorMenu();
                        } else {
                            logger.info("Invalid User Id or Password!");
                        }
                        break;
                    case 4:
                        String adminId = CRSApplication.authenticateUser();
                        if (adminId != null) {
                            ZoneId zoneId = ZoneId.of("Asia/Kolkata");
                            ZonedDateTime date = ZonedDateTime.now(zoneId);
                            logger.info("Login Successful at : "+date);
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

    /**
     * Prompts user to enter his credentials and verify them.
     *
     * @return userId of the successfully logged in user
     */
    public static String authenticateUser() {
        String userId;
        String userPassword = null;
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter User ID ? ");
        userId = scanner.next();
        logger.info("Enter User Password ? ");
        userPassword = scanner.next();
        UserInterface userInterface = new UserOperation();
        // TODO: try catch can be done for handling authentication error
        return userInterface.verifyCredentials(userId, userPassword);
    }
}

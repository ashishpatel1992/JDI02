package com.flipkart.client;

import com.flipkart.service.*;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class CRSApplication {
    private static Logger logger = Logger.getLogger(CRSApplication.class);
    public static boolean loginStatus = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            logger.info("=== Login MENU ===");
            logger.info("Login As:-");
            logger.info("1. Student");
            logger.info("2. Professor");
            logger.info("3. Admin");
            logger.info("4. Exit");

            choice = scanner.nextInt();
            String userId;
            String userPassword;

            logger.info("Enter User ID ? ");
            userId = scanner.next();
            logger.info("Enter User Password ? ");
            userPassword = scanner.next();

            UserInterface userInterface = new UserOperation();
            StudentClient studentClient = new StudentClient(userId);

            if (loginStatus || userInterface.verifyCredentials(userId, userPassword)) {
                logger.info("User Authenticated");
                loginStatus = true;
                switch (choice) {
                    case 1:
                        logger.info("Student is selected");
                        studentClient.studentMenu();
                        break;
                    case 2:
                        logger.info("Professor is selected");
                        break;
                    case 3:
                        logger.info("Admin is selected");
                        break;
                    case 4:
                        logger.info("Exit is selected");
                        System.exit(0);
                        return;

                    default:
                        logger.info("Student is selected");


                }
            } else {
                logger.info("Invalid userId or password");
            }

        }


    }
}

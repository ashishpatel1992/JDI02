package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseGradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.CRSConstants;

import com.flipkart.dao.NotificationDaoImp;
import com.flipkart.dao.NotificationDaoInterface;

import com.flipkart.dao.ProfessorDaoImp;

import com.flipkart.service.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to handle Student frontend operations
 *
 * @Author -  Team JEDI 02
 */
public class StudentCRSClient {

    private static final Logger logger = Logger.getLogger(StudentCRSClient.class);

    CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
    StudentInterface studentInterface;
    String studentId;
    Scanner scanner = new Scanner(System.in);

    /**
     * Initialize Constructor with studentId
     *
     * @param studentId student ID
     */
    public StudentCRSClient(String studentId) {
        this.studentId = studentId;
        studentInterface = new StudentOperation(studentId);
    }

    /**
     * Displays frontend menu for student
     */
    public void studentMenu() {
        int choice;

        Student student = studentInterface.getStudentProfile();
        if (student == null) {
            return;
        }
        if (!student.isApproved()) {
            logger.info(student.getName() + " your approval is pending.");
            return;
        }
        logger.info("Welcome " + student.getName() + ". You are logged in as " + student.getRole() + ".");

        while (true) {
            logger.info("+---------------------------------+");
            logger.info(String.format("| %-32s|", "STUDENT MENU"));
            logger.info("+---------------------------------+");
            logger.info(String.format("| 1. %-28s |", "View Available Courses"));
            logger.info(String.format("| 2. %-28s |", "Add Course to selection"));
            logger.info(String.format("| 3. %-28s |", "Drop Course from selection"));
            logger.info(String.format("| 4. %-28s |", "Register Course"));
            logger.info(String.format("| 5. %-28s |", "View Registered Courses"));
            logger.info(String.format("| 6. %-28s |", "Pay Fee"));
            logger.info(String.format("| 7. %-28s |", "View Notifications"));
            logger.info(String.format("| 8. %-28s |", "Get Grades"));
            logger.info(String.format("| 9. %-28s |", "Logout"));
            logger.info("+---------------------------------+");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableCourses();
                    break;
                case 2:
                    printAddCourseToSelectionInfo();
                    break;
                case 3:
                    printDropCourseFromSelectionInfo();
                    break;
                case 4:
                    printDoRegisterCourseInfo();
                    break;
                case 5:
                    printRegisteredCourseInfo();
                    // TODO: Inform user if payment fees is not paid
                    break;
                case 6:
                    payFees();
                    break;
                case 7:
                    displayNotifications();
                    break;
                case 8:
                    printReportCardInfo();
                    break;

                case 9:
                    // TODO Define login/logout enums
                    studentId = null;
                    return;
                default:
                    logger.info("Invalid Choice");
            }
        }
    }

    /**
     * Displays list of available courses in log
     */
    public void viewAvailableCourses() {
        if (!isRegistered()) {
            ArrayList<Course> courseArrayList = courseCatalogueInterface.getCourseList();
            if (!courseArrayList.isEmpty()) {
                logger.info("+-----------------------------------------------------+");
                logger.info(String.format("| %-52s|", "AVAILABLE COURSES"));
                logger.info("+-----------------------------------------------------+");
                logger.info(String.format("| %-8s | %-10s | %-11s | %-13s |", "CourseId", "CourseName", "ProfessorId", "ProfessorName"));
                logger.info("+-----------------------------------------------------+");
                for (Course course : courseArrayList) {
                    if (course.getProfessorId() != null) {
                        String professorId = course.getProfessorId();
                        if (professorId == null) {
                            logger.info(String.format("| %8s %10s", course.getId(), course.getName()));
                        } else {
                            Professor professor = new ProfessorOperation(professorId).getProfessor();
                            if (professor == null) {
                                logger.info(String.format("%8s %10s", course.getId(), course.getName()));
                            } else {
                                logger.info(String.format("| %-8s | %-10s | %-11s | %-13s |", course.getId(), course.getName(), professor.getId(), professor.getName()));
                            }

                        }
                    }
                }
                logger.info("+-----------------------------------------------------+");
            } else {
                logger.info("No Courses available to display.");
            }
        } else {
            logger.info("You are already Registered.");
        }


    }

    /**
     * Checks if student is already registered for courses
     *
     * @return returns true if student is already registered and if registration is pending returns false
     */
    boolean isRegistered() {
        RegisteredCoursesOperation registeredCoursesOperation = new RegisteredCoursesOperation(studentId);
        return registeredCoursesOperation.isRegistered();
    }

    /**
     * Adds course to selection cart
     */
    public void printAddCourseToSelectionInfo() {

        if (!isRegistered()) {
            String courseId;
            logger.info("Please choose CourseId from following list:-");
            viewAvailableCourses();
            logger.info("Enter CourseId >");
            courseId = scanner.next();
            // TODO: Hide courses without professor

            if (studentInterface.getCourseSelection().contains(courseId)) {
                logger.info("Course Already in your Selection List");
                return;
            } else {
                try {
                    if (studentInterface.addCourseToSelection(courseId)) {

                        logger.info("Course " + courseId + " - " + courseCatalogueInterface.getCourse(courseId).getName() + " added successfully");
                    } else {
                        logger.info("Invalid CourseId entered");
                        logger.info("Please choose CourseId from following list:-");
                        viewAvailableCourses();
                    }
                    printCourseSelectionInfo();

                } catch (Exception e) {
                    logger.info("Invalid CourseId entered");
                    logger.info("Please choose CourseId from following list:-");
                    viewAvailableCourses();
                }
            }
            if (studentInterface.getCourseSelection().size() >= CRSConstants.MIN_COURSE_REQUIREMENT) {
                logger.info("Minimum Course requirement fulfilled. You can proceed for registration");
            }

        } else {
            logger.info("You are already Registered.");
        }


    }

    /**
     * Displays list of courses selected for registration
     * Similar to cart functionality
     */
    public void printCourseSelectionInfo() {
        ArrayList<String> courseIdSelectionList = studentInterface.getCourseSelection();
        logger.info("+-----------------------------------------------------+");
        logger.info(String.format("| %-52s|", "SELECTED COURSES FOR REGISTRATION"));
        logger.info("+-----------------------------------------------------+");
        logger.info(String.format("| %-51s |", "You have selected following " + courseIdSelectionList.size() + " courses:"));
        logger.info("+-----------------------------------------------------+");
        logger.info(String.format("| %-8s | %-40s |", "CourseId", "CourseName"));
        logger.info("+-----------------------------------------------------+");

        for (int i = 0; i < courseIdSelectionList.size(); i++) {
            String courseId = courseIdSelectionList.get(i);
            Course course = courseCatalogueInterface.getCourse(courseId);
            if (course != null) {
                logger.info(String.format("| %-8s | %-40s |", course.getId(), course.getName()));
            } else {
                logger.info("Invalid Course ID " + courseId);
            }
        }
        logger.info("+-----------------------------------------------------+");
    }


    /**
     * Removes a course from Selection cart
     */
    public void printDropCourseFromSelectionInfo() {
        if (!isRegistered()) {
            printCourseSelectionInfo();
            logger.info("Enter course Id to drop >");
            String courseId;
            courseId = scanner.next();
            if (studentInterface.dropCourseFromSelection(courseId)) {
                logger.info("Course " + courseId + " has been removed.");
            } else {
                logger.info("Course not present in the Selection list");
            }
            printCourseSelectionInfo();
        } else {
            logger.info("You are already Registered.");
        }

    }

    /**
     * Displays registered courses information
     */
    public void printDoRegisterCourseInfo() {
        if (!isRegistered()) {
            printCourseSelectionInfo();
            if (studentInterface.registerCourses() != null) {
                printRegisteredCourseInfo();
            } else {
                logger.info("You have selected " + studentInterface.getCourseSelection().size() + " course(s). Please select " + CRSConstants.MIN_COURSE_REQUIREMENT + " courses.");
            }

        } else {
            logger.info("You are already Registered.");
        }

    }

    /**
     * Displays registered courses information
     */
    public void printRegisteredCourseInfo() {

        ArrayList<Course> registeredCourseList = studentInterface.getRegisteredCourses();
        if (registeredCourseList.size() > 0) {
            logger.info("+----------------------------------------------+");
            logger.info(String.format("| %-44s |", "REGISTERED COURSES"));
            logger.info(String.format("| %-44s |", "You are registered for following courses:"));
            logger.info("+----------------------------------------------+");
            logger.info(String.format("| %-8s | %-15s | %-15s |", "CourseId", "CourseName", "ProfessorId"));
            logger.info("+----------------------------------------------+");
            for (Course regCourse : registeredCourseList) {
                String regCourseId = regCourse.getId();
                String regCourseName = regCourse.getName();
                String regCourseProfessorName = "";
                if (regCourse.getProfessorId() != null) {
                    regCourseProfessorName = ProfessorDaoImp.getInstance().getProfessor(regCourse.getProfessorId()).getName();
                }
                logger.info(String.format("| %-8s | %-15s | %-15s |", regCourseId, regCourseName, regCourseProfessorName));

            }
            logger.info("+----------------------------------------------+");
        } else {
            logger.info("Either you dont have any course or You havent completed the registration yet.");
        }
    }

    public void printRegisteredCourseInfoWithFee() {

        ArrayList<Course> registeredCourseList = studentInterface.getRegisteredCourses();
        if (registeredCourseList.size() > 0) {
            logger.info(String.format("| %-44s |", "You are registered for following courses:"));
            logger.info("+----------------------------------------------+");
            logger.info(String.format("| %-8s | %-15s | %-15s |", "CourseId", "CourseName", "Fee"));
            logger.info("+----------------------------------------------+");
            for (Course regCourse : registeredCourseList) {
                String regCourseId = regCourse.getId();
                String regCourseName = regCourse.getName();
                double courseFee = regCourse.getFee();

                logger.info(String.format("| %-8s | %-15s | %-15s |", regCourseId, regCourseName, courseFee));

            }
            logger.info("+----------------------------------------------+");
        } else {
            logger.info("Either you dont have any course or You havent completed the registration yet.");
        }
    }

    /**
     * Displays report card information
     */
    public void printReportCardInfo() {
        // TODO: Finish Get Grades Feature
        ReportCardOperation reportCardOperation = new ReportCardOperation(studentId);
        ArrayList<CourseGradeCard> courseGradeCards = reportCardOperation.getGrades();
        // TODO: Fix with if else condition whether registration over or not
        if (courseGradeCards.size() > 0) {

            logger.info("+----------------------------------------------+");
            logger.info(String.format("| %-44s |", "REPORT CARD"));
            logger.info("+----------------------------------------------+");
            logger.info(String.format("| %-8s | %-15s | %-15s |", "CourseId", "CourseName", "Grade"));
            logger.info("+----------------------------------------------+");
            for (CourseGradeCard courseGradeCard : courseGradeCards) {
                String gradCardCourseId = courseGradeCard.getCourse().getId();
                String gradeCardCourseName = courseGradeCard.getCourse().getName();
                String gradeCardGrade = courseGradeCard.getGrade();

                logger.info(String.format("| %-8s | %-15s | %-15s |", gradCardCourseId, gradeCardCourseName, gradeCardGrade));

            }
            logger.info("+----------------------------------------------+");
        } else {
            logger.info(CRSConstants.NO_COURSE_ASSIGNED);
        }
    }


    void payFees() {
        if (isRegistered()) {
            int fee = studentInterface.getTotalFee();
            logger.info("+----------------------------------------------+");
            logger.info(String.format("| %-44s |", "FEE PAYMENT"));
            logger.info("+----------------------------------------------+");
            printRegisteredCourseInfoWithFee();
            logger.info("Total fee is Rs." + fee + ". Type 'yes' to continue...");
            String choiceSelected = scanner.next().toLowerCase();
            if (choiceSelected.equals("yes")) {
                logger.info("Pay via: ");
                logger.info("1. Credit Card");
                logger.info("2. Debit Card");
                logger.info("3. Cash");
                int choice = Integer.parseInt(scanner.next());
                if (choice >= 1 && choice <= 3) {
                    if (studentInterface.makePayment(choice, fee)) {
                        logger.info("Payment Successful");
                    } else {
                        logger.info("Payment failed.");
                    }
                } else {
                    logger.info("Cannot do such payment!!");
                }
            } else {
                logger.info("Transaction Cancelled by user");
            }
        } else {
            logger.info("You need to complete registration before paying fee.");
        }

    }

    /**
     * Display notifications for student
     */
    void displayNotifications() {
        NotificationDaoInterface notificationDaoInterface = NotificationDaoImp.getInstance();
        ArrayList<String> notifications = notificationDaoInterface.getNotificationsForStudent(studentId);
        if (notifications == null) {
            logger.info("No notifications available.");
        } else if (notifications.size() == 0) {
            logger.info("No notifications available.");
        } else {
            logger.info("+------------------------------------------------------------------------------------------------------+");
            logger.info(String.format("| %-100s |", "NOTIFICATIONS"));
            logger.info("+------------------------------------------------------------------------------------------------------+");
            for (String notificationText : notifications) {

                logger.info(String.format("| %-99s |", notificationText));
            }
            logger.info("+------------------------------------------------------------------------------------------------------+");
        }

    }

}

package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to handle Student frontend operations
 */
public class StudentCRSClient {

    private static Logger logger = Logger.getLogger(StudentCRSClient.class);
    //    ArrayList<String> courseIdSelectionList;
    CourseCatalogueInterface courseCatalogueOperation = new CourseCatalogueOperation();
    StudentInterface studentInterface;
    // TODO: if made static will it be shared with everyone? i guess yes! so avoiding it find better way for accessing studentId for session
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
     * Displays list of available courses in log
     */
    public void viewAvailableCourses() {
//        ArrayList<Course> courseArrayList = courseCatalogueOperation.getCourseList();
        ArrayList<Course> courseArrayList = courseCatalogueOperation.getCourseList();
        if (!courseArrayList.isEmpty()) {
            for (Course course : courseArrayList) {
                // TODO: Fetch Professor Name and print when Professor is implemented
                if (course.getProfessorId() == null) {
                    logger.info(course.getId() + " " + course.getName() + " N/A \t N/A");
                } else {
                    Professor professor = new ProfessorOperation(course.getProfessorId()).getProfessor();
                    logger.info(course.getId() + " " + course.getName() + " " + professor.getName() + " " + professor.getDepartment());
                }

            }
        } else {
            logger.info("No Courses available to display.");
        }

    }

    /**
     * Adds course to selection cart
     */
    public void printAddCourseToSelectionInfo() {

        String courseId;

        logger.info("Enter course Id");
        courseId = scanner.next();
        // TODO: Avoid same course to be added again
        try {
            if (studentInterface.addCourseToSelection(courseId)) {

                logger.info("Course " + courseId + " added successfully");
            } else {
                logger.info("Invalid CourseId entered");
                logger.info("Please choose CourseId from following list:-");
                viewAvailableCourses();
            }
            printCourseSelectionInfo();
        }catch (Exception e){
            logger.info("Invalid CourseId entered");
            logger.info("Please choose CourseId from following list:-");
            viewAvailableCourses();
        }
    }

    /**
     * Displays list of courses selected for registration
     * Similar to cart functionality
     */
    public void printCourseSelectionInfo() {
        ArrayList<String> courseIdSelectionList = studentInterface.getCourseSelection();
        logger.info("You have selected following " + courseIdSelectionList.size() + " courses:");
        logger.info("CourseId\tCourseName");
        for (int i = 0; i < courseIdSelectionList.size(); i++) {
            String cid = courseIdSelectionList.get(i);
            Course c = courseCatalogueOperation.getCourse(cid);
            if (c != null) {
                logger.info(c.getId() + "\t\t" + c.getName());
            } else {
                logger.info("\t\tInvalid Course");
            }

        }
    }


    /**
     * Removes a course from Selection cart
     */
    public void printDropCourseFromSelectionInfo() {
        logger.info("Enter course Id");
        String courseId;
        courseId = scanner.next();
        if (studentInterface.dropCourseFromSelection(courseId)) {
            logger.info("Course " + courseId + " has been removed.");
        } else {
            logger.info("Course not present in the Selection list");
        }
        printCourseSelectionInfo();
    }

    /**
     * Displays registered courses information
     */
    public void printDoRegisterCourseInfo() {
        printCourseSelectionInfo();
        if (studentInterface.getCourseSelection().size() > 0) {
            if (studentInterface.registerCourses() != null) {
                ArrayList<Course> courseArrayList = studentInterface.getRegisteredCourses();
                logger.info("You are successfully registered for following courses.");
                logger.info("CourseId\tCourseName");
                for (Course regCourse : courseArrayList) {
                    logger.info(regCourse.getId() + "\t\t" + regCourse.getName() + "\t\t" + regCourse.getProfessorId());
                }
            }else{
                logger.info("You have selected "+studentInterface.getCourseSelection().size()+" course(s). Please select 6 courses.");
            }
        }
    }

    /**
     * Displays registered courses information
     */
    public void printRegisteredCourseInfo() {
        logger.info("You are registered for following courses:- ");

        ArrayList<Course> registeredCourseList = studentInterface.getRegisteredCourses();
        logger.info(registeredCourseList.size());
        if (registeredCourseList.size() > 0) {
            logger.info("CourseId\tCourseName");
            for (Course regCourse : registeredCourseList) {
                logger.info(regCourse.getId() + "\t\t" + regCourse.getName() + "\t\t" + regCourse.getProfessorId());
            }
        } else {
            logger.info("Either you dont have any course or You havent completed the registration yet.");
        }
    }

    /**
     * Displays report card information
     */
    public void printReportCardInfo() {
        ReportCardOperation reportCardOperation = new ReportCardOperation(studentId);
        reportCardOperation.getGrades();
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
            logger.info("==== Student MENU =====");
            logger.info("1. View Available Courses");
            logger.info("2. Add Course to selection");
            logger.info("3. Drop Course from selection");
            logger.info("4. Register Course");
            logger.info("5. View Registered Course");
            logger.info("6. Get Grades");
            logger.info("7. Pay Fee");
            logger.info("8. Logout");

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
                    break;
                case 6:
                    printReportCardInfo();
                    break;
                case 7:
                    payFees();
                    break;
                case 8:
                    // TODO Define login/logout enums
                    studentId = null;
                    return;
                default:
                    logger.info("Invalid Choice");
            }
        }
    }

    void payFees() {
        int fee = studentInterface.getTotalFee();
        logger.info("Total fee is Rs."+ fee + " press 'yes' to continue...");
        String choiceSelected = scanner.next();
        if(choiceSelected.equals("yes")) {
            logger.info("Pay via: ");
            logger.info("1. Credit Card");
            logger.info("2. Debit Card");
            logger.info("3. Cash");
            int choice = Integer.parseInt(scanner.next());
            if(choice >= 1 && choice <= 3) {
                if(studentInterface.makePayment(choice, fee)){
                    logger.info("Payment Successful");
                }else{
                    logger.info("Payment failed.");
                }
            }
            else {
                logger.info("Cannot do such payment!!");
            }
        }
        else {
            logger.info("Payment not done.");
        }
    }
    /**
     * Displays Professor menu
     */
    public void professorMenu() {

    }

    /**
     * Displays Admin Menu
     */
    public void adminMenu() {

    }
}

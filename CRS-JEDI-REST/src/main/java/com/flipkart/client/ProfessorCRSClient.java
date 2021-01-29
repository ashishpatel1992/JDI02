package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.CRSConstants;
import com.flipkart.service.CourseCatalogueInterface;
import com.flipkart.service.CourseCatalogueOperation;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class to handle Professor frontend operations
 *
 * @Author -  Team JEDI 02
 */
public class ProfessorCRSClient {

    private static Logger logger = Logger.getLogger(ProfessorCRSClient.class);

    String professorId;
    ProfessorInterface professorInterface;
    CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
    Scanner scanner;

    /**
     * Initialize Constructor with professorId
     *
     * @param professorId
     */
    public ProfessorCRSClient(String professorId) {
        this.professorId = professorId;
        professorInterface = new ProfessorOperation(professorId);
        scanner = new Scanner(System.in);
    }

    /**
     * Displays frontend menu for Professor
     */
    public void professorMenu() {
        int choice;
        boolean loggedIn = true;
        Professor professor = professorInterface.getProfessor();
        if (professor == null) {
            return;
        }
        logger.info("Welcome " + professor.getName() + ". You are logged in as " + professor.getRole() + ".");

        while (true) {
            logger.info("+-----------------------------+");
            logger.info(String.format("| %-28s|", "PROFESSOR MENU "));
            logger.info("+-----------------------------+");
            logger.info(String.format("| 1. %-24s |", "View Course"));
            logger.info(String.format("| 2. %-24s |", "Grade Students"));
            logger.info(String.format("| 3. %-24s |", "View Enrolled Students"));
            logger.info(String.format("| 4. %-24s |", "Logout"));
            logger.info("+-----------------------------+");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewCourse();
                    break;
                case 2:
                    gradeStudents();
                    break;
                case 3:
                    viewEnrolledStudents();
                    break;
                case 4:
                    logger.info("Successfully logged out");
                    loggedIn = false;
                    break;
                default:
                    logger.info("Invalid Choice");
            }

            if (!loggedIn) {
                break;
            }
        }
    }

    /**
     * View all the courses
     */
    void viewCourse() {
        Course course = professorInterface.getCourseDetail();
        if (course != null) {
            try {
                logger.info("+---------------------------+");
                logger.info(String.format("| %-26s|", "PROFESSOR COURSES "));
                logger.info("+---------------------------+");
                logger.info(String.format("| %-8s | %-14s |", "CourseId", "CourseName", "ProfessorId"));
                logger.info("+---------------------------+");
                logger.info(String.format("| %-8s | %-14s |", course.getId(), course.getName()));
                logger.info("+---------------------------+");
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        } else {
            logger.info(CRSConstants.NO_COURSE_ASSIGNED);
        }
    }

    /**
     * Grade students
     */
    void gradeStudents() {
        HashMap<String, String> studentsEnrolled = professorInterface.getEnrolledStudents();
        /**
         * String - studentId
         * String - grade
         */
        if (studentsEnrolled != null) {
            if (studentsEnrolled.size() > 0) {
                // TODO: As of now Grading all students at once
                // TODO: Display list of student ID's with name for students to be graded, once the registration process is over
                HashMap<String, String> gradeOfStudent = new HashMap<>();
                for (Map.Entry<String, String> student : studentsEnrolled.entrySet()) {
                    logger.info("Enter grade for Student ID - " + student.getKey() + "\t" + student.getValue() + ":");
                    String gradeEntered = scanner.next();
                    gradeOfStudent.put(student.getKey(), gradeEntered);
                }
                if (professorInterface.gradeStudent(gradeOfStudent)) {
                    logger.info("Grades assigned to all students of course " + professorInterface.getCourseDetail().getId());
                } else {
                    logger.info("Unable to assign grades");
                }
            } else {
                logger.info("No Students enrolled");
            }
        } else {
            logger.info(CRSConstants.NO_COURSE_ASSIGNED);
        }


    }

    /**
     * Perform view enrolled students operations
     */
    void viewEnrolledStudents() {
        HashMap<String, String> enrolledStudentsMap = professorInterface.getEnrolledStudents();
        if (enrolledStudentsMap.size() > 0) {
            logger.info("+-----------------------------+");
            logger.info(String.format("| %-27s |", "ENROLLED STUDENTS"));
            logger.info("+-----------------------------+");
            logger.info(String.format("| %-8s | %-15s |", "StudentId", "StudentName"));
            logger.info("+-----------------------------+");
            for (Map.Entry<String, String> student : enrolledStudentsMap.entrySet()) {
                logger.info(String.format("| %-9s | %-15s |", student.getKey(), student.getValue()));
            }
            logger.info("+-----------------------------+");
        } else {
            logger.info("No student registered!");
        }
    }

}
package com.flipkart.client;

import com.flipkart.dao.ProfessorDaoImp;
import org.apache.log4j.Logger;
import com.flipkart.bean.*;
import com.flipkart.service.*;

import java.util.*;

/**
 * Class to Handle admin frontend operations
 *
 * @Author -  Team JEDI 02
 */
public class AdminCRSClient {

    private static Logger logger = Logger.getLogger(AdminCRSClient.class);
    AdminInterface adminInterface;
    CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
    Scanner scanner;
    String adminId;

    /**
     * Initialize Constructor with studentId
     */
    public AdminCRSClient(String adminId) {
        adminInterface = new AdminOperation(adminId);
        scanner = new Scanner(System.in);
        this.adminId = adminId;
    }

    /**
     * Displays frontend menu for Admin
     */
    public void adminMenu() {
        int choice;
        boolean loggedIn = true;
        Admin admin = adminInterface.getAdminProfile();
        if (admin == null) {
            return;
        }
        logger.info("Welcome " + admin.getName() + ". You are logged in as " + admin.getRole() + ".");
        while (true) {

            logger.info("==== Admin Menu =====");
            logger.info("1. Add Course");
            logger.info("2. Add Professor");
            logger.info("3. Assign Professor to Course");
            logger.info("4. Approve Student");
            logger.info("5. View Courses");
            logger.info("6. Generate Report Card");
            logger.info("7. Logout");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    displayAddProfessor();
                    break;
                case 3:
                    assignProfessorToCourse();
                    break;
                case 4:
                    approveStudent();
                    break;
                case 5:
                    viewCourses();
                    break;
                case 6:
                    if (generateReportCard())
                        logger.info("Report Card Generated");
                    else
                        logger.info("Unable to generate report card");
                    break;
                case 7:
                    logger.info("Successfully logged out");
                    return;
                // TODO: Remove course  and remove Professor if course is assigned to students so it cant be removed, if professor assigned to course, professor cannot be removed.
                // TODO: Display enrolled students
                // TODO: Display all professors

                default:
                    logger.info("Invalid Choice");
            }
        }
    }

    /**
     * Displays list of unassigned professors
     *
     * @return true if there is any unassigned professor
     */
    private boolean displayUnAssignedProfessors() {
        boolean isProfessorUnAssigned = false;
        ArrayList<Professor> unAssignedProfessors = courseCatalogueInterface.getUnAssignedProfessors();

        if (unAssignedProfessors.size() > 0) {
            logger.info("List of Unassigned Professors:-");
            isProfessorUnAssigned = true;
            for (Professor professor : unAssignedProfessors) {
                logger.info(professor.getId() + " " + professor.getName());
            }
        } else {
            isProfessorUnAssigned = false;
        }
        return isProfessorUnAssigned;
    }

    /**
     * Display list of unassigned courses
     *
     * @return true if there is any unassigned course
     */
    private boolean displayUnAssignedCourses() {
        boolean isCourseUnAssigned = false;

        logger.info("List of Unassigned Courses:-");
        ArrayList<Course> unAssignedCourses = courseCatalogueInterface.getUnAssignedCourses();
        if (unAssignedCourses.size() > 0) {
            isCourseUnAssigned = true;
            for (Course course : unAssignedCourses) {
                logger.info(course.getId() + " " + course.getName());
            }
        }
        return isCourseUnAssigned;
    }

    /**
     * Assigns Professor to a course
     */
    private void assignProfessorToCourse() {
        // TODO: If any of the below is unassigned then only procced for course assignment
        if (displayUnAssignedProfessors() && displayUnAssignedCourses()) {
            logger.info("Enter course ID to assign");
            String courseId = scanner.next();
            logger.info("Enter professor ID to assign");
            String professorId = scanner.next();

            if (adminInterface.assignProfessorToCourse(professorId, courseId)) {
                logger.info(professorId + " assigned to course " + courseId);
            } else {
                logger.info("Unable to assign professor to course.");
            }
        } else {
            logger.info("No professor or course available to be assigned.");
        }

    }

    /**
     * View all the courses
     */
    public void viewCourses() {
        ArrayList<Course> courseArrayList = courseCatalogueInterface.getCourseList();
        if (courseArrayList.size() > 0) {
            logger.info(String.format("%8s %10s %12s %13s", "CourseID", "CourseName", "ProfessorID", "ProfessorName"));
            for (Course course : courseArrayList) {

                String professorId = course.getProfessorId();
                if (course.getProfessorId() == null) {
                    logger.info(String.format("%8s %10s", course.getId(), course.getName()));
                } else {
                    Professor professor = ProfessorDaoImp.getInstance().getProfessor(professorId);
                    logger.info(String.format("%8s %10s %12s %13s", course.getId(), course.getName(), professorId, professor.getName()));

                }

            }
        } else {
            logger.info("No Courses Available to display.");
        }

    }

    /**
     * Perform add course operations
     */
    public void addCourse() {
        boolean unAssignedProfessors = displayUnAssignedProfessors();
        try {
            logger.info("Enter the course ID >");
            String courseID = scanner.next();
            logger.info("Enter the course name >");
            String courseName = scanner.next();
            logger.info("Enter course fee >");
            double courseFee = scanner.nextDouble();
            String professorId = null;
            if (unAssignedProfessors) {
                logger.info("Enter the Professor Id to assign to this course ");
                professorId = scanner.next();
            }

            if (courseCatalogueInterface.addCourse(courseID, courseName, professorId, courseFee)) {
                logger.info("Course added successfully");
            } else {
                logger.info("Unable to add course");
            }
        } catch (Exception e) {
            logger.info("Invalid Input");
        }
    }

    /**
     * Perform add professor operations
     */
    public void displayAddProfessor() {
        logger.info("Enter professor id:");
        String professorId = scanner.next();
        logger.info("Enter professor name:");
        String professorName = scanner.next();
        logger.info("Enter professor email:");
        String professorEmail = scanner.next();
        logger.info("Enter professor department:");
        String professorDepartment = scanner.next();
        logger.info("Enter professor password:");
        String password = scanner.next();
        String userId = adminInterface.addProfessor(professorId, professorName, professorEmail, professorDepartment, password);
        if (userId != null) {
            logger.info("Professor: " + professorName + " added successfully with ProfessorID : " + userId);
        }
    }

    /**
     * Display list of unapproved students
     *
     * @return number of unapproved students
     */
    public int displayUnApprovedStudent() {
        ArrayList<Student> unApprovedStudents = adminInterface.getUnApprovedStudents();
        if (unApprovedStudents.size() > 0) {
            logger.info("Students pending for approval: ");
            logger.info("StudentId\tStudentName");
            for (Student unApprovedStudent : unApprovedStudents) {
                logger.info(unApprovedStudent.getId() + "\t\t" + unApprovedStudent.getName());
            }

        } else {
            logger.info("No students pending for approval");

        }
        return unApprovedStudents.size();
    }

    /**
     * Approves a student
     */
    public void approveStudent() {
        if (displayUnApprovedStudent() > 0) {
            logger.info("Enter Student Id to approve:");
            String studentId = scanner.next();
            // TODO: Exception Handling to return StudentAlready approved, Student Id invalid
            if (adminInterface.approveStudent(studentId)) {
                logger.info("Student ID : " + studentId + " Approved Successfully");
            } else {
                logger.info("Invalid student id provided or Student Id already approved");
            }
        }

    }

    /**
     * Generates the report card
     */
    public boolean generateReportCard() {
        // TODO: Check if student is enrolled before generating reportcard
        logger.info("Enter student id to generate report card:");
        String studentId = scanner.next();
        HashMap<String, String> studentGrades = adminInterface.generateReportCard(studentId);
        if (studentGrades != null) {
            logger.info("Course ID\tGrade");
            for (Map.Entry<String, String> courseGradeMap : studentGrades.entrySet()) {
                logger.info(courseGradeMap.getKey() + "\t" + courseGradeMap.getValue());
            }
            return true;
        } else {
            return false;
        }
    }

}
package com.flipkart.client;

import com.flipkart.dao.ProfessorDaoImp;
import org.apache.log4j.Logger;
import com.flipkart.bean.*;
import com.flipkart.service.*;

import java.util.*;

/**
 * Handles admin frontend
 */
public class AdminCRSClient {

    private static Logger logger = Logger.getLogger(AdminCRSClient.class);
    AdminInterface adminInterface = new AdminOperation();
    CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
    Scanner scanner;
    String adminId;

    /**
     * Initialize Constructor with studentId
     */
    public AdminCRSClient(String aid) {
        scanner = new Scanner(System.in);
        adminId = aid;
    }

    /**
     * Displays frontend menu for Admin
     */
    public void adminMenu() {
        int choice;
        boolean loggedIn = true;
        while (true) {

            logger.info("==== Admin Menu =====");
            logger.info("1. Add Course");
            logger.info("2. Add Professor");
            logger.info("3. Assign Professor to Course");
            logger.info("4. Approve Student");
            logger.info("5. View Courses");
            logger.info("6. Remove Course");
            logger.info("7. Remove Professor");
            logger.info("8. Generate Report Card");
            logger.info("9. Logout");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (addCourse())
                        logger.info("Course added successfully");
                    else
                        logger.info("Unable to add course");
                    break;
                case 2:
                    addProfessor();
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
                    if(removeCourse()){
                        logger.info("Course successfully removed");
                    }else{
                        logger.info("Cannot remove course.");
                    }
                    break;
                case 7:
                    logger.info("remove professor");
                    break;
                case 8:
                    if (generateReportCard())
                        logger.info("Report Card Generated");
                    else
                        logger.info("Unable to generate report card");
                    break;
                case 9:
                    logger.info("Successfully logged out");
                    return;
                    // TODO: Remove course  and remove Professor if course is assigned to students so it cant be removed, if professor assigned to course, professor cannot be removed.

                default:
                    logger.info("Invalid Choice");
            }
        }
    }

    private boolean displayUnAssignedProfessors() {
        boolean isProfessorUnAssigned = false;
        ArrayList<Professor> unAssignedProfessors = courseCatalogueInterface.getUnAssignedProfessors();
        logger.info("List of Unassigned Professors:-");
        if (unAssignedProfessors.size() > 0) {
            isProfessorUnAssigned = true;
            for (Professor professor : unAssignedProfessors) {
                logger.info(professor.getId() + " " + professor.getName());
            }
        }
        return isProfessorUnAssigned;
    }

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

    private void assignProfessorToCourse() {
        // TODO: If any of the below is unassigned then only procced for course assignment
        displayUnAssignedProfessors();
        displayUnAssignedCourses();

        logger.info("Enter course ID to assign");
        String courseId = scanner.next();
        logger.info("Enter professor ID to assign");
        String professorId = scanner.next();

        if (adminInterface.assignProfessorToCourse(professorId, courseId)) {
            logger.info(professorId + " assigned to course " + courseId);
        } else {
            logger.info("Unable to assign professor to course.");
        }

        logger.info("");
    }

    public void viewCourses() {
        ArrayList<Course> courseArrayList = courseCatalogueInterface.getCourseList();
        logger.info("CourseID\tCourseName\tProfessorID\tProfessorName");
        for (Course course : courseArrayList) {
            // TODO: Fetch Professor Name and print when Professor is implemented
            if (course.getProfessorId() == null) {
                logger.info(course.getId() + " " + course.getName() + " N/A");
            } else {
                logger.info(course.getId() + " " + course.getName() + " " + course.getProfessorId() + " " + ProfessorDaoImp.getInstance().getProfessor(course.getProfessorId()).getName());
            }

        }
    }

    /**
     * Perform add course operations
     */
    public boolean addCourse() {
        logger.info("Enter the course ID you want to add");
        String courseID = scanner.next();
        logger.info("Enter the course name you want to add");
        String courseName = scanner.next();
        logger.info("Enter the Professor Id for course " + courseName);
        String professorId = scanner.next();
        return courseCatalogueInterface.addCourse(courseID, courseName, professorId);

    }

    /**
     * Perform add professor operations
     */
    public void addProfessor() {
        logger.info("Enter professor id:");
        String professorId = scanner.nextLine();
        logger.info("Enter professor name:");
        String professorName = scanner.next();
        logger.info("Enter professor email:");
        String professorEmail = scanner.next();
        logger.info("Enter professor department:");
        String professorDepartment = scanner.next();
        String password = adminInterface.addProfessor(professorId, professorName, professorEmail, professorDepartment);
        logger.info(password);
        if (password != null) {
            logger.info("Professor: " + professorName + " Successfully Added\nProfessor ID : " + professorId + "\nPassword : " + password);
        }
    }

    public int displayUnApprovedStudent() {
        ArrayList<Student> unApprovedStudents = adminInterface.getUnApprovedStudents();
        if (unApprovedStudents.size() > 0) {
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
     * To approve the student
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
     * To generate the report card
     */
    public boolean generateReportCard() {
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
    public boolean removeCourse(){

        return false;
    }

}
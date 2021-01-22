package com.flipkart.client;

import org.apache.log4j.Logger;
import com.flipkart.bean.*;
import com.flipkart.service.*;
import java.util.*;

/**
 * Handles admin frontend
 */
public class AdminCRSClient {

    private static Logger logger = Logger.getLogger(AdminCRSClient.class);
    AdminInterface adminInterface=new AdminOperation();
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
            logger.info("3. Approve Students");
            logger.info("4. View Courses");
            logger.info("5. Generate Report Card");
            logger.info("6. Logout");
            Scanner scanner=new Scanner(System.in);
            choice = scanner.nextInt();

            switch(choice){
                case 1:
                    if(addCourse())
                        logger.info("Course added successfully");
                    else
                        logger.info("Unable to add course");
                    break;
                case 2:
                    addProfessor();
                case 3:
                    if(approveStudent())
                        logger.info("Student Approved");
                    else
                        logger.info("Unable to approve the student");
                    break;
                case 4 :
                    viewCourses();
                    break;
                case 5 :
                    if(generateReportCard())
                        logger.info("Report Card Generated");
                    else
                        logger.info("Unable to generate report card");
                    break;
                case 6:
                    logger.info("Successfully logged out");
                    return;
                default:
                    logger.info("Invalid Choice");
            }
        }
    }

    public void viewCourses() {
        ArrayList<Course> courseArrayList = courseCatalogueInterface.getCourseList();
        for (Course course : courseArrayList) {
            // TODO: Fetch Professor Name and print when Professor is implemented
            logger.info(course.getId() + " " + course.getName() + " " + course.getProfessorId());
        }
    }
    /**
     * Perform add course operations
     */
    public boolean addCourse(){
        logger.info("Enter the course ID you want to add");
        String courseID= scanner.next();
        logger.info("Enter the course name you want to add");
        String courseName= scanner.next();
        logger.info("Enter the Professor Id for course "+courseName);
        String professorId = scanner.next();
        return courseCatalogueInterface.addCourse(courseID, courseName,professorId);

    }
    /**
     * Perform add professor operations
     */
    public void addProfessor() {
        logger.info("Enter professor id:");
        String professorId =scanner.nextLine();
        logger.info("Enter professor name:");
        String professorName = scanner.next();
        logger.info("Enter professor email:");
        String professorEmail = scanner.next();
        logger.info("Enter professor department:");
        String professorDepartment = scanner.next();
        String password = adminInterface.addProfessor(professorId,professorName,professorEmail,professorDepartment);
        if(password!=null){
            logger.info("Professor: "+professorName+" Successfully Added\nProfessor ID : "+ professorId+"\nPassword : "+password);
        }
    }
    /**
     * To approve the student
     */
    public boolean approveStudent(){
        logger.info("Enter Student Id to approve:");
        String studentId = scanner.next();
        return adminInterface.approveStudent(studentId);
    }
    /**
     * To generate the report card
     */
    public boolean generateReportCard() {
        logger.info("Enter student id to generate report card:");
        String studentId = scanner.next();
        HashMap<String,String> studentGrades =  adminInterface.generateReportCard(studentId);
        if(studentGrades!=null){
            logger.info("Course ID\tGrade");
            for(Map.Entry<String,String> courseGradeMap:studentGrades.entrySet()){
                logger.info(courseGradeMap.getKey()+"\t"+courseGradeMap.getValue());
            }
            return true;
        }else{
            return false;
        }
    }


}
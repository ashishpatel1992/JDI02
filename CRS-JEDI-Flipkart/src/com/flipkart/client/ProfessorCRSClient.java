package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
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
 */
public class ProfessorCRSClient {

    private static Logger logger = Logger.getLogger(ProfessorCRSClient.class);

    String professorId;
    ProfessorInterface professorInterface;
    CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
    Scanner scanner;

    /**
     * Initialize Constructor with professorId
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
        if(professor ==  null){
            return;
        }
        logger.info("Welcome "+ professor.getName()+". You are logged in as "+professor.getRole()+".");

        while (true) {
            logger.info("==== Professor Menu =====");
            logger.info("1. View Course");
            logger.info("2. Grade Students");
            logger.info("3. View Enrolled Students");
            logger.info("4. Logout");

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

            if(!loggedIn){
                break;
            }
        }
    }

    /**
     * View all the courses
     */
    void viewCourse(){
        Course course = professorInterface.getCourseDetail();
        logger.info(course.getStudentsEnrolled());
        if(course!=null) {
            try {
                logger.info("CourseId\tCourseName");
                logger.info(course.getId() + "\t\t" + course.getName());
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }else{
            logger.info("No course assigned!");
        }
    }

    /**
     * Grade students
     */
    void gradeStudents(){
        HashMap<String,String> studentsEnrolled = professorInterface.getEnrolledStudents();
        /**
         * String - studentId
         * String - grade
         */
        logger.info(studentsEnrolled.size());
        if(studentsEnrolled.size() > 0){
            // TODO: As of now Grading all students at once
            HashMap<String,String> gradeOfStudent = new HashMap<>();
            for (Map.Entry<String,String> student:studentsEnrolled.entrySet()){
                logger.info("Enter grade for Student ID - "+student.getKey()+"\t"+student.getValue()+":");
                String gradeEntered=scanner.next();
                gradeOfStudent.put(student.getKey(),gradeEntered);
            }
            if(professorInterface.gradeStudent(gradeOfStudent)){
                logger.info("Grades assigned to all students of course "+ professorInterface.getCourseDetail().getId());
            }else{
                logger.info("Unable to assign grades");
            }
        }else{
            logger.info("No Students enrolled");
        }


    }

    /**
     * Perform view enrolled students operations
     */
    void viewEnrolledStudents(){
        HashMap<String,String> enrolledStudentsMap= professorInterface.getEnrolledStudents();
        if(enrolledStudentsMap.size()>0){
            logger.info("Student ID\tName");
            for(Map.Entry<String,String> student:enrolledStudentsMap.entrySet()){
                logger.info(student.getKey()+"\t\t"+student.getValue());
            }
        }else{
            logger.info("No student registered!");
        }
    }

}
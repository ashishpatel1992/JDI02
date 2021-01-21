package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.service.CourseCatalogueInterface;
import com.flipkart.service.CourseCatalogueOperation;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProfessorCRSClient {
    /**
     * Takes professor id to initialize, handles frontend for Professor operations
     *
     * @param professorId
     */
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
        while (true) {
            logger.info("==== Professor Menu =====");
            logger.info("1. View Course");
            logger.info("2. Grade Students");
            logger.info("3. View Enrolled Students");
            logger.info("4. Logout");

            choice = scanner.nextInt();

            switch(choice){
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
     * Perform view courses operations
     */
    void viewCourse(){
        Course course = professorInterface.getCourseDetail();
        if(course!=null) {
            logger.info("CourseId\tCourseName\tStudents Enrolled");
            logger.info(course.getId() + "\t" + course.getName() + "\t" + course.getStudentsEnrolled().size());
        }else{
            logger.info("No course assigned!");
        }
    }

    /**
     * Perform grade student operations
     */
    void gradeStudents(){

        logger.info("GradeStudentClientBefore");
        ArrayList<Student> studentsEnrolled= professorInterface.getEnrolledStudents();
        /**
         * String - studentId
         * String - grade
         */
        logger.info(studentsEnrolled.size());
        if(studentsEnrolled.size() > 0){
            // TODO: As of now Grading all students at once
            HashMap<String,String> gradeOfStudent = new HashMap<>();
            for (Student student:studentsEnrolled){
                logger.info("Enter grade for Student ID - "+student.getId()+":");
                gradeOfStudent.put(student.getId(),scanner.next());
            }
            logger.info("GradeStudentClientAfter");
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
        ArrayList<Student> enrolledStudents= professorInterface.getEnrolledStudents();
        if(enrolledStudents.size()>0){
            logger.info("Student ID\tName\tBranch");
            for(Student student:enrolledStudents){
                logger.info(student.getId()+"\t"+student.getName()+"\t"+student.getBranch());
            }
        }else{
            logger.info("No student registered!");
        }
    }

}
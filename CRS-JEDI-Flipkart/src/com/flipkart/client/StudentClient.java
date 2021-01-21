package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.service.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentClient {
    /**
     * Takes student Id to initialize, handles frontend for Student
     * @param sid
     */
    private static Logger logger = Logger.getLogger(StudentClient.class);
    ArrayList<String> courseIdSelectionList;
    CourseCatalogueInterface courseCatalogueOperation = new CourseCatalogueOperation();
    // TODO: if made static will it be shared with everyone? i guess yes! so avoiding it find better way for accessing studentId for session
    String studentId;

    /**
     * Initialize Constructor with studentId
     * @param sid
     */
    public StudentClient(String sid) {

        studentId = sid;
        courseIdSelectionList = new ArrayList<String>();
    }

    /**
     * Displays list of available courses in log
     */
    public void viewAvailableCourses() {
        ArrayList<Course> courseArrayList = courseCatalogueOperation.getCourseList();
        for (Course course : courseArrayList) {
            // TODO: Fetch Professor Name and print when Professor is implemented
            logger.info(course.getId() + " " + course.getName() + " " + course.getProfessorId());
        }
    }

    /**
     * Displays list of courses selected for registration
     * Similar to cart functionality
     */
    public void getCourseSelection() {
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
     * Add course to selection cart
     * @param courseId
     */
    public void addCourseToSelection(String courseId) {
        // TODO: Avoid same course to be added again
        Course c = courseCatalogueOperation.getCourse(courseId);
//        Check if it is a valid course
        if (c != null) {
            courseIdSelectionList.add(courseId);
        } else {
            logger.info("Invalid CourseId entered");
            logger.info("Please choose CourseId from following list:-");
            viewAvailableCourses();
        }

    }

    /**
     * Remove a course from Selection cart
     * @param courseId
     */
    public void removeCourseFromSelection(String courseId) {
        if (courseIdSelectionList.contains(courseId)) {
            courseIdSelectionList.remove(courseId);
            logger.info("Course " + courseId + " has been removed.");
        } else {
            logger.info("Course not present in the Selection list");
        }
    }

    /**
     * Displays frontend menu for student
     */
    public void studentMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        RegisteredCoursesOperation registeredCoursesOperation;
        registeredCoursesOperation = new RegisteredCoursesOperation(studentId);

        while (true) {
            logger.info("==== Student MENU =====");
            logger.info("1. View Available Courses");
            logger.info("2. Add Course to selection");
            logger.info("3. Remove Course from selection");
            logger.info("4. Register Course");
            logger.info("5. View Registered Course");
            logger.info("6. Drop Course");
            logger.info("7. Get Grades");
            logger.info("8. Logout");


            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableCourses();

                    break;
                case 2:

                    String courseId;

                    logger.info("Enter course Id");
                    courseId = scanner.next();
                    addCourseToSelection(courseId);

                    getCourseSelection();

                    break;
                case 3:
                    logger.info("Enter course Id");
                    courseId = scanner.next();
                    removeCourseFromSelection(courseId);
                    getCourseSelection();

                    break;
                case 4:

                    getCourseSelection();
                    if (courseIdSelectionList.size() > 0) {
                        // check if already registered otherwise register
                        if (registeredCoursesOperation.registerCourses(courseIdSelectionList)) {
                            ArrayList<Course> courseArrayList = registeredCoursesOperation.getRegisteredCourses();
                            logger.info("You are successfully registered for following courses.");
                            logger.info("CourseId\tCourseName");
                            for (Course regCourse : courseArrayList) {
                                logger.info(regCourse.getId() + "\t\t" + regCourse.getName() + "\t\t" + regCourse.getProfessorId());
                            }
                        } else {
                            logger.info("Already Registered for Course");
                        }

                    } else {
                        logger.info("No course selected for registration.");
                    }

                    break;
                case 5:
                    logger.info("You are registered for following courses:- ");
                    ArrayList<Course> registeredCourseList = registeredCoursesOperation.getRegisteredCourses();
                    if (registeredCourseList.size() > 0) {
                        logger.info("CourseId\tCourseName");
                        for (Course regCourse : registeredCourseList) {
                            logger.info(regCourse.getId() + "\t\t" + regCourse.getName() + "\t\t" + regCourse.getProfessorId());
                        }
                    } else {
                        logger.info("Either you dont have any course or You havent completed the registration yet.");
                    }

                    break;
                case 6:
                    logger.info("Enter course Id to drop");
                    courseId = scanner.next();
                    if (registeredCoursesOperation.dropCourse(courseId)) {
                        logger.info("Course " + courseId + " successfully dropped");
                    } else {
                        logger.info("Unable to drop the course");
                    }
                    break;
                case 7:
                    ReportCardOperation reportCardOperation = new ReportCardOperation(studentId);
                    reportCardOperation.getGrades();
                    return;

                default:
                    logger.info("Invalid Choice");
            }
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

package com.flipkart.restcontroller;

import com.flipkart.bean.Course;
import com.flipkart.service.CourseCatalogueInterface;
import com.flipkart.service.CourseCatalogueOperation;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Class to handle Professor frontend operations
 *
 * @Author -  Team JEDI 02
 */
@Path("/professor")
public class ProfessorRESTAPI {
//    private static Logger logger = Logger.getLogger(ProfessorRESTAPI.class);

    public ProfessorRESTAPI(){

    }
    public ProfessorRESTAPI(String professorId) {
//        professorInterface = new ProfessorOperation(professorId);
    }
    /**
     * Displays frontend menu for Professor
     */
    public void professorMenu() {
//        int choice;
//        boolean loggedIn = true;

//        logger.info("Welcome " + professor.getName() + ". You are logged in as " + professor.getRole() + ".");

//            logger.info("+-----------------------------+");
//            logger.info(String.format("| %-28s|", "PROFESSOR MENU "));
//            logger.info("+-----------------------------+");
//            logger.info(String.format("| 1. %-24s |", "View Course"));
//            logger.info(String.format("| 2. %-24s |", "Grade Students"));
//            logger.info(String.format("| 3. %-24s |", "View Enrolled Students"));
//            logger.info(String.format("| 4. %-24s |", "Logout"));
//            logger.info("+-----------------------------+");
    }

    /**
     * View all the courses
     */
    @GET
    @Path("/getstatus")
    @Produces("application/json")
    public String getStatus(){
        return "rgeg";
    }

    @GET
    @Path("/get-course/{professorId}")
    @Produces("application/json")
    public Course viewCourseByProfId(@PathParam("professorId") String professorId) {

        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        Course course = professorInterface.getCourseDetail();

        return course;
    }


    @GET
    @Path("/get-students/{professorId}")
    @Produces("application/json")
    public String getEnrolledStudents(@PathParam("professorId") String professorId){
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        HashMap<String, String> studentsEnrolled = professorInterface.getEnrolledStudents();
        return studentsEnrolled.get("20170035");
    }

    /**
     * Grade students
     */
    @PUT
    @Path("/update-grades/{professorId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response gradeStudents(@PathParam("professorId") String professorId, HashMap<String,String> gradeOfStudent) {
//        HashMap<String, String> studentsEnrolled = professorInterface.getEnrolledStudents();
//        /**
//         * String - studentId
//         * String - grade
//         */
//        if (studentsEnrolled != null) {
//            if (studentsEnrolled.size() > 0) {
//                HashMap<String, String> gradeOfStudent = new HashMap<>();
//                for (Map.Entry<String, String> student : studentsEnrolled.entrySet()) {
////                    logger.info("Enter grade for Student ID - " + student.getKey() + "\t" + student.getValue() + ":");
//                    String gradeEntered = scanner.next();
//                    gradeOfStudent.put(student.getKey(), gradeEntered);
//                }
//                if (professorInterface.gradeStudent(gradeOfStudent)) {
////                    logger.info("Grades assigned to all students of course " + professorInterface.getCourseDetail().getId());
//                } else {
////                    logger.info("Unable to assign grades");
//                }
//            } else {
////                logger.info("No Students enrolled");
//            }
//        } else {
////            logger.info(CRSConstants.NO_COURSE_ASSIGNED);
//        }
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        if(professorInterface.gradeStudent(gradeOfStudent)){
            return Response.status(200).entity("Grades successfully entered").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Entering grades failed.").build();

    }


}
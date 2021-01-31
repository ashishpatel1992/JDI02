package com.flipkart.restcontroller;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.restcontroller.beans.ResponseMessageRest;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;
import org.apache.log4j.Logger;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Class to control Professor REST
 *
 * @Author -  Team JEDI 02
 */
@Path("/professor")
public class ProfessorRESTAPI {
    private static final Logger logger = Logger.getLogger(ProfessorRESTAPI.class);

    /**
     * View all the courses
     */
    @GET
    @Path("/getstatus")
    @Produces("application/json")
    public String getStatus() {
        return "success";
    }

    @GET
    @Path("/{professorid}")
    @Produces("application/json")
    public Response getProfessor(@PathParam("professorid") String professorId) {
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        Professor professor = professorInterface.getProfessor();
        if (professor == null) {
            return Response.status(404).entity(new ResponseMessageRest("No professor found")).build();
        } else {
            return Response.status(200).entity(professor).build();
        }
    }

    @GET
    @Path("{professorid}/get-course")
    @Produces("application/json")
    public Response viewCourseByProfId(@NotNull @PathParam("professorid") String professorId) throws ValidationException {
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        Course course = professorInterface.getCourseDetail();
        if (course == null) {
            logger.info("Course logger from Professor");
            // TODO: Have exception to handle invalid professor ID or course not assigned
            return Response.status(404).entity(new ResponseMessageRest("Invalid Professor id")).build();
        } else {
            return Response.status(200).entity(professorInterface.getCourseDetail()).build();
        }

    }


    @GET
    @Path("{professorid}/get-students")
    @Produces("application/json")
    public Response getEnrolledStudents(@NotNull @PathParam("professorid") String professorId) throws ValidationException {
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        HashMap<String, String> enrolledStudentsMap = professorInterface.getEnrolledStudents();
        if (enrolledStudentsMap == null) {
            return Response.status(404).entity(new ResponseMessageRest("Unable to get details of enrolled students")).build();
        } else {
            return Response.status(200).entity(enrolledStudentsMap).build();
        }
    }

    /**
     * Grade students
     * key = studentId,value = grade
     */
    @PUT
    @Path("{professorid}/update-grades")
    @Consumes("application/json")
    @Produces("application/json")
    public Response gradeStudents(@NotNull @PathParam("professorid") String professorId, @NotNull HashMap<String, String> gradeOfStudent) throws ValidationException {
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        if (professorInterface.gradeStudent(gradeOfStudent)) {
            return Response.status(200).entity(new ResponseMessageRest("Grade updated Succesfully.")).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseMessageRest("Unable to update Grades")).build();

    }


}
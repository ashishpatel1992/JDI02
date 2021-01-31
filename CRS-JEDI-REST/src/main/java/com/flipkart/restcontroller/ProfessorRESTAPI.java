package com.flipkart.restcontroller;

import com.flipkart.bean.Course;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;

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

    /**
     * View all the courses
     */
    @GET
    @Path("/getstatus")
    @Produces("application/json")
    public String getStatus(){
        return "success";
    }

    @GET
    @Path("/get-course")
    @Produces("application/json")
    public Course viewCourseByProfId(@NotNull @QueryParam("professorId") String professorId) throws ValidationException {
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        return professorInterface.getCourseDetail();
    }


    @GET
    @Path("/get-students")
    @Produces("application/json")
    public HashMap<String, String> getEnrolledStudents(@NotNull @QueryParam("professorId") String professorId) throws ValidationException{
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        return professorInterface.getEnrolledStudents();
    }

    /**
     * Grade students
     */
    @PUT
    @Path("/update-grades")
    @Consumes("application/json")
    @Produces("application/json")
    public Response gradeStudents(@NotNull @QueryParam("professorId") String professorId, @NotNull HashMap<String,String> gradeOfStudent) throws ValidationException{
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        if(professorInterface.gradeStudent(gradeOfStudent)){
            return Response.status(200).entity("{\"msg\":\"Students graded successfully\"}").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("{\"msg\":\"Unable to update grades\"}").build();

    }


}
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

    public ProfessorRESTAPI(){
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
    public HashMap<String, String> getEnrolledStudents(@PathParam("professorId") String professorId){
        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        HashMap<String, String> studentsEnrolled = professorInterface.getEnrolledStudents();
        return studentsEnrolled;
    }

    /**
     * Grade students
     */
    @PUT
    @Path("/update-grades/{professorId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response gradeStudents(@PathParam("professorId") String professorId, HashMap<String,String> gradeOfStudent) {

        ProfessorInterface professorInterface = new ProfessorOperation(professorId);
        if(professorInterface.gradeStudent(gradeOfStudent)){
            return Response.status(200).entity("{\"msg\":\"Students graded successfully\"}").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("{\"msg\":\"Unable to update grades\"}").build();

    }


}
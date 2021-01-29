package com.flipkart.restcontroller;

import com.flipkart.bean.Student;
import com.flipkart.restcontroller.beans.StudentRest;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.StudentRegistrationInterface;
import com.flipkart.service.StudentRegistrationOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/student")
public class StudentRESTAPI {
    @GET
    @Path("/by-id/{studentid}")
    @Produces("application/json")
    public Student getStudentById(@PathParam("studentid") String studentId) {
        StudentInterface studentInterface = new StudentOperation(studentId);
        return studentInterface.getStudentProfile();
    }

    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerStudent(StudentRest studentRest) {

        StudentRegistrationInterface studentRegistrationInterface = new StudentRegistrationOperation();
        Student newStudent = new Student(studentRest.getId(), studentRest.getName(), studentRest.getEmail(), studentRest.getRole(), studentRest.getBranch(), false);
        String pass = studentRegistrationInterface.isRegistrationDataValid(newStudent, studentRest.password);
        return Response.status(201).entity(newStudent.getId()).build();
    }


}

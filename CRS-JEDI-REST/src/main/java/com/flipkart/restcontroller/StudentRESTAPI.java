package com.flipkart.restcontroller;

import com.flipkart.bean.Student;
import com.flipkart.client.StudentCRSClient;
import com.flipkart.restcontroller.beans.StudentRest;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.StudentRegistrationInterface;
import com.flipkart.service.StudentRegistrationOperation;
import org.apache.log4j.Logger;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@Path("/student")
public class StudentRESTAPI {
    private static Logger logger = Logger.getLogger(StudentRESTAPI.class);

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

    @POST
    @Path("/{studentid}/add-course")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourseToSelection(@PathParam("studentid") String studentId, String courseId) {
        logger.info("AddCourse");
    // TODO : Fix to get courseId instead of full json
//        StudentInterface studentInterface = new StudentOperation(studentId);
//        if (studentInterface.getCourseSelection().contains(courseId)) {
//            return Response.status(201).entity("Course already Exist").build();
//        } else {
//            if (studentInterface.addCourseToSelection(courseId)) {
//                return Response.status(201).entity("Course added successfully").build();
//            } else {
//                return Response.status(201).entity("Failed to add course").build();
//            }
//        }
        return Response.status(201).entity(studentId+" "+courseId).build();
    }

    @GET
    @Path("/{studentid}/get-course-selection")
    @Produces("application/json")
    public Response addCourseToSelection(@PathParam("studentid") String studentId) {
        logger.info("AddCourse");

        StudentInterface studentInterface = new StudentOperation(studentId);
        ArrayList<String> courseSelection = studentInterface.getCourseSelection();
        return Response.status(200).entity(courseSelection).build();
    }


}

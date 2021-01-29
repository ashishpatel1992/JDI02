package com.flipkart.restcontroller;

import com.flipkart.bean.*;
import com.flipkart.restcontroller.beans.ProfessorCourseRest;
import com.flipkart.restcontroller.beans.ProfessorRest;
import com.flipkart.restcontroller.beans.StudentIdRest;
import com.flipkart.service.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Class to control Admin REST
 *
 * @Author -  Team JEDI 02
 */
@Path("/admin")
public class AdminRESTAPI {

    /**
     * Displays list of unassigned professors
     *
     * @return true if there is any unassigned professor
     */
    @GET
    @Path("/get-unassigned-professors")
    @Produces("application/json")
    public ArrayList<Professor> displayUnAssignedProfessors() {
        CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
        return courseCatalogueInterface.getUnAssignedProfessors();

    }

    /**
     * Display list of unassigned courses
     *
     * @return true if there is any unassigned course
     */
    @GET
    @Path("/get-unassigned-courses")
    @Produces("application/json")
    public ArrayList<Course> displayUnAssignedCourses() {
        CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
        return courseCatalogueInterface.getUnAssignedCourses();
    }

    /**
     * Assigns Professor to a course
     */
    @PUT
    @Path("assign-professor")
    @Consumes("application/json")
    public Response assignProfessorToCourse(@Valid ProfessorCourseRest professorCourse) {
        AdminInterface adminInterface = new AdminOperation();
        if (adminInterface.assignProfessorToCourse(professorCourse.getProfessorId(), professorCourse.getCourseId())) {
            return Response.status(200).entity(professorCourse).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/getstatus")
    @Produces("application/json")
    public String getStatus(){
        return "success";
    }

    /**
     * View all the courses
     */
    @GET
    @Path("/get-courses")
    @Produces("application/json")
    public ArrayList<Course> viewCourses() {
        CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
        return courseCatalogueInterface.getCourseList();
    }

    /**
     * Perform add course operations
     */
    @POST
    @Path("add-course")
    @Consumes("application/json")
    public Response addCourse(@Valid Course course) throws ValidationException {
        //displayUnAssignedProfessors();
        CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
        if (courseCatalogueInterface.addCourse(course.getId(), course.getName(), course.getProfessorId(), course.getFee())) {
            return Response.status(201).entity(course).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * Perform add professor operations
     */
    @POST
    @Path("add-professor")
    @Consumes("application/json")
    public Response displayAddProfessor(@Valid ProfessorRest professorRest) {
        Professor professor = professorRest.getProfessor();
        AdminInterface adminInterface = new AdminOperation();
        String userId = adminInterface.addProfessor(professor.getId(), professor.getName(), professor.getEmail(), professor.getDepartment(), professorRest.getPassword());
        if (userId != null) {
            return Response.status(201).entity(professor).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * Display list of unapproved students
     *
     * @return number of unapproved students
     */
    @GET
    @Path("/get-unapproved-students")
    @Produces("application/json")
    public ArrayList<Student> displayUnApprovedStudent() {
        AdminInterface adminInterface = new AdminOperation();
        return adminInterface.getUnApprovedStudents();
    }

    /**
     * Approves a student
     */
    @PUT
    @Path("approve-student")
    @Consumes("application/json")
    public Response approveStudent(@Valid StudentIdRest studentIdRest) {
            AdminInterface adminInterface = new AdminOperation();
            // TODO: Exception Handling to return StudentAlready approved, Student Id invalid
            if (adminInterface.approveStudent(studentIdRest.getStudentId())) {
                return Response.status(200).entity("Student ID - "+studentIdRest.getStudentId()+" successfully approved").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
    }

    /**
     * Generates the report card
     */
    @GET
    @Path("/get-report-card/{studentId}")
    @Produces("application/json")
    public ArrayList<CourseGradeCard> generateReportCard(@PathParam("studentId") String studentId) {
        // TODO: Check if student is enrolled before generating reportcard
        ReportCardOperation reportCardOperation = new ReportCardOperation(studentId);
        return reportCardOperation.getGrades();
        // TODO: Fix with if else condition whether registration over or not
    }

}
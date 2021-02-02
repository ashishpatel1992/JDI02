package com.flipkart.restcontroller;

import com.flipkart.bean.*;
import com.flipkart.restcontroller.beans.AssignProfessorRest;
import com.flipkart.restcontroller.beans.ProfessorRest;
import com.flipkart.restcontroller.beans.ResponseMessageRest;
import com.flipkart.service.*;
import org.apache.log4j.Logger;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private static final Logger logger = Logger.getLogger(AdminRESTAPI.class);

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
    @Path("/assign-professor")
    @Produces("application/json")
    @Consumes("application/json")
    public Response assignProfessorToCourse(AssignProfessorRest assignProfessorRest) throws ValidationException {
        AdminInterface adminInterface = new AdminOperation();
        if (adminInterface.assignProfessorToCourse(assignProfessorRest.getProfessorId(), assignProfessorRest.getCourseId())) {
            return Response.status(200).entity(new ResponseMessageRest("Professor - " + assignProfessorRest.getProfessorId() + " assigned to course - " + assignProfessorRest.getCourseId())).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseMessageRest("Unable to assign professor to the course")).build();
        }
    }

    @GET
    @Path("/getstatus")
    @Produces("application/json")
    public String getStatus() {
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
    @Path("/add-course")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addCourse(@Valid Course course) throws ValidationException {
        CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();
        if (courseCatalogueInterface.addCourse(course.getId(), course.getName(), course.getProfessorId(), course.getFee())) {
            return Response.status(201).entity(course).build();
        } else {
            return Response.status(200).entity(new ResponseMessageRest("Course Already Exist")).build();
        }
    }

    /**
     * Perform add professor operations
     */
    @POST
    @Path("/add-professor")
    @Consumes("application/json")
    @Produces("application/json")
    public Response displayAddProfessor(@Valid ProfessorRest professorRest) throws ValidationException {
        Professor professor = professorRest.getProfessor();
        AdminInterface adminInterface = new AdminOperation();
        String userId = adminInterface.addProfessor(professor.getId(), professor.getName(), professor.getEmail(), professor.getDepartment(), professorRest.getPassword());
        if (userId != null) {
            return Response.status(201).entity(professor).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseMessageRest("Unable to add professor")).build();
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
    @Path("/approve-student/{studentid}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response approveStudent(@Size(min = 2, max = 15, message = "The length of Id should be between 1 to 15") @PathParam("studentid") String studentId) throws ValidationException {
        AdminInterface adminInterface = new AdminOperation();
        logger.info("Approve Student");
        if (adminInterface.approveStudent(studentId)) {
            return Response.status(200).entity(new ResponseMessageRest("Student ID - " + studentId + " successfully approved")).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseMessageRest("Student ID - " + studentId + " already approved or not registered!")).build();
        }
    }

    /**
     * Generates the report card
     */
    @GET
    @Path("/get-report-card/{studentid}")
    @Produces("application/json")
    @Consumes("application/json")
    public ArrayList<CourseGradeCard> generateReportCard(@PathParam("studentid") String studentId) throws ValidationException {
        ReportCardOperation reportCardOperation = new ReportCardOperation(studentId);
        return reportCardOperation.getGrades();
    }

}
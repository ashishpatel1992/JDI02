package com.flipkart.restcontroller;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseGradeCard;
import com.flipkart.bean.Student;
import com.flipkart.dao.NotificationDaoImp;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.restcontroller.beans.StudentCourseIdRest;
import com.flipkart.restcontroller.beans.StudentFeeRest;
import com.flipkart.restcontroller.beans.StudentRest;
import com.flipkart.service.*;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/student")
public class StudentRESTAPI {
    private static final Logger logger = Logger.getLogger(StudentRESTAPI.class);

    @GET
    @Path("/by-id/{studentid}")
    @Produces("application/json")
    public Student getStudentById(@PathParam("studentid") String studentId) {
        System.out.println("from sout");
        logger.info("from logger");
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
    @Path("/{studentid}/register-courses")
    @Produces("application/json")
    public Response registerCourses(@PathParam("studentid") String studentId, StudentCourseIdRest studentCourseIds) {
        logger.info("Register course");
        RegisteredCoursesInterface registeredCoursesInterface = new RegisteredCoursesOperation(studentId);
        CourseCatalogueInterface courseCatalogueInterface = new CourseCatalogueOperation();

        ArrayList<String> registeredCourseIds = registeredCoursesInterface.registerCourses(studentCourseIds.getStudentCourseIdList());
        ArrayList<Course> courseArraylist = new ArrayList<>();

        for (String courseId : registeredCourseIds) {
            courseArraylist.add(courseCatalogueInterface.getCourse(courseId));
        }
        return Response.status(200).entity(courseArraylist).build();
    }

    @GET
    @Path("/{studentid}/courses")
    @Produces("application/json")
    public Response getRegisteredCourses(@PathParam("studentid") String studentId) {
        RegisteredCoursesInterface registeredCoursesInterface = new RegisteredCoursesOperation(studentId);
        ArrayList<Course> registeredCoursesList = registeredCoursesInterface.getRegisteredCourses();
        return Response.status(200).entity(registeredCoursesList).build();

    }

    @GET
    @Path("/{studentid}/reportcard")
    @Produces("application/json")
    public Response getReportCard(@PathParam("studentid") String studentId) {
        ReportCardOperation reportCardOperation = new ReportCardOperation(studentId);
        ArrayList<CourseGradeCard> courseGradeCards = reportCardOperation.getGrades();

        return Response.status(200).entity(courseGradeCards).build();

    }

    @GET
    @Path("/{studentid}/notifications")
    @Produces("application/json")
    public Response getNotifications(@PathParam("studentid") String studentId) {
        NotificationDaoInterface notificationDaoInterface = NotificationDaoImp.getInstance();
        ArrayList<String> notifications = notificationDaoInterface.getNotificationsForStudent(studentId);

        return Response.status(200).entity(notifications).build();
    }

    @GET
    @Path("/{studentid}/get-fee-amount")
    @Produces("application/json")
    public Response getFees(@PathParam("studentid") String studentId) {
        StudentInterface studentInterface = new StudentOperation(studentId);
        StudentFeeRest studentFeeRest = new StudentFeeRest(studentId, studentInterface.getTotalFee());

        studentFeeRest.setCourses(studentInterface.getRegisteredCourses());

        return Response.status(200).entity(studentFeeRest).build();

    }

}

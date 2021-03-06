package com.flipkart.bean;

import com.flipkart.dao.CourseCatalogueDaoImp;
import com.flipkart.service.CourseOperation;

import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import java.util.ArrayList;

/**
 * Course - bean class for course
 *
 * @Author -  Team JEDI 02
 */
public class Course {
    @Size(min = 2, max = 10, message = "The length of Course Id should be between 1 to 10")
    String id;
    @Size(min = 1, max = 30, message = "The length of Course Name should be between 1 to 30")
    String name;
    @Size(min = 0, max = 25, message = "The length of Professor Id should be less than 25")
    String professorId;
    @Min(value = 1, message = "Fee should be greater that 0")
    double fee;


    public Course() {

    }


    public Course(String courseId, String courseName, double courseFee) {
        id = courseId;
        name = courseName;
        professorId = null;
        fee = courseFee;
    }

    /**
     * Overloaded constructor for Course class, initialises courseID<br>
     * , course name and professorId
     *
     * @param courseId    course id
     * @param courseName  course name
     * @param professorId stores the information about professor, if no professor is assigned it is null
     */
    public Course(String courseId, String courseName, String professorId, double courseFee) {
        this.id = courseId;
        this.name = courseName;
        this.professorId = professorId;
        this.fee = courseFee;
    }

    /**
     * Returns Id property of Course class
     *
     * @return Id of course
     */
    public String getId() {
        return id;
    }

    /**
     * Sets value of Id property of Course class
     *
     * @param id id of course
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns name property of Course class
     *
     * @return name of course
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of name property of Course class
     *
     * @param name name of course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns professorId property of Course class
     *
     * @return professorId of course
     */
    public String getProfessorId() {
        return professorId;
    }

    /**
     * Sets value of professorId property of Course class
     *
     * @param professorId professorId of course
     */
    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    /**
     * @return the fees of a specific course
     */
    public double getFee() {
        return fee;
    }

    /**
     * Set fee for specific course
     *
     * @param fee
     */
    public void setFee(double fee) {
        this.fee = fee;
    }
}

package com.flipkart.bean;

import java.util.ArrayList;

/**
 * Course - bean class for course
 *
 * @Author -  Team JEDI 02
 */
public class Course {
    String id;
    String name;
    String professorId;
    double fee;


    ArrayList<Student> studentsEnrolled;

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

    /**
     * Constructor for Course class, initialises courseID and course name
     *
     * @param courseId   course id
     * @param courseName course name
     */
    public Course(String courseId, String courseName, double courseFee) {
        id = courseId;
        name = courseName;
        professorId = null;
        studentsEnrolled = null;
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
        studentsEnrolled = null;
    }

    /**
     * Returns a list of enrolled students for this course
     *
     * @return array list of students enrolled
     */
    public ArrayList<Student> getStudentsEnrolled() {

        return studentsEnrolled;
    }

    /**
     * Sets value of students enrolled list property of Course class
     *
     * @param studentsEnrolled arraylist of enrolled students
     */
    public void setStudentsEnrolled(ArrayList<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
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


}

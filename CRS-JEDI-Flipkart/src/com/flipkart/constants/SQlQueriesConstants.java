package com.flipkart.constants;

/**
 * Contains SQl Queries for CRS
 */
public class SQlQueriesConstants {

    public static String LOGIN_USER_QUERY = "SELECT * FROM USERS WHERE USERID = ? AND ROLE=?";


    //Admin Queries
    public static String ADD_USER_QUERY = "INSERT INTO USER VALUES(?,?,?,?,?)";
    public static String ADD_STUDENT_QUERY = "INSERT INTO STUDENT VALUES(?,?,?)";
    public static String ADD_PROFESSOR_QUERY = "INSERT INTO PROFESSOR VALUES(?,?)";

    // Student Queries
    public static String GET_ALL_COURSES_QUERY = "SELECT `courseid`, `coursecatalogueid`, `coursename`, `professorid` FROM `course`";
}

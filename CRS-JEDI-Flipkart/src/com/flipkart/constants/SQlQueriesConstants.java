package com.flipkart.constants;

/**
 * Contains SQl Queries for CRS
 */
public class SQlQueriesConstants {

    public static String LOGIN_USER_QUERY = "SELECT * FROM USERS WHERE USERID = ? AND ROLE=?";


    //Admin Queries
    public static String ADD_USER_QUERY = "INSERT INTO USER(userid,name,email,password,role) VALUES(?,?,?,?,?)";
    public static String ADD_STUDENT_QUERY = "INSERT INTO STUDENT(userid,branch,approved) VALUES(?,?,?)";
    public static String ADD_PROFESSOR_QUERY = "INSERT INTO PROFESSOR(userid,department) VALUES(?,?)";
    public static String ADD_COURSE_QUERY = "INSERT INTO COURSE(courseid,coursename,professorid,coursecatalogueid) VALUES(?,?,?,?)";
    public static String APPROVE_STUDENT_QUERY = "UPDATE STUDENT SET APPROVED=\"1\" WHERE USERID = ?";
}

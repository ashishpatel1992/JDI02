package com.flipkart.constants;

/**
 * Contains SQl Queries for CRS
 */
public class SQlQueriesConstants {

    public static String LOGIN_USER_QUERY = "SELECT * FROM USERS WHERE USERID = ? AND ROLE=?";


    //Admin Queries
    public static String ADD_USER_QUERY = "INSERT INTO USER VALUES(?,?,?,?,?)";
    public static  String ADD_STUDENT_QUERY = "INSERT INTO STUDENT VALUES(?,?,?)";
}

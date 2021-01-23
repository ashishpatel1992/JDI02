package com.flipkart.constants;

/**
 * Contains SQl Queries for CRS
 */
public class SQlQueriesConstants {

    public static String LOGIN_USER_QUERY = "SELECT * FROM USERS WHERE USERID = ? AND ROLE=?";


    //Professor Queries
    public static  String ADD_GRADE_QUERY = "UPDATE REGISTEREDCOURSE SET GRADE = ? WHERE STUDENTID = ? AND COURSEID = ?";
    public static String GET_ENROLLED_STUDENTS_FOR_COURSE = "SELECT user.userid,user.name FROM  USER JOIN REGISTEREDCOURSE ON USER.USERID = REGISTEREDCOURSE.STUDENTID WHERE COURSEID=?";

    //Admin Queries
    public static String ADD_USER_QUERY = "INSERT INTO USER(userid,name,email,password,role) VALUES(?,?,?,?,?)";
    public static String ADD_STUDENT_QUERY = "INSERT INTO STUDENT(userid,branch,approved) VALUES(?,?,?)";
    public static String ADD_PROFESSOR_QUERY = "INSERT INTO PROFESSOR(userid,department) VALUES(?,?)";
    public static String ADD_COURSE_QUERY = "INSERT INTO COURSE(courseid,coursename,professorid,coursecatalogueid) VALUES(?,?,?,?)";
    public static String APPROVE_STUDENT_QUERY = "UPDATE STUDENT SET APPROVED=\"1\" WHERE USERID = ?";

    //Student Queries
    public static String GET_ALL_COURSES_QUERY = "SELECT `courseid`, `coursecatalogueid`, `coursename`, `professorid` FROM `course`";
    public static String GET_ALL_COURSE_ID_FOR_REG_STUDENT_QUERY = "SELECT `studentid`, `courseid`, `grade` FROM `registeredcourse` WHERE `studentid` = ?";
    public static String ADD_STUDENT_COURSE_REGISTRATION = "INSERT INTO `registeredcourse`(`studentid`, `courseid`) VALUES (?,?);";

    //Course Queries
    public static  String GET_COURSE_DETAIL = "SELECT `courseid`, `coursecatalogueid`, `coursename`, `professorid` FROM `course` WHERE `courseid` = ?";
}

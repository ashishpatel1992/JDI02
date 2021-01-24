package com.flipkart.constants;

/**
 * Contains SQl Queries for CRS Application
 */
public class SQLQueriesConstants {

    public static String LOGIN_USER_QUERY = "SELECT * FROM USERS WHERE USERID = ? AND ROLE=?";

    // User Queries
    public static String GET_USER_PROFILE_QUERY = "SELECT `userid`, `name`, `email`, `role` FROM `user` WHERE `userid` = ?";
    public static String VERIFY_CREDENTIALS_QUERY ="SELECT * FROM USER WHERE USERID = ? AND PASSWORD = ?";

    // Professor Queries
    public static String ADD_GRADE_QUERY = "UPDATE REGISTEREDCOURSE SET GRADE = ? WHERE STUDENTID = ? AND COURSEID = ?";
    public static String GET_ENROLLED_STUDENTS_FOR_COURSE = "SELECT user.userid,user.name FROM  USER JOIN REGISTEREDCOURSE ON USER.USERID = REGISTEREDCOURSE.STUDENTID WHERE COURSEID=?";
    public static String GET_PROFESSOR_PROFILE_QUERY = "SELECT professor.`userid`, USER.`name`,  USER.`email`,  USER.`role`,  `professor`.`department` FROM  professor,  USER WHERE  professor.userid = USER.userid AND professor.userid = ?";

    // Admin Queries
    public static String ADD_USER_QUERY = "INSERT INTO USER(userid,name,email,password,role) VALUES(?,?,?,?,?)";
    public static String ADD_STUDENT_QUERY = "INSERT INTO STUDENT(userid,branch,approved) VALUES(?,?,?)";
    public static String ADD_PROFESSOR_QUERY = "INSERT INTO PROFESSOR(userid,department) VALUES(?,?)";
    public static String ADD_COURSE_QUERY = "INSERT INTO COURSE(courseid,coursename,professorid,coursecatalogueid) VALUES(?,?,?,?)";
    public static String APPROVE_STUDENT_QUERY = "UPDATE STUDENT SET APPROVED=\"1\" WHERE USERID = ?";
    public static String GET_UNAPPROVED_STUDENT_LIST = "SELECT `userid`, `branch`, `approved` FROM `student` WHERE `approved` = 0";

    // Student Queries
    public static String GET_STUDENT_PROFILE_QUERY = "SELECT stud.`userid`, USER.`name`,  USER.`email`,  USER.`role`,  stud.`branch`,  stud.`approved` FROM  student AS stud,  USER WHERE  stud.userid = USER.userid AND stud.userid = ?";
    public static String GET_ALL_COURSE_ID_FOR_REG_STUDENT_QUERY = "SELECT `studentid`, `courseid`, `grade` FROM `registeredcourse` WHERE `studentid` = ?";
    public static String ADD_STUDENT_COURSE_REGISTRATION = "INSERT INTO `registeredcourse`(`studentid`, `courseid`) VALUES (?,?);";
    public static String GET_APPROVAL_STATUS_QUERY = "SELECT `userid`, `branch`, `approved` FROM `student` WHERE `userid` = ?";

    //Course Queries
    public static String GET_COURSE_DETAIL = "SELECT `courseid`, `coursecatalogueid`, `coursename`, `professorid` FROM `course` WHERE `courseid` = ?";
    public static String GET_ALL_COURSES_QUERY = "SELECT `courseid`, `coursecatalogueid`, `coursename`, `professorid` FROM `course`";
    public static String ADD_PROFESSOR_TO_COURSE_ID = "UPDATE `course` SET `professorid`=? WHERE `courseid` = ?";

}

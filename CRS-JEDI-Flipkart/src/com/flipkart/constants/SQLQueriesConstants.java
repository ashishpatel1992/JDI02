package com.flipkart.constants;

/**
 * Contains SQl Queries for CRS Application
 */
public class SQLQueriesConstants {

    public static String LOGIN_USER_QUERY = "SELECT * FROM `user` WHERE `userid` = ? AND `role` = ? ";

    // User Queries
    public static String GET_USER_PROFILE_QUERY = "SELECT `userid`, `name`, `email`, `role` FROM `user` WHERE `userid` = ?";
    public static String VERIFY_CREDENTIALS_QUERY ="SELECT * FROM user WHERE USERID = ? AND PASSWORD = ?";

    // Professor Queries
    public static String ADD_GRADE_QUERY = "UPDATE `registeredcourse` SET `grade` = ? WHERE `studentid` = ? AND `courseid`= ?";
    public static String GET_ENROLLED_STUDENTS_FOR_COURSE = "SELECT user.`userid`,user.`name` FROM  `user` JOIN `registeredcourse` ON user.`userid` = registeredcourse.`studentid` WHERE `courseid`=?";
    public static String GET_PROFESSOR_PROFILE_QUERY = "SELECT professor.`userid`, user.`name`,  user.`email`,  user.`role`,  `professor`.`department` FROM  `professor`, `user` WHERE  professor.`userid` = user.`userid` AND professor.`userid` = ?";

    // Admin Queries
    public static String ADD_USER_QUERY = "INSERT INTO `user`(`userid`,`name`,`email`,`password`,`role`) VALUES(?,?,?,?,?)";
    public static String ADD_STUDENT_QUERY = "INSERT INTO `student`(`userid`,`branch`,`approved`) VALUES(?,?,?)";
    public static String ADD_PROFESSOR_QUERY = "INSERT INTO `professor`(`userid`,`department`) VALUES(?,?)";
    public static String ADD_COURSE_QUERY = "INSERT INTO `course`(`courseid`,`coursename`,`professorid`,`coursecatalogueid`) VALUES(?,?,?,?)";
    public static String APPROVE_STUDENT_QUERY = "UPDATE `student` SET `approved`=\"1\" WHERE `userid` = ?";
    public static String GET_UNAPPROVED_STUDENT_LIST = "SELECT `userid`, `branch`, `approved` FROM `student` WHERE `approved` = 0";
    public static String GET_ADMIN_PROFILE_QUERY = "SELECT `userid`, `name`, `email`, `role` FROM `user` WHERE `userid` = ? AND `role` = 'admin'";

    // Student Queries
    public static String GET_STUDENT_PROFILE_QUERY = "SELECT stud.`userid`, user.`name`,  user.`email`,  user.`role`,  stud.`branch`,  stud.`approved` FROM  `student` AS stud,  `user` AS user WHERE  stud.userid = user.`userid` AND stud.`userid` = ?";
    public static String GET_ALL_COURSE_ID_FOR_REG_STUDENT_QUERY = "SELECT `studentid`, `courseid`, `grade` FROM `registeredcourse` WHERE `studentid` = ?";
    public static String ADD_STUDENT_COURSE_REGISTRATION = "INSERT INTO `registeredcourse`(`studentid`, `courseid`) VALUES (?,?);";
    public static String GET_APPROVAL_STATUS_QUERY = "SELECT `userid`, `branch`, `approved` FROM `student` WHERE `userid` = ?";

    //Course Queries
    public static String GET_COURSE_DETAIL = "SELECT `courseid`, `coursecatalogueid`, `coursename`, `professorid` FROM `course` WHERE `courseid` = ?";
    public static String GET_ALL_COURSES_QUERY = "SELECT `courseid`, `coursecatalogueid`, `coursename`, `professorid` FROM `course`";
    public static String ADD_PROFESSOR_TO_COURSE_ID = "UPDATE `course` SET `professorid`=? WHERE `courseid` = ?";
    public static String GET_UNASSIGNED_COURSES_QUERY = "SELECT `courseid`, `coursecatalogueid`, `coursename`, `professorid` FROM `course` WHERE `professorid` IS NULL";
    public static String GET_UNASSIGNED_PROFESSORS_QUERY = "SELECT `userid`,`department` FROM `professor` WHERE professor.userid NOT IN (SELECT `professorid` FROM `course` WHERE `professorid` IS NOT NULL)";

}

package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class that implements all methods of AdminDaoInterface
 */
public class AdminDaoImp implements AdminDaoInterface {

    private static volatile AdminDaoImp instance = null;

    private AdminDaoImp() {
    }

    /**
     * Returns static instance of AdminDaoImp class
     *
     * @return instance of AdminDaoImp class
     */
    public static AdminDaoImp getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (AdminDaoImp.class) {
                instance = new AdminDaoImp();
            }
        }
        return instance;
    }

    private static Logger logger = Logger.getLogger(AdminDaoImp.class);
    Connection connection = DBUtils.getConnection();

    /**
     * Adds new course to catalogue
     *
     * @param course course to add
     * @return true if course is added successfully
     */
    @Override
    public boolean addCourse(Course course) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(SQLQueriesConstants.ADD_COURSE_QUERY);
            stmt.setString(1, course.getId());
            stmt.setString(2, course.getName());
            stmt.setString(3, course.getProfessorId());
            stmt.setString(4, "1");
            int updatedValues = stmt.executeUpdate();
            if (updatedValues > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Returns list of unapproved students
     *
     * @return array list of un approved students
     */
    @Override
    public ArrayList<String> getUnApprovedStudentsIds() {
        ArrayList<String> unApprovedStudentIds = new ArrayList<String>();
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(SQLQueriesConstants.GET_UNAPPROVED_STUDENT_LIST);
            logger.info(resultSet.getFetchSize());
            while (resultSet.next()) {
                String unApprovedStudentId;
                unApprovedStudentId = resultSet.getString("userid");
                unApprovedStudentIds.add(unApprovedStudentId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return unApprovedStudentIds;
    }

    /**
     * Approves a student for registration
     *
     * @param studentId Id of student to be approved
     * @return true if the student was successfully approved
     */
    @Override
    public boolean approveStudent(String studentId) {

        StudentDaoInterface studentDaoInterface = StudentDaoImp.getInstance();
        return studentDaoInterface.approveStudent(studentId);
    }

    /**
     * Assigns a course to professor
     *
     * @param professorId id of professor to assign
     * @param courseId    id of course to assign
     * @return true if professor was successfully assigned the course
     */
    @Override
    public boolean assignProfessorToCourse(String professorId, String courseId) {

        return CourseCatalogueDaoImp.getInstance().assignProfessorToCourse(professorId, courseId);
    }

    @Override
    public Admin getAdminProfile(String adminId) {
        Admin admin = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(SQLQueriesConstants.GET_ADMIN_PROFILE_QUERY);
            logger.info(resultSet.getFetchSize());
            while (resultSet.next()) {
                String rsUserId;
                String rsName;
                String rsEmail;
                String rsRole;

                rsUserId = resultSet.getString("userid");
                rsName = resultSet.getString("name");
                rsEmail = resultSet.getString("email");
                rsRole = resultSet.getString("role");
                admin = new Admin(rsUserId, rsName, rsEmail, rsRole);
                return admin;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return admin;
    }
}

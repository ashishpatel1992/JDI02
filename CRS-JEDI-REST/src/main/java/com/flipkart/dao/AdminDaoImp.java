package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class that implements all methods of AdminDaoInterface
 *
 * @Author -  Team JEDI 02
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

    private static final Logger logger = Logger.getLogger(AdminDaoImp.class);
    Connection connection = DBUtils.getConnection();

    /**
     * Adds new course to catalogue
     *
     * @param course course to add
     * @return true if course is added successfully
     */
    @Override
    public boolean addCourse(Course course) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_COURSE_QUERY);
            preparedStatement.setString(1, course.getId());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setString(3, course.getProfessorId());
            preparedStatement.setString(4, "1");
            preparedStatement.setDouble(5, course.getFee());
            int updatedValues = preparedStatement.executeUpdate();
            return updatedValues > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
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
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQueriesConstants.GET_UNAPPROVED_STUDENT_LIST);

            while (resultSet.next()) {
                String unApprovedStudentId;
                unApprovedStudentId = resultSet.getString("userid");
                unApprovedStudentIds.add(unApprovedStudentId);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_ADMIN_PROFILE_QUERY);
            preparedStatement.setString(1, adminId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {

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
            } else {
                throw new UserNotFoundException("AdminId " + adminId + " not found.");
            }


        } catch (SQLException | UserNotFoundException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return admin;
    }
}

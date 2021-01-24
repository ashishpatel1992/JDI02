package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that implements all methods of StudentDaoInterface
 */
public class StudentDaoImp implements StudentDaoInterface {

    private static volatile StudentDaoImp instance = null;

    private StudentDaoImp() {
    }

    /**
     * Returns static instance of StudentDaoImp class
     *
     * @return instance of StudentDaoImp class
     */
    public static StudentDaoImp getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (StudentDaoImp.class) {
                instance = new StudentDaoImp();
            }
        }
        return instance;
    }

    private static Logger logger = Logger.getLogger(StudentDaoImp.class);
    Connection connection = DBUtils.getConnection();

    /**
     * Gets details of student by student id
     *
     * @param studentId id of student for which details are returned
     * @return details of student in Student object
     */
    @Override
    public Student getStudent(String studentId) {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            stmt = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_PROFILE_QUERY);
            stmt.setString(1, studentId);
            resultSet = stmt.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    String rsStudentId;
                    String rsStudentName;
                    String rsStudentEmail;
                    String rsStudentRole;
                    String rsStudentBranch;
                    boolean rsStudentApproved;

                    rsStudentId = resultSet.getString("userid");
                    rsStudentName = resultSet.getString("name");
                    rsStudentEmail = resultSet.getString("email");
                    rsStudentRole = resultSet.getString("role");
                    rsStudentBranch = resultSet.getString("branch");
                    rsStudentApproved = resultSet.getString("approved").equalsIgnoreCase("1") ? true : false;

                    student = new Student(rsStudentId, rsStudentName, rsStudentEmail, rsStudentRole, rsStudentBranch, rsStudentApproved);
                    return student;
                }
            } else {
                throw new UserNotFoundException("StudentId " + studentId + " not found.");
            }


        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return student;
    }

    /**
     * Gets approved status for a student by student id
     *
     * @param studentId id of student for which the approved status is required
     * @return true if student is approved, else false
     */
    @Override
    public boolean getApprovalStatus(String studentId) {
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        String approvedStatus = null;
        try {
            stmt = connection.prepareStatement(SQLQueriesConstants.GET_APPROVAL_STATUS_QUERY);
            stmt.setString(1, studentId);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                approvedStatus = resultSet.getString("approved");
                if (approvedStatus.equalsIgnoreCase("1")) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Sets the approved status to 1(true) by student id
     *
     * @param studentId id of student for which the status has to be set
     * @return true if it was successfully set, else false
     */
    @Override
    public boolean approveStudent(String studentId) {
        PreparedStatement stmt = null;
        if (!getApprovalStatus(studentId)) {
            try {
                stmt = connection.prepareStatement(SQLQueriesConstants.APPROVE_STUDENT_QUERY);
                stmt.setString(1, studentId);
                int updatedValues = stmt.executeUpdate();
                if (updatedValues > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
            return false;
        }
        return false;

    }
}

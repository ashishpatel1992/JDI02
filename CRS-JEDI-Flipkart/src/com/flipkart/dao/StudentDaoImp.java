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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Class that implements all methods of StudentDaoInterface
 *
 * @Author -  Team JEDI 02
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_PROFILE_QUERY);
            preparedStatement.setString(1, studentId);
            resultSet = preparedStatement.executeQuery();

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
                preparedStatement.close();
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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String approvedStatus = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_APPROVAL_STATUS_QUERY);
            preparedStatement.setString(1, studentId);
            resultSet = preparedStatement.executeQuery();

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
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
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
        PreparedStatement preparedStatement = null;
        if (!getApprovalStatus(studentId)) {
            try {
                preparedStatement = connection.prepareStatement(SQLQueriesConstants.APPROVE_STUDENT_QUERY);
                preparedStatement.setString(1, studentId);
                int updatedValues = preparedStatement.executeUpdate();
                if (updatedValues > 0) {
                    return true;
                } else {
                    return false;
                }
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
        return false;

    }

    /**
     * Calculate total fee for a student from database
     *
     * @return fee amount
     */
    @Override
    public int getTotalFee(String studentId) {
        int totalFees = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.CALCULATE_TOTAL_FEE);
            preparedStatement.setString(1, studentId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalFees = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return totalFees;
    }

    /**
     * Make payment of fee for a student
     *
     * @param studentId     if of student for which payment has to be made
     * @param paymentMethod method selected for making payment
     * @param fees          fees to be payed
     */
    @Override
    public boolean makePayment(String studentId, int paymentMethod, int fees) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.MAKE_PAYMENT_QUERY);
            preparedStatement.setString(1, studentId);
            preparedStatement.setInt(2, fees);
            preparedStatement.setInt(3, paymentMethod);
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            preparedStatement.setString(4, strDate);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException se) {
            logger.error("Fee already payed!");
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}

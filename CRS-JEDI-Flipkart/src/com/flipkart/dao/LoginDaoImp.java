package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.UserAlreadyExistException;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Random;

/**
 * Class that implements all methods of LoginDaoInterface
 */
public class LoginDaoImp implements LoginDaoInterface {

    private static volatile LoginDaoImp instance = null;

    private LoginDaoImp() {
    }
    /**
     * Returns static instance of LoginDaoImp class
     * @return instance of LoginDaoImp class
     */
    public static LoginDaoImp getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (LoginDaoImp.class) {
                instance = new LoginDaoImp();
            }
        }
        return instance;
    }

    private static Logger logger = Logger.getLogger(LoginDaoImp.class);
    Connection connection = DBUtils.getConnection();

    /**
     *  Verify credentials and returns login status
     * @param userId id of user to login
     * @param password password of user
     * @return true if provided userid and password are correct
     */
    @Override
    public boolean login(String userId, String password) {
        PreparedStatement stmt = null;
        ResultSet rs= null;
        try{
            stmt = connection.prepareStatement(SQLQueriesConstants.VERIFY_CREDENTIALS_QUERY);
            stmt.setString(1,userId);
            stmt.setString(2,password);
            rs = stmt.executeQuery();
            while(rs.next()){
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Adds a new student to database
     * @param student details of student in Student object
     * @param password password of student
     * @return userId if student was successfully added
     * @return null if adding student failed
     */
    @Override
    public String addStudent(Student student,String password) {
        String userId = null;
        PreparedStatement stmt = null;
        try {
            userId = addUser(student.getId(), student.getName(), student.getEmail(), "student",password);
            stmt = connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT_QUERY);
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getBranch());
            int studentApproved = (student.isApproved()) ? 1 : 0;
            stmt.setInt(3, studentApproved);
            int updatedValues = stmt.executeUpdate();
            if (updatedValues > 0) {
                return userId;
            } else {
                return null;
            }
        } catch (UserAlreadyExistException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }


        return password;
    }

    /**
     * Adds a new professor to database
     * @param professor details of professor in Professor object
     * @param password password of professor
     * @return userId if professor was successfully added
     * @return null if adding professor failed
     */
    @Override
    public String addProfessor(Professor professor,String password) {
        String userId = null;
        PreparedStatement stmt = null;
        try {
            userId = addUser(professor.getId(), professor.getName(), professor.getEmail(), "professor",password);
            stmt = connection.prepareStatement(SQLQueriesConstants.ADD_PROFESSOR_QUERY);
            stmt.setString(1, professor.getId());
            stmt.setString(2, professor.getDepartment());
            int updatedValues = stmt.executeUpdate();
            if (updatedValues > 0) {
                return userId;
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (UserAlreadyExistException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * Check if a user already exists in database
     * @param userId id of user to check
     * @return true if user already exists
     */
    @Override
    public boolean checkIfUserAlreadyExist(String userId){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_USER_PROFILE_QUERY);
            preparedStatement.setString(1,userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String rsUserId;

                rsUserId = resultSet.getString("userid");
                if(rsUserId.equalsIgnoreCase(userId)){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Adds a new user to database
     * @param userid if of new user
     * @param name name of new user
     * @param email email of new user
     * @param role role of new user
     * @param password password of new user
     * @return user id if user was added successfully
     * @return null if adding user failed
     * @throws UserAlreadyExistException Exception is thrown when new user already exists in database
     */
    public String addUser(String userid, String name, String email, String role,String password) throws UserAlreadyExistException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, role);
            int updatedValues = preparedStatement.executeUpdate();
            if (updatedValues > 0) {
                return password;
            } else {
                return null;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new UserAlreadyExistException("UserId: " + userid + " already exists.");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return password;
    }

}

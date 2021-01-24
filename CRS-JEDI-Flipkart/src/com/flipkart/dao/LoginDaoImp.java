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
 * Database access object class for login operations, performs the following operations
 * 1. Verify login credentials for a user
 * 2. Adds a student to database
 * 3. Adds a professor to database
 * 4. Approve a registered student
 */
public class LoginDaoImp implements LoginDaoInterface {

    private static volatile LoginDaoImp instance = null;

    private LoginDaoImp() {
    }

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

    @Override
    public String addStudent(Student student) {
        String password = null;
        PreparedStatement stmt = null;
        try {
            password = addUser(student.getId(), student.getName(), student.getEmail(), "student");
            stmt = connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT_QUERY);
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getBranch());
            int studentApproved = (student.isApproved()) ? 1 : 0;
            stmt.setInt(3, studentApproved);
            int updatedValues = stmt.executeUpdate();
            if (updatedValues > 0) {
                return password;
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

    @Override
    public String addProfessor(Professor professor) {
        String password = null;
        PreparedStatement stmt = null;
        try {
            password = addUser(professor.getId(), professor.getName(), professor.getEmail(), "professor");
            stmt = connection.prepareStatement(SQLQueriesConstants.ADD_PROFESSOR_QUERY);
            stmt.setString(1, professor.getId());
            stmt.setString(2, professor.getDepartment());
            int updatedValues = stmt.executeUpdate();
            if (updatedValues > 0) {
                return password;
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
    public String addUser(String userid, String name, String email, String role) throws UserAlreadyExistException {
        String generatedPassword = null;
        PreparedStatement preparedStatement = null;
        try {
            generatedPassword = generatePassword();
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, generatedPassword);
            preparedStatement.setString(5, role);
            int updatedValues = preparedStatement.executeUpdate();
            if (updatedValues > 0) {
                return generatedPassword;
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
        return generatedPassword;
    }

    public String generatePassword() {

        String numbers = "0123456789";
        Random rndm_method = new Random();
        String otp = new String();
        for (int i = 0; i < 5; i++) {
            otp += numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }

}

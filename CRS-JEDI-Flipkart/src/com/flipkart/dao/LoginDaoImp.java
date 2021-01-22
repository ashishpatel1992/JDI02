package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.SQlQueriesConstants;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * Database access object class for login operations, performs the following operations
 * 1. Verify login credentials for a user
 * 2. Adds a student to database
 * 3. Adds a professor to database
 * 4. Approve a registered student
 */
public class LoginDaoImp implements LoginDaoInterface{

    private static Logger logger = Logger.getLogger(LoginDaoImp.class);
    Connection connection = DBUtils.getConnection();

    @Override
    public String login(String userId, String password) {

        return null;
    }

    @Override
    public String addStudent(Student student) {
        String password = addUser(student.getId(),student.getName(),student.getEmail(),"student");
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(SQlQueriesConstants.ADD_STUDENT_QUERY);
            stmt.setString(1,student.getId());
            stmt.setString(2,student.getBranch());
            int studentApproved = (student.isApproved()) ? 1 : 0;
            stmt.setInt(3,studentApproved);
            int updatedValues  = stmt.executeUpdate();
            if(updatedValues>0){
                return password;
            }else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

    @Override
    public String addProfessor(Professor professor) {
        String password = addUser(professor.getId(),professor.getName(),professor.getEmail(),"professor");
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(SQlQueriesConstants.ADD_PROFESSOR_QUERY);
            stmt.setString(1,professor.getId());
            stmt.setString(2, professor.getDepartment());
            int updatedValues  = stmt.executeUpdate();
            if(updatedValues>0){
                return password;
            }else{
                return null;
            }
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public boolean approveStudent(String studentid) {
        PreparedStatement stmt=null;
        try{
            stmt = connection.prepareStatement(SQlQueriesConstants.APPROVE_STUDENT_QUERY);
            stmt.setString(1,studentid);
            int updatedValues  = stmt.executeUpdate();
            if(updatedValues>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public String addUser(String userid,String name,String email,String role){
        String generatedPassword = generatePassword();
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(SQlQueriesConstants.ADD_USER_QUERY);
            stmt.setString(1,userid);
            stmt.setString(2, name);
            stmt.setString(3,email);
            stmt.setString(4,generatedPassword);
            stmt.setString(5,role);
            int updatedValues  = stmt.executeUpdate();
            if(updatedValues>0){
                return generatedPassword;
            }else{
                return null;
            }
        }catch (SQLException e){
            logger.info(e.getMessage());
            return null;
        }finally{
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public String generatePassword(){

        String numbers = "0123456789";
        Random rndm_method = new Random();
        String otp = new String();
        for (int i = 0; i < 5; i++) {
            otp += numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }

}

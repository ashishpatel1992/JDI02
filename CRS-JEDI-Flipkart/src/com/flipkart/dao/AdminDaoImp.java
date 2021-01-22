package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQlQueriesConstants;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDaoImp implements  AdminDaoInterface{
    private static Logger logger = Logger.getLogger(AdminDaoImp.class);
    Connection connection = DBUtils.getConnection();

    @Override
    public boolean addCourse(Course course) {
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(SQlQueriesConstants.ADD_COURSE_QUERY);
            stmt.setString(1,course.getId());
            stmt.setString(2,course.getName());
            stmt.setString(3,course.getProfessorId());
            stmt.setString(4,"1");
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

    @Override
    public boolean approveStudent(String studentid) {
        LoginDaoInterface loginDaoInterface = new LoginDaoImp();
        return loginDaoInterface.approveStudent(studentid);
    }
}

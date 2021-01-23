package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class AdminDaoImp implements AdminDaoInterface {

    private static volatile AdminDaoImp instance = null;

    private AdminDaoImp() {
    }

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

    @Override
    public boolean approveStudent(String studentId) {

        StudentDaoInterface studentDaoInterface = StudentDaoImp.getInstance();
        return studentDaoInterface.approveStudent(studentId);
    }

    @Override
    public boolean assignProfessorToCourse(String professorId, String courseId) {

        return CourseCatalogueDaoImp.getInstance().assignProfessorToCourse(professorId, courseId);
    }
}

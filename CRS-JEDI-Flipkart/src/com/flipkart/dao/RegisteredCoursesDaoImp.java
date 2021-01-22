package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQlQueriesConstants;
import com.flipkart.service.RegisteredCoursesOperation;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class RegisteredCoursesDaoImp implements RegisteredCoursesDaoInterface {

    private static volatile RegisteredCoursesDaoImp instance = null;
    private static Logger logger = Logger.getLogger(RegisteredCoursesOperation.class);

    Connection connection = DBUtils.getConnection();

    private RegisteredCoursesDaoImp() {
    }

    public static RegisteredCoursesDaoImp getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (RegisteredCoursesDaoImp.class) {
                instance = new RegisteredCoursesDaoImp();
            }
        }
        return instance;
    }

    @Override
    public ArrayList<String> getCourseIdsForStudent(String studentId) {
        Connection connection = DBUtils.getConnection();
        ArrayList<String> studentCourseIdList = new ArrayList<String>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        // TODO: Check if courseId list is null
        try {
            preparedStatement = connection.prepareStatement(SQlQueriesConstants.GET_ALL_COURSE_ID_FOR_REG_STUDENT_QUERY);
            preparedStatement.setString(1, studentId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String rsCourseId;
                String rsStudentId;

                rsCourseId = resultSet.getString("courseid");
                rsStudentId = resultSet.getString("studentId");

//                logger.info(rsCourseId+" ==== "+rsStudentId);
                studentCourseIdList.add(rsCourseId);
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
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return studentCourseIdList;
        }
    }

    @Override
    public ArrayList<String> doStudentRegistration(String studentId, ArrayList<String> courseIdSelectionList) {
        // ADD_STUDENT_COURSE_REGISTRATION
        logger.info("Inside RegisteredCoursesDaoImp:doStudentRegistration");
        PreparedStatement stmt = null;
        try {
            for (String courseIdSelection : courseIdSelectionList) {
                stmt = connection.prepareStatement(SQlQueriesConstants.ADD_STUDENT_COURSE_REGISTRATION);
                stmt.setString(1, studentId);
                stmt.setString(2, courseIdSelection);

                int updatedValues = stmt.executeUpdate();
                logger.info(updatedValues + " items inserted");
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
        return courseIdSelectionList;
    }
}

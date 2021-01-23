package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class CourseCatalogueDaoImp implements CourseCatalogueDaoInterface {
//    public static void main(String[] args) {
//        CourseCatalogueDaoImp courseCatalogueDaoImp =new CourseCatalogueDaoImp();
//        courseCatalogueDaoImp.getAllCourses();
//    }

    private static volatile CourseCatalogueDaoImp instance = null;

    private CourseCatalogueDaoImp() {
    }

    public static CourseCatalogueDaoImp getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (CourseCatalogueDaoImp.class) {
                instance = new CourseCatalogueDaoImp();
            }
        }
        return instance;
    }

    private static Logger logger = Logger.getLogger(CourseCatalogueDaoImp.class);


    public ArrayList<Course> getAllCourses() {
        Connection connection = DBUtils.getConnection();
        ArrayList<Course> courseArrayList = new ArrayList<Course>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQueriesConstants.GET_ALL_COURSES_QUERY);

            while (resultSet.next()) {
                String courseId;
                String courseName;
                String professorId;

                courseId = resultSet.getString("courseid");
                courseName = resultSet.getString("coursename");
                // TODO: if professor is not null then fetch professor Name and return
                professorId = resultSet.getString("professorid");
//                logger.info(courseId+" "+courseName+" "+professorId);
                Course course = new Course(courseId, courseName, professorId);
                courseArrayList.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            

        }
        return courseArrayList;
    }

    @Override
    public Course getCourseDetail(String courseId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course course = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_COURSE_DETAIL);
            preparedStatement.setString(1, courseId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    String rsCourseId;
                    String rsCourseName;
                    String rsProfessorId;

                    rsCourseId = resultSet.getString("courseid");
                    rsCourseName = resultSet.getString("coursename");
                    // TODO: if professor is not null then fetch professor Name and return
                    rsProfessorId = resultSet.getString("professorid");
                    course = new Course(rsCourseId, rsCourseName, rsProfessorId);
                    break;
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e);
            }

            return course;
        }
    }

    @Override
    public boolean assignProfessorToCourse(String professorId, String courseId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = connection.prepareStatement(SQLQueriesConstants.ADD_PROFESSOR_TO_COURSE_ID);
            stmt.setString(1, professorId);
            stmt.setString(2, courseId);
            int updatedValues = stmt.executeUpdate();
            if (updatedValues > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}

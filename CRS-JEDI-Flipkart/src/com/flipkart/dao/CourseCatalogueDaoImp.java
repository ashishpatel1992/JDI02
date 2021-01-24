package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class that implements all methods of CourseCatalogueInterface
 */
public class CourseCatalogueDaoImp implements CourseCatalogueDaoInterface {

    private static volatile CourseCatalogueDaoImp instance = null;

    private CourseCatalogueDaoImp() {
    }

    /**
     * Returns static instance of CourseCatalogueDaoImp class
     * @return instance of CourseCatalogueDaoImp class
     */
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

    /**
     * Returns list of all courses from catalogue
     * @return arraylist of courses
     */
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

    /**
     * Returns course details for a course
     * @param courseId id of course for which details will be returned
     * @return course details in Course object
     * @throws CourseNotFoundException thrown in case no course is found with provided courseid
     */
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

    /**
     * Assigns a professor to a course
     * @param professorId id of professor which has to be assigned to course
     * @param courseId id of course to which professor will be assigned
     * @return true if professor was successfully assigned to the course
     */
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

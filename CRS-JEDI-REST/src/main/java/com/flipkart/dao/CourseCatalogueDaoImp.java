package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class that implements all methods of CourseCatalogueInterface
 *
 * @Author -  Team JEDI 02
 */
public class CourseCatalogueDaoImp implements CourseCatalogueDaoInterface {

    private static volatile CourseCatalogueDaoImp instance = null;

    public CourseCatalogueDaoImp() {
    }

    /**
     * Returns static instance of CourseCatalogueDaoImp class
     *
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

    private static final Logger logger = Logger.getLogger(CourseCatalogueDaoImp.class);
    Connection connection = DBUtils.getConnection();

    /**
     * Returns list of all courses from catalogue
     *
     * @return arraylist of courses
     */
    public ArrayList<Course> getAllCourses() {

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
                double courseFee;

                courseId = resultSet.getString("courseid");
                courseName = resultSet.getString("coursename");
                professorId = resultSet.getString("professorid");
                courseFee = resultSet.getDouble("fee");
                Course course = new Course(courseId, courseName, professorId, courseFee);
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
     *
     * @param courseId id of course for which details will be returned
     * @return course details in Course object
     * @throws CourseNotFoundException thrown in case no course is found with provided courseid
     */
    @Override
    public Course getCourseDetail(String courseId) {

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
                    double rsCourseFee;

                    rsCourseId = resultSet.getString("courseid");
                    rsCourseName = resultSet.getString("coursename");
                    rsProfessorId = resultSet.getString("professorid");
                    rsCourseFee = resultSet.getDouble("fee");
                    course = new Course(rsCourseId, rsCourseName, rsProfessorId, rsCourseFee);
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
     *
     * @param professorId id of professor which has to be assigned to course
     * @param courseId    id of course to which professor will be assigned
     * @return true if professor was successfully assigned to the course
     */
    @Override
    public boolean assignProfessorToCourse(String professorId, String courseId) {

        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_PROFESSOR_TO_COURSE_ID);
            preparedStatement.setString(1, professorId);
            preparedStatement.setString(2, courseId);
            int updatedValues = preparedStatement.executeUpdate();
            return updatedValues > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<Course> getUnAssignedCourses() {
        ArrayList<Course> unAssignedCourses = new ArrayList<Course>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Course course = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_UNASSIGNED_COURSES_QUERY);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    String rsCourseId;
                    String rsCourseName;
                    String rsProfessorId;
                    double rsCourseFee;

                    rsCourseId = resultSet.getString("courseid");
                    rsCourseName = resultSet.getString("coursename");
                    rsProfessorId = resultSet.getString("professorid");
                    rsCourseFee = resultSet.getDouble("fee");
                    course = new Course(rsCourseId, rsCourseName, rsProfessorId, rsCourseFee);
                    unAssignedCourses.add(course);
                }
                return unAssignedCourses;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e);
            }

        }
        return unAssignedCourses;
    }

    @Override
    public ArrayList<Professor> getUnAssignedProfessors() {
        ArrayList<Professor> unAssignedProfessors = new ArrayList<Professor>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_UNASSIGNED_PROFESSORS_QUERY);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    String rsProfessorId;

                    rsProfessorId = resultSet.getString("userid");
                    Professor professor = ProfessorDaoImp.getInstance().getProfessor(rsProfessorId);
                    unAssignedProfessors.add(professor);
                }
                return unAssignedProfessors;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e);
            }

        }
        return unAssignedProfessors;
    }

    @Override
    public ArrayList<Student> getStudentsEnrolled(String courseId) {
        ArrayList<Student> studentsEnrolledList = new ArrayList<Student>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_STUDENTS_ENROLLED_QUERY);
            preparedStatement.setString(1, courseId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    String rsStudentId;

                    rsStudentId = resultSet.getString("userid");
                    Student professor = StudentDaoImp.getInstance().getStudent(rsStudentId);
                    studentsEnrolledList.add(professor);
                }
                return studentsEnrolledList;
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e);
            }

        }
        return studentsEnrolledList;
    }
}

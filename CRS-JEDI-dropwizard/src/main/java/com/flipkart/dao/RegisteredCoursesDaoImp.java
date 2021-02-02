package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseGradeCard;
import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.service.RegisteredCoursesOperation;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class that implements all methods of RegisteredCoursesDaoInterface
 *
 * @Author -  Team JEDI 02
 */
public class RegisteredCoursesDaoImp implements RegisteredCoursesDaoInterface {

    private static volatile RegisteredCoursesDaoImp instance = null;
    private static final Logger logger = Logger.getLogger(RegisteredCoursesOperation.class);

    Connection connection = DBUtils.getConnection();

    private RegisteredCoursesDaoImp() {
    }

    /**
     * Returns static instance of RegisteredCoursesDaoImp class
     *
     * @return instance of RegisteredCoursesDaoImp class
     */
    public static RegisteredCoursesDaoImp getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (RegisteredCoursesDaoImp.class) {
                instance = new RegisteredCoursesDaoImp();
            }
        }
        return instance;
    }

    /**
     * Returns list of course ids a student is enrolled in
     *
     * @param studentId id of student for which the list of courses is required
     * @return arraylist of courseids the student is enrolled in
     */
    @Override
    public ArrayList<String> getCourseIdsForStudent(String studentId) {

        ArrayList<String> studentCourseIdList = new ArrayList<String>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_ALL_COURSE_ID_FOR_REG_STUDENT_QUERY);
            preparedStatement.setString(1, studentId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String rsCourseId;
                String rsStudentId;

                rsCourseId = resultSet.getString("courseid");
                rsStudentId = resultSet.getString("studentId");

                studentCourseIdList.add(rsCourseId);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            return studentCourseIdList;
        }
    }

    /**
     * Enrolls student in selected courses
     *
     * @param studentId             id of the student for which the courses have to be enrolled
     * @param courseIdSelectionList list of courseids which have to be enrolled for student
     * @return arraylist of courseids in which the student was enrolled
     */
    @Override
    public ArrayList<String> doStudentRegistration(String studentId, ArrayList<String> courseIdSelectionList) {
        PreparedStatement preparedStatement = null;
        ArrayList<String> courseIdRemovalList = new ArrayList<>();
        try {
            for (String courseIdSelection : courseIdSelectionList) {

                // Check if course exists
                CourseCatalogueDaoImp courseCatalogueDaoImp = CourseCatalogueDaoImp.getInstance();
                if (courseCatalogueDaoImp.getCourseDetail(courseIdSelection) == null) {
                    courseIdRemovalList.add(courseIdSelection);
                }
            }
            courseIdSelectionList.removeAll(courseIdRemovalList);
            for (String courseIdSelection : courseIdSelectionList) {
                preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT_COURSE_REGISTRATION);
                preparedStatement.setString(1, studentId);
                preparedStatement.setString(2, courseIdSelection);

                int updatedValues = preparedStatement.executeUpdate();
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
        return courseIdSelectionList;
    }

    @Override
    public ArrayList<CourseGradeCard> getCourseGradeCardForStudent(String studentId) {
        ArrayList<CourseGradeCard> courseGradeCards = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_COURSES_GRADE);
            preparedStatement.setString(1, studentId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String rsStudentId = studentId;
                String rsName = resultSet.getString("name");
                String rsEmail = resultSet.getString("email");
                String rsRole = resultSet.getString("role");
                String rsBranch = resultSet.getString("branch");
                boolean rsApproved = resultSet.getString("approved").equals("1");
                String rsCourseId = resultSet.getString("courseid");
                String rsCourseName = resultSet.getString("coursename");
                String rsProfessorId = resultSet.getString("professorid");
                double rsFee = resultSet.getDouble("fee");
                String rsGrade = resultSet.getString("grade") != null ? resultSet.getString("grade") : "-";

                Course course = new Course(rsCourseId, rsCourseName, rsProfessorId, rsFee);
                Student student = new Student(rsStudentId, rsName, rsEmail, rsRole, rsBranch, rsApproved);

                CourseGradeCard courseGradeCard = new CourseGradeCard(course, student, rsGrade);
                courseGradeCards.add(courseGradeCard);

            }
            return courseGradeCards;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            return courseGradeCards;
        }
    }
}

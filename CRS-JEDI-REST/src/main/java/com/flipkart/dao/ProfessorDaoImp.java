package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that implements all methods of ProfessorDaoInterface
 *
 * @Author -  Team JEDI 02
 */
public class ProfessorDaoImp implements ProfessorDaoInterface {

    private static final Logger logger = Logger.getLogger(LoginDaoImp.class);
    private static volatile ProfessorDaoImp instance = null;
    Connection connection = DBUtils.getConnection();

    private ProfessorDaoImp() {
    }

    /**
     * Returns static instance of ProfessorDaoImp class
     *
     * @return instance of ProfessorDaoImp class
     */
    public static ProfessorDaoImp getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (ProfessorDaoImp.class) {
                instance = new ProfessorDaoImp();
            }
        }
        return instance;
    }

    /**
     * Get details of professor with professor id
     *
     * @param professorId if of professor for which the details are returned
     * @return details of professor in Professor object
     */
    @Override
    public Professor getProfessor(String professorId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Professor professor = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_PROFESSOR_PROFILE_QUERY);
            preparedStatement.setString(1, professorId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    String rsProfessorId = resultSet.getString("userid");
                    String rsProfessorName = resultSet.getString("name");
                    String rsProfessorEmail = resultSet.getString("email");
                    String rsProfessorRole = resultSet.getString("role");
                    String rsProfessorDepartment = resultSet.getString("department");
                    professor = new Professor(rsProfessorId, rsProfessorName, rsProfessorEmail, rsProfessorRole, rsProfessorDepartment);
                    return professor;
                }
            } else {
                throw new UserNotFoundException("ProfessorId " + professorId + " not found.");
            }


        } catch (SQLException | UserNotFoundException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }

        }
        return professor;
    }

    /**
     * Returns list of students enrolled in a course
     *
     * @param courseid if of course for which the list of enrolled students is returned
     * @return map of studentid, student name enrolled for course
     */
    @Override
    public HashMap<String, String> getEnrolledStudentsForCourse(String courseid) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<String, String> enrolledStudentsMap = new HashMap<>();
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS_FOR_COURSE);
            preparedStatement.setString(1, courseid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String studentId = resultSet.getString("userid");
                String studentName = resultSet.getString("name");
                enrolledStudentsMap.put(studentId, studentName);
            }
            return enrolledStudentsMap;
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
        }
        return null;
    }

    /**
     * Enters grades of student for a course in database
     *
     * @param gradesOfStudents map of studentId, grade of students
     * @param courseId         id of course for which grades have to be entered
     * @return true if grades were successfully entered else false
     */
    @Override
    public boolean enterGradesOfStudents(HashMap<String, String> gradesOfStudents, String courseId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE_QUERY);
            for (Map.Entry<String, String> student : gradesOfStudents.entrySet()) {
                String studentId = student.getKey();
                String studentGrade = student.getValue();
                preparedStatement.setString(1, studentGrade);
                preparedStatement.setString(2, studentId);
                preparedStatement.setString(3, courseId);
                int updatedValues = preparedStatement.executeUpdate();
                if (updatedValues == 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }
}

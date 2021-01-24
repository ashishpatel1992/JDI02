package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that implements all methods of ProfessorDaoInterface
 */
public class ProfessorDaoImp implements ProfessorDaoInterface {

    private static Logger logger = Logger.getLogger(LoginDaoImp.class);

    private static volatile ProfessorDaoImp instance = null;

    private ProfessorDaoImp() {
    }

    /**
     * Returns static instance of ProfessorDaoImp class
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
     * @param professorId if of professor for which the details are returned
     * @return details of professor in Professor object
     */
    @Override
    public Professor getProfessor(String professorId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Professor professor = null;
        try {
            stmt = connection.prepareStatement(SQLQueriesConstants.GET_PROFESSOR_PROFILE_QUERY);
            stmt.setString(1, professorId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String rsProfessorId = rs.getString("userid");
                String rsProfessorName = rs.getString("name");
                String rsProfessorEmail = rs.getString("email");
                String rsProfessorRole = rs.getString("role");
                String rsProfessorDepartment = rs.getString("department");
                professor = new Professor(rsProfessorId, rsProfessorName, rsProfessorEmail, rsProfessorRole, rsProfessorDepartment);
                return professor;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return professor;
    }

    /**
     * Returns list of students enrolled in a course
     * @param courseid if of course for which the list of enrolled students is returned
     * @return map of studentid, student name enrolled for course
     */
    @Override
    public HashMap<String, String> getEnrolledStudentsForCourse(String courseid) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HashMap<String, String> enrolledStudentsMap = new HashMap<>();
        try {
            stmt = connection.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS_FOR_COURSE);
            stmt.setString(1, courseid);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("userid");
                String studentName = rs.getString("name");
                enrolledStudentsMap.put(studentId, studentName);
            }
            return enrolledStudentsMap;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Enters grades of student for a course in database
     * @param gradesOfStudents map of studentId, grade of students
     * @param courseId id of course for which grades have to be entered
     * @return true if grades were successfully entered else false
     */
    @Override
    public boolean enterGradesOfStudents(HashMap<String, String> gradesOfStudents, String courseId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE_QUERY);
            for (Map.Entry<String, String> student : gradesOfStudents.entrySet()) {
                String studentId = student.getKey();
                String studentGrade = student.getValue();
                stmt.setString(1, studentGrade);
                stmt.setString(2, studentId);
                stmt.setString(3, courseId);
                int updatedValues = stmt.executeUpdate();
                if (updatedValues == 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }
}

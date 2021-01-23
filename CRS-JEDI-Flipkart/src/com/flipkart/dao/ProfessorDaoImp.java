package com.flipkart.dao;

import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ProfessorDaoImp implements ProfessorDaoInterface{

    private static Logger logger = Logger.getLogger(LoginDaoImp.class);
    Connection connection = DBUtils.getConnection();

    @Override
    public HashMap<String,String> getEnrolledStudentsForCourse(String courseid) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        HashMap<String,String> enrolledStudentsMap = new HashMap<>();
        try{
            stmt = connection.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS_FOR_COURSE);
            stmt.setString(1,courseid);
            rs = stmt.executeQuery();
            while(rs.next()){
                String studentId = rs.getString("userid");
                String studentName = rs.getString("name");
                enrolledStudentsMap.put(studentId,studentName);
            }
            return enrolledStudentsMap;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean enterGradesOfStudents(HashMap<String, String> gradesOfStudents, String courseId) {
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE_QUERY);
            for(Map.Entry<String,String> student:gradesOfStudents.entrySet()){
                    String studentId = student.getKey();
                    String studentGrade = student.getValue();
                    stmt.setString(1,studentGrade);
                    stmt.setString(2,studentId);
                    stmt.setString(3,courseId);
                    int updatedValues = stmt.executeUpdate();
                    if(updatedValues==0){
                        return false;
                    }
            }
        }catch(SQLException e){
            return false;
        }finally {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
            return true;
    }
}

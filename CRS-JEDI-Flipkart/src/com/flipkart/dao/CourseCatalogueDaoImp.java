package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQlQueriesConstants;
import com.flipkart.service.CourseCatalogueOperation;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class CourseCatalogueDaoImp implements CourseCatalogueDaoInterface{
//    public static void main(String[] args) {
//        CourseCatalogueDaoImp courseCatalogueDaoImp =new CourseCatalogueDaoImp();
//        courseCatalogueDaoImp.getAllCourses();
//    }

    // note: The meaning of volatile modifier tell the compiler of java that the value of variable must never
    // be cached
    private static volatile CourseCatalogueDaoImp instance = null;

    // private constructor
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


    public ArrayList<Course> getAllCourses(){
        Connection connection = DBUtils.getConnection();
        ArrayList<Course> courseArrayList = new ArrayList<Course>();
        Statement stmt = null;
        ResultSet resultSet = null;
        try{
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(SQlQueriesConstants.GET_ALL_COURSES_QUERY);

            while(resultSet.next()){
                String courseId;
                String courseName;
                String professorId;

                courseId = resultSet.getString("courseid");
                courseName = resultSet.getString("coursename");
                // TODO: if professor is not null then fetch professor Name and return
                professorId = resultSet.getString("professorid");
//                logger.info(courseId+" "+courseName+" "+professorId);
                Course course = new Course(courseId,courseName,professorId);
                courseArrayList.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
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

//            DBUtils.closeConnection();
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }

        }
    return courseArrayList;
    }
}

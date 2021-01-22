package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//import org.apache.log4j.Logger;

public class DBUtil {

    private static Connection connection = null;

//    private static Logger logger = Logger.getLogger(DBUtil.class);

    public static Connection getConnection() {

        if (connection == null){
            try {
                String DB_URL = "jdbc:mysql://localhost/test";
                //  Database credentials
                String USER = "root";
                String PASS = "root";

//                InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("config.properties");
//                prop.load(inputStream);
//                String driver = prop.getProperty("driver");
//                String url = prop.getProperty("url");
//                String user = prop.getProperty("user");
//                String password = prop.getProperty("password");
//                Class.forName(driver);
//                connection = DriverManager.getConnection(url, user, password);

                Properties prop = new Properties();
                prop.put("user",USER);
                prop.put("password",PASS);
                connection = DriverManager.getConnection(DB_URL,prop);
            } catch (Exception e) {
//                logger.error(e.getMessage());
            }
        }
        return connection;

    }

    public static boolean closeConnection() {
        try {
            if(connection != null) {
                connection.close();
            }
        }catch (SQLException e) {
            return false;
        }
        return true;
    }


}
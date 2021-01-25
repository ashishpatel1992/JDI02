package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//import org.apache.log4j.Logger;

/**
 * Database utility class performs the following operations
 * 1. Get a connection to database
 * 2. Close a connection to database
 *
 * @Author -  Team JEDI 02
 */
public class DBUtils {

    private static Connection connection = null;

//    private static Logger logger = Logger.getLogger(DBUtil.class);

    /**
     * Returns a Connection object connected to database
     *
     * @return Connection
     */
    public static Connection getConnection() {

        if (connection == null) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
                properties.load(inputStream);
                String driver = properties.getProperty("driver");
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;

    }

    /**
     * Closes the established connection to database
     */
    public static boolean closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
package com.flipkart.dao;

import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;
import sun.reflect.annotation.ExceptionProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Class that implements all methods of NotificationDaoInterface
 *
 * @Author -  Team JEDI 02
 */
public class NotificationDaoImp implements NotificationDaoInterface {

    private static volatile NotificationDaoImp instance = null;
    private static final Logger logger = Logger.getLogger(AdminDaoImp.class);
    Connection connection = DBUtils.getConnection();

    private NotificationDaoImp() {
    }

    /**
     * Returns static instance of NotificationDaoImp class
     *
     * @return instance of NotificationDaoImp class
     */
    public static NotificationDaoImp getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (NotificationDaoImp.class) {
                instance = new NotificationDaoImp();
            }
        }
        return instance;
    }

    /**
     * Adds notification to database for a student
     *
     * @param studentId if of student for which notification has to added
     * @param message   message to enter for notification
     */
    @Override
    public void addNotification(String studentId, String message) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_NOTIFICATION_QUERY);
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, message);
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            preparedStatement.setString(3, strDate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e.getMessage());
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    /**
     * Get notifications for a student
     *
     * @param studentId if of student for which notifications have to be return
     * @return arraylist of notifications
     */
    @Override
    public ArrayList<String> getNotificationsForStudent(String studentId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> notifications = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_NOTIFICATIONS_QUERY);
            preparedStatement.setString(1, studentId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                notifications.add(resultSet.getString("date") + "\t" + resultSet.getString("message"));
            }

            return notifications;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return notifications;
    }
}

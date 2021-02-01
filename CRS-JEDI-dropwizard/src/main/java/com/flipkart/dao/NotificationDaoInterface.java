package com.flipkart.dao;

import java.util.ArrayList;

/**
 * Interface for Notifications Dao
 *
 * @Author -  Team JEDI 02
 */
public interface NotificationDaoInterface {
    /**
     * Adds notification to database for a student
     * @param studentId if of student for which notification has to added
     * @param message message to enter for notification
     */
    void addNotification(String studentId, String message);

    /**
     * Get notifications for a student
     * @param studentId if of student for which notifications have to be return
     * @return arraylist of notifications
     */
    ArrayList<String> getNotificationsForStudent(String studentId);

}

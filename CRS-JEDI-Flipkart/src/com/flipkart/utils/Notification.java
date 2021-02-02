package com.flipkart.utils;

/**
 * Handles Notification for a student
 * @Author -  Team JEDI 02
 */
public class Notification {
    String messageId;
    String studentId;
    String message;

    /**
     * Get the message ID
     * @return message ID
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Set the message ID
     * @param messageId
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets the Student ID
     * @return student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Set the Student ID
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

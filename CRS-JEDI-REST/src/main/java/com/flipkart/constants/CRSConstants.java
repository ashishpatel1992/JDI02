package com.flipkart.constants;

import java.time.format.DateTimeFormatter;

/**
 * Stores constants for the CRS System
 *
 * @Author -  Team JEDI 02
 */
public class CRSConstants {
    public static final int MIN_COURSE_REQUIREMENT = 3;
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm:ss Z");
    public static final String INVALID_USERID_OR_PASSWORD = "Invalid login credentials";
    public static final String NO_COURSE_ASSIGNED = "No course assigned!";
}

package com.flipkart.exception;

/**
 * Throws Exception when grading for all courses in which student is enrolled is not completed..
 */
public class ReportCardGenerationFailedException extends  Exception{
    String message;


    ReportCardGenerationFailedException(String message){
        this.message = message;
    }
}

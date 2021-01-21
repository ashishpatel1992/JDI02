package com.flipkart.test;

import com.flipkart.client.StudentCRSClient;

public class TestStudentMenu {
    public static void main(String[] args) {
        StudentCRSClient studentCRSClient = new StudentCRSClient("1");
        studentCRSClient.studentMenu();

//        CourseCatalogueOperation courseCatalogueOperation = new CourseCatalogueOperation();
//
//        System.out.println(courseCatalogueOperation.getCourse("201"));
    }
}

package com.flipkart.test;

import com.flipkart.client.StudentClient;
import com.flipkart.service.CourseCatalogueOperation;

public class TestStudentMenu {
    public static void main(String[] args) {
        StudentClient studentClient = new StudentClient("1");
        studentClient.studentMenu();

//        CourseCatalogueOperation courseCatalogueOperation = new CourseCatalogueOperation();
//
//        System.out.println(courseCatalogueOperation.getCourse("201"));
    }
}

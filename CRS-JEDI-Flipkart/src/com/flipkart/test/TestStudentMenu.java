package com.flipkart.test;

import com.flipkart.client.AdminCRSClient;
import com.flipkart.client.ProfessorCRSClient;
import com.flipkart.client.StudentCRSClient;
import com.flipkart.dao.*;
import com.flipkart.service.AdminOperation;

public class TestStudentMenu {
    public static void main(String[] args) {
//        StudentCRSClient studentCRSClient = new StudentCRSClient("1");
//        studentCRSClient.studentMenu();

//        CourseCatalogueOperation courseCatalogueOperation = new CourseCatalogueOperation();
//
//        System.out.println(courseCatalogueOperation.getCourse("201"));
//        LoginDaoImp loginDaoInterface = new LoginDaoImp();
//        System.out.println(loginDaoInterface.generatePassword());
//
//        AdminCRSClient adminCRSClient = new AdminCRSClient("1");
//        adminCRSClient.adminMenu();
//        new AdminCRSClient("1").adminMenu();
//        new ProfessorCRSClient("10").professorMenu();
//        new AdminCRSClient("1").adminMenu();
        new StudentCRSClient("101").studentMenu();
        System.out.println();


    }
}

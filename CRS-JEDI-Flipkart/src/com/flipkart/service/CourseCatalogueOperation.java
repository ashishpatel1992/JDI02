package com.flipkart.service;

import com.flipkart.bean.Course;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CourseCatalogueOperation implements CourseCatalogueInterface {
    private static Logger logger = Logger.getLogger(CourseCatalogueOperation.class);
    ArrayList<Course> courseList = new ArrayList<Course>();

    public CourseCatalogueOperation(){
        // TODO: Remove Dummy Data once connected to DB

        ArrayList<String> courseName = new ArrayList<String>(){
            {
                add("OS");
                add("DBMS");
                add("ML");
                add("AI");
            }
        };
        for(int i=0;i<courseName.size();i++){
            Course course = new Course(String.valueOf(200+i+1), courseName.get(i));
            courseList.add(course);

        }
    }

    @Override
    public ArrayList<Course> getCourseList() {
        logger.info("ViewCourses");
        return courseList;
    }



    @Override
    public boolean addCourse(String courseId, String courseName) {
        logger.info("Add Course");
        Course course = new Course(courseId,courseName);
        courseList.add(course);
        // TODO: We need to create professor before assigning him a course
        // TODO: a course will be hidden if no professor is assigned
        return true;
    }

    @Override
    public Course getCourse(String courseId) {
        Course selection = null;
        for(Course course : courseList){
//            System.out.println(course.getId()+" == "+courseId);
//            System.out.println(course.getName());

//            System.out.println(course.getId().getClass()+" "+courseId.getClass());
            // TODO : Why Only working with equalsIgnoreCase
//            System.out.println(courseId.equalsIgnoreCase(course.getId()));
            if(course.getId().equalsIgnoreCase(courseId)){
//                System.out.println(course.getName());
                selection = course;
            }
        }
        return selection;
    }

    @Override
    public boolean deleteCourse(String courseId) {
        // fetch object of that course id
        boolean flag = false;
        for(int i=0;i<courseList.size();i++){
            if(courseList.get(i).getId() == courseId){
                courseList.remove(i);
                flag = true;
                break;
            }
        }

        logger.info("Delete Course");
        return flag;
    }

    @Override
    public boolean assignProfessor(String courseId) {
        // TODO: later on call to doa
//        Course course = null;
//        for(int i=0;i<courseList.size();i++){
//            if(courseList.get(i).getId() == courseId){
//
//                course = courseList.get(i);
//                break;
//            }
//        }
//        logger.info("Assign Professor");
//        return course;
        return true;
    }
}

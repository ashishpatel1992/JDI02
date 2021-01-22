package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.dao.CourseCatalogueDaoImp;
import com.flipkart.dao.CourseCatalogueDaoInterface;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the operation of CourseCatalogue
 */
public class CourseCatalogueOperation implements CourseCatalogueInterface {




    private static volatile CourseCatalogueOperation instance = null;


    public static CourseCatalogueOperation getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (CourseCatalogueOperation.class) {
                instance = new CourseCatalogueOperation();
            }
        }
        return instance;
    }

    private static Logger logger = Logger.getLogger(CourseCatalogueOperation.class);

    ArrayList<Course> courseList = new ArrayList<Course>();

    public CourseCatalogueOperation(){
//        CourseCatalogueDaoInterface courseCatalogueDaoInterface = new CourseCatalogueDaoImp();
        CourseCatalogueDaoInterface courseCatalogueDaoInterface = CourseCatalogueDaoImp.getInstance();
        courseList = courseCatalogueDaoInterface.getAllCourses();

        // TODO: Remove Dummy Data once connected to DB

//        ArrayList<String> courseName = new ArrayList<String>(){
//            {
//                add("OS");
//                add("DBMS");
//                add("ML");
//                add("AI");
//            }
//        };
//        for(int i=0;i<courseName.size();i++){
//            Course course = new Course(String.valueOf(200+i+1), courseName.get(i));
//            courseList.add(course);
//
//        }
    }

    /**
     * returns the list of all courses
     * @return
     */
    @Override
    public ArrayList<Course> getCourseList() {
        logger.info("ViewCourses");
        return courseList;
    }


    /**
     * creates a new course and add to course list and further add to database
     * @param courseId
     * @param courseName
     * @return
     */
    @Override
    public boolean addCourse(String courseId, String courseName,String professorId) {
        logger.info("Add Course");
        Course course = new Course(courseId,courseName,professorId);
        courseList.add(course);
        //TODO: add course list to database
        // TODO: We need to create professor before assigning him a course
        // TODO: a course will be hidden if no professor is assigned
        return true;
    }

    /**
     * Returns the course along with its details
     * @param courseId
     * @return
     */
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

    /**
     * Delete a specific course
     * @param courseId
     * @return
     */
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

    /**
     * Assign a professor to a course
     * @param courseId
     * @return
     */
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

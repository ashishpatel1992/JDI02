package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoImp;
import com.flipkart.dao.ProfessorDaoInterface;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to perform the following professor operations
 * 1. get course details
 * 2. get list of enrolled students
 * 3. grade enrolled students
 *
 * @Author -  Team JEDI 02
 */
public class ProfessorOperation implements ProfessorInterface {
    private static Logger logger = Logger.getLogger(ProfessorOperation.class);
    String professorId;

    /**
     * Constructor to set professor id
     *
     * @param professorId professor ID
     */
    public ProfessorOperation(String professorId) {
        this.professorId = professorId;
    }

    /**
     * Get Professor details
     *
     * @return Professor details
     */
    public Professor getProfessor() {
        return ProfessorDaoImp.getInstance().getProfessor(professorId);
    }

    /**
     * Returns the course details
     *
     * @return Course
     */
    @Override
    public Course getCourseDetail() {
        try {
            ArrayList<Course> courseArrayList = new CourseCatalogueOperation().getCourseList();

            boolean success = false;
            for (Course course : courseArrayList) {
                if (course.getProfessorId().equals(professorId)) {

                    return course;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * Gets list of enrolled students
     *
     * @return Array List of Students
     */
    @Override
    public HashMap<String, String> getEnrolledStudents() {

        Course course = getCourseDetail();
        HashMap<String, String> studentsEnrolledList = null;

        if (course == null) {
            return studentsEnrolledList;
        }
        String courseId = course.getId();

        studentsEnrolledList = ProfessorDaoImp.getInstance().getEnrolledStudentsForCourse(courseId);
        return studentsEnrolledList;
    }

    /**
     * Grade students
     *
     * @param gradesOfStudents Hashmap containing Student Id and grades.
     * @return true if graded successfully
     */
    @Override
    public boolean gradeStudent(HashMap<String, String> gradesOfStudents) {

        Course course = getCourseDetail();
        if (course != null) {
            String courseId = course.getId();

            return ProfessorDaoImp.getInstance().enterGradesOfStudents(gradesOfStudents, courseId);
        } else {
            // TODO: Throw GradeAssignmentException
            return false;
        }

    }
}

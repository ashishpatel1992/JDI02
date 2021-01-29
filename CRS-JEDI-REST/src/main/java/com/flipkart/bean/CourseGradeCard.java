package com.flipkart.bean;

/**
 * CourseGradeCard - bean class for CoursGradeing
 * @Author -  Team JEDI 02
 */
public class CourseGradeCard {
    Course course;
    Student student;
    String grade;

    /**
     * Creates a Course Grade Card
     * @param course object
     * @param student object
     * @param grade of student in a given course
     */
    public CourseGradeCard(Course course, Student student, String grade) {
        this.course = course;
        this.student = student;
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

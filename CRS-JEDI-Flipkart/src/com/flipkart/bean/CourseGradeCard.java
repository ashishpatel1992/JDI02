package com.flipkart.bean;

public class CourseGradeCard {
    Course course;
    Student student;
    String grade;

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

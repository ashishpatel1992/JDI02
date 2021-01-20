package com.flipkart.bean;

import java.util.ArrayList;

public class RegisteredCourses
{
    ArrayList<Course> selectedCourses;

    public ArrayList<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(ArrayList<Course> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }
}

package com.isu.reggieplan.Schedule;

import java.util.*;

public class Semester {
    
    private List<Course> courses;
    private int year;
    private String season;



    public Semester(int year, String season){
        this.year = year;
        this.season = season;
    }

    public Semester(int year, String season, Course course){
        this.year = year;
        this.season = season;
        this.courses.add(course);
    }

    public Semester(int year, String season, List<Course> courses){
        this.year = year;
        this.season = season;
        this.courses.addAll(courses);
    }

    public int getCredits(){
        int credits = 0;
        for(Course course : courses){
            credits += course.getCredit_hours();
        }
        return credits;
    }

    public List<Course> getCourses(){
        return this.courses;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSeason() {
        return this.season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

}

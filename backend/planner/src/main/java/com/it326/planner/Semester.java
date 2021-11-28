package com.it326.planner;

import java.util.Date;
import java.util.List;
import com.it326.planner.*;

public class Semester {
    private int maxCreds;
    private Date semesterStart, semesterEnd;
    private List<Course> courses;
    private boolean completed;

    public Semester(){
        maxCreds = 15;
        semesterStart = null;
        semesterEnd = null;
    }

    public void addCourse(Course c){
        courses.add(c);
    }

    public void removeCourse(Course c){
        courses.remove(c);
    }

    //Getters and Setters

    public int getMaxCreds(){
        return maxCreds;
    }

    public void setMaxCreds(int maxCreds){
        this.maxCreds = maxCreds;
    }

    public Date getSemesterStart(){
        return semesterStart;
    }

    public Date setSemesterStart(Date semesterStart){
        return semesterStart;
    }

    public Date getSemesterEnd(){
        return semesterEnd;
    }

    public Date setSemesterEnd(Date semesterEnd){
        return semesterEnd;
    }

    public boolean isCompleted(){
        return completed;
    }

    public void setCompleted(boolean compl){
        completed = compl;
    }

    public Course getCourses(int index){
        return courses.get(index);
    }
}
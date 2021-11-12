package com.it326.planner;

import java.util.Date;
import java.util.List;
import com.it326.planner.*;

public class Course{
    private String department, teacherName, description;
    private int courseNumber;

    public Course(){
        department = "";
        teacherName = "";
        description = "";
        courseNumber = 0;
    }

    //Getters and Setters

    public int getDepartment(){
        return department;
    }

    public void setDepartment(int department){
        this.department = department;
    }

    public int getTeacherName(){
        return teacherName;
    }

    public void setTeacherName(int teacherName){
        this.teacherName = teacherName;
    }
    
    public int getDescription(){
        return description;
    }

    public void setDescription(int description){
        this.description = description;
    }

    public int getcourseNumber(){
        return courseNumber;
    }

    public void setcourseNumber(int courseNumber){
        this.courseNumber = courseNumber;
    }
}
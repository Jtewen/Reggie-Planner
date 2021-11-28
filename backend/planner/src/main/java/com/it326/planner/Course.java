package com.it326.planner;

import java.util.Date;
import java.util.List;
import com.it326.planner.*;

public class Course{
    private String department, teacherName, description;
    private int courseNumber;
    private StudentInfo studentInfo;
    private List<Course> preReq;


    public Course(){
        department = "";
        teacherName = "";
        description = "";
        courseNumber = 0;
        studentInfo = new StudentInfo();
    }

    public Course(String dept, String teach, String desc, int num){
        department = "";
        teacherName = "";
        description = "";
        courseNumber = 0;
        studentInfo = new StudentInfo();
    }


    //Getters and Setters

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public String getTeacherName(){
        return teacherName;
    }

    public void setTeacherName(String teacherName){
        this.teacherName = teacherName;
    }
    
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getcourseNumber(){
        return courseNumber;
    }

    public void setcourseNumber(int courseNumber){
        this.courseNumber = courseNumber;
    }

    public void addPreReq(Course c){
        preReq.add(c);
    }

}
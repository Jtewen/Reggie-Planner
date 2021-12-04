package com.it326;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Comparable<Course>, Serializable{
    private String department, description;
    private int courseNumber, credits;
    private String[] tempReqs;
    private List<Course> preReqs = new ArrayList<Course>();
    private boolean completed;


    public Course(){
        department = "";
        description = "";
        courseNumber = 0;
        new StudentInfo();
        preReqs = new ArrayList<Course>();
    }

    public Course(String dept, int num, String name, int creds, String pre, String desc){
        department = dept;
        description = desc;
        courseNumber = num;
        credits = creds;
        tempReqs = pre.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").replace("or", ",").split(",");
        new StudentInfo();
    }


    //Getters and Setters
    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
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
    
    public boolean getCmpleted(){
        return completed;
    }

    public void setCmpleted(Boolean completed){
        this.completed = completed;
    }

    public void setcourseNumber(int courseNumber){
        this.courseNumber = courseNumber;
    }

    public void addPreReq(Course c){
        preReqs.add(c);
    }

    public String[] getTempPreReqs(){
        return tempReqs;
    }

    public List<Course> getPreReqs(){
        return preReqs;
    }

    public int getCredits(){
        return credits;
    }

    public String toString(){
        return department + " " + courseNumber;
    }

    @Override
    public int compareTo(Course o) {
        return this.getcourseNumber()-o.getcourseNumber();
    }

}
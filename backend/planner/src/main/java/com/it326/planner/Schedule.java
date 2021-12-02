package com.it326.planner;

import java.util.*;
import java.util.List;
import com.it326.planner.*;
import com.it326.planner.Majors.Major;

public class Schedule {
    Major major;
    String minor;
    int externalCreds;
    Date graduation;
    List<Semester> semesters = new ArrayList<Semester>();
    List<Course> unassignedCourses = new ArrayList<Course>();

    public Schedule(){
        
    }


    public void addSemester(){
        this.semesters.add(new Semester());
    }


    public void addSemester(Semester s){
        this.semesters.add(s);
    }

    public void removeSemester(Semester semester){

    }

    public void addExternalCreds(int creds){
        this.setExternalCreds(this.getExternalCreds()+creds);
    }

    public void addMinor(String minor){

    }

    public void removeMinor(){

    }

    //Getters and Setters

    public Date getGraduation() {
        return this.graduation;
    }

    public void setGraduation(Date graduation) {
        this.graduation = graduation;
    }

    public int getExternalCreds() {
		return this.externalCreds;
	}

    public void setExternalCreds(int externalCreds) {
        this.externalCreds = externalCreds;
    }

    public Major getMajor() {
		return this.major;
	}

    public void setMajor(Major major) {
        this.major = major;
        this.unassignedCourses = major.getRequiredCourse();
    }

    public String getMinor() {
		return this.minor;
	}

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public Semester getSemester(int index){
        return semesters.get(index);
    }

    public List<Course> getUnassignedCourses(){
        return unassignedCourses;
    }


}

package com.it326;

import java.util.*;
import java.util.List;
import com.it326.Majors.Major;

public class Schedule {
    String name;
    Major major;
    String minor;
    int externalCreds;
    Date graduation;
    List<Semester> semesters = new ArrayList<Semester>();
    List<Course> allCourses = new ArrayList<Course>();
    List<Course> unassignedCourses = new ArrayList<Course>();

    public Schedule(){
        
    }

    public Schedule(String n){
        name = n;
    }


    public void addSemester(){
        this.semesters.add(new Semester());
    }

    public void addSemester(String seas, int year){
        this.semesters.add(new Semester(seas, year));
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

    public void calculateSchedule(){
        
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
        this.allCourses = this.unassignedCourses;
    }

    public String getMinor() {
		return this.minor;
	}

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public List<Semester> getSemesters(){
        Collections.sort(semesters);
        return semesters;
    }

    public List<Course> getUnassignedCourses(){
        return unassignedCourses;
    }

    public void setUnassignedCourses(List<Course> cs){
        unassignedCourses = cs;
    }

    public void setName(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return getName();
    }

    public void addCourse(Semester s, Course c){
        if(s.addCourse(c))
            unassignedCourses.remove(c);
    }

    public void removeCourse(Semester s, Course c){
        s.removeCourse(c);
        System.out.println("Removed "+c);
        unassignedCourses.add(c);
        Collections.sort(unassignedCourses);
        System.out.println(unassignedCourses);
    }

}

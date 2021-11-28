package com.it326.planner;

import java.util.Date;
import java.util.List;
import com.it326.planner.*;

public class Schedule {
    String major, minor;
    int externalCreds;
    Date graduation;
    List<Semester> semesters;

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

    public String getMajor() {
		return this.major;
	}

    public void setMajor(String major) {
        this.major = major;
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

}

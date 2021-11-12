package com.it326.planner;

import java.util.Date;
import java.util.List;
import com.it326.planner.*;

public class StudentInfo{
    private int grade;
    private String notes;

    public StudentInfo(){
        grade = 0;
        notes = "";
    }
    
    //Getters and Setters

    public int getGrade(){
        return grade;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }

    public int getNotes(){
        return notes;
    }

    public void setNotes(int notes){
        this.notes = notes;
    }
}
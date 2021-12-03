package com.it326;

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

    public String getNotes(){
        return notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }
}
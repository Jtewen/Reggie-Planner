package com.it326;

import java.io.Serializable;
import java.util.*;
import java.util.List;
import com.it326.Majors.Major;
import com.it326.Majors.Minor;

public class Schedule implements Serializable {
    String name;
    Major major;
    Minor minor;
    int externalCreds;
    Date graduation;
    List<Semester> semesters = new ArrayList<Semester>();
    List<Course> allCourses = new ArrayList<Course>();
    List<Course> unassignedCourses = new ArrayList<Course>();

    public Schedule() {

    }

    public Schedule(String n) {
        name = n;
    }

    public Semester addSemester() {
        int year = 2020 + java.util.Calendar.YEAR;
        String seas = "";
        if (semesters.isEmpty()) {
            if (java.util.Calendar.MONTH < 7)
                seas = "Fall";
            else {
                seas = "Spring";
                year++;
            }
        } else {
            if (semesters.get(semesters.size() - 1).getSeason() == "Spring") {
                seas = "Fall";
                year = semesters.get(semesters.size() - 1).getYear() + 1;
            } else {
                seas = "Spring";
                year = semesters.get(semesters.size() - 1).getYear();
            }
        }
        Semester tempSem = new Semester(seas, year);
        this.semesters.add(tempSem);
        Collections.sort(semesters);
        return tempSem;
    }

    public Semester addSemester(boolean summer) {
        int year = 2020 + java.util.Calendar.YEAR;
        String seas = "";
        if(summer && !semesters.isEmpty() && semesters.get(semesters.size()-1).getSeason()=="Spring"){
            semesters.add(new Semester("Summer", semesters.get(semesters.size()-1).getYear()));
            semesters.get(semesters.size()-1).setMaxCreds(6);
            return semesters.get(semesters.size()-1);
        }
        if (semesters.isEmpty()) {
            if (java.util.Calendar.MONTH < 7)
                seas = "Fall";
            else {
                seas = "Spring";
                year++;
            }
        } else {
            if (semesters.get(semesters.size() - 1).getSeason() == "Spring") {
                seas = "Fall";
                year = semesters.get(semesters.size() - 1).getYear() + 1;
            }
            else if(semesters.get(semesters.size() - 1).getSeason() == "Summer") {
                seas = "Fall";
                year = semesters.get(semesters.size() - 1).getYear() + 1;
            }
            else {
                seas = "Spring";
                year = semesters.get(semesters.size() - 1).getYear();
            }
        }
        Semester tempSem = new Semester(seas, year);
        this.semesters.add(tempSem);
        Collections.sort(semesters);
        return tempSem;
    }

    public Semester addSemester(boolean summer, int index) {
        String seas;
        String prevSeas = semesters.get(index).getSeason().trim();
        int year = semesters.get(index).getYear();
        System.out.println(semesters.get(index).getSeason());
        switch(prevSeas){
            case "Fall": seas = "Spring";break;
            case "Spring": if(summer){seas = "Summer";break;}else{seas = "Fall";break;}
            case "Summer": seas = "Fall";break;
            default: seas = "Fall";
        }
        Semester tempSem = new Semester(seas, year);
        this.semesters.add(tempSem);
        Collections.sort(semesters);
        return tempSem;
    }

    public void clearSchedule(){
        for(int i = semesters.size()-1; i>=0; i--){
            removeSemester(semesters.get(i));
        }
    }

    public Semester addSemester(String seas, int year) {
        Semester tempSem = new Semester(seas, year);
        this.semesters.add(tempSem);
        Collections.sort(semesters);
        return tempSem;
    }

    public void addSemester(Semester s){
        semesters.add(s);
    }


    public void removeSemester(Semester semester) {
        for(int i = semester.getCourses().size()-1; i>=0; i--){
            removeCourse(semester, semester.getCourses().get(i));
        }
        semesters.remove(semester);
    }

    public void addExternalCreds(int creds) {
        this.setExternalCreds(this.getExternalCreds() + creds);
    }

    public void addMinor(String minor) {

    }

    public void removeMinor() {

    }

    public void calculateSchedule() {

    }

    // Getters and Setters

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
        this.unassignedCourses.clear();
        this.unassignedCourses.addAll(major.getRequiredCourse());
        if(minor!=null)
            this.unassignedCourses.addAll(minor.getRequiredCourse());
        this.allCourses = this.unassignedCourses;
    }

    public Minor getMinor() {
        return this.minor;
    }

    public void setMinor(Minor minor) {
        this.minor = minor;
        this.unassignedCourses.clear();
        if(major!=null)
            this.unassignedCourses.addAll(major.getRequiredCourse());
        this.unassignedCourses.addAll(minor.getRequiredCourse());
        this.allCourses = this.unassignedCourses;
    }

    public List<Semester> getSemesters() {
        Collections.sort(semesters);
        return semesters;
    }

    public List<Course> getUnassignedCourses() {
        return unassignedCourses;
    }

    public void setUnassignedCourses(List<Course> cs) {
        unassignedCourses = cs;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return getName();
    }

    public boolean addCourse(Semester s, Course c) {
        if (s.addCourse(c)){
            unassignedCourses.remove(c);
            return true;
        }
        return false;
    }

    public boolean addCourseExplicit(Semester s, Course c) {
        if (verifyCourse(s, c) && s.addCourse(c)){
            c.setCmpleted(true);
            unassignedCourses.remove(c);
            return true;
        }
        return false;

    }

    public void removeCourse(Semester s, Course c) {
        s.removeCourse(c);
        c.setCmpleted(false);
        unassignedCourses.add(c);
        Collections.sort(unassignedCourses);
    }

    public boolean verifyCourse(Semester s, Course c){

        boolean flag = false;
        int index = this.getSemesters().indexOf(s);
        for(Course pre : c.getPreReqs()){
            //for(Semester sem : this.getSemesters()){
            for(int i = 0; i<this.getSemesters().size(); i++){
                if(this.getSemesters().indexOf(getSemesters().get(i))>=index)
                    break;
                if(getSemesters().get(i).getCourses().contains(pre))
                    flag = true;
            }
            if(!flag)
                return false;
        }
        return true;
    }

}

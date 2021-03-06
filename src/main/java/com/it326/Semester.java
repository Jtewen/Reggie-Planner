package com.it326;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Semester implements Comparable<Semester>, Serializable {
    private int maxCreds;
    private int year;
    private String season;
    private List<Course> courses = new ArrayList<Course>();
    private boolean completed;

    public Semester() {
        maxCreds = 15;
    }

    public Semester(String seas, int y) {
        maxCreds = 15;
        season = seas;
        year = y;
    }

    public boolean addCourse(Course c) {
        int credsLeft = maxCreds - getCurrentCredits();
        if (credsLeft - c.getCredits() < 0)
            return false;
        courses.add(c);
        return true;
    }

    public void removeCourse(Course c) {
        courses.remove(c);
    }

    // Getters and Setters

    public int getMaxCreds() {
        return maxCreds;
    }

    public void setMaxCreds(int maxCreds) {
        this.maxCreds = maxCreds;
    }

    public int getCurrentCredits() {
        int temp = 0;
        for (Course crs : courses) {
            temp += crs.getCredits();
        }
        return temp;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean compl) {
        completed = compl;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setYear(int y) {
        year = y;
    }

    public int getYear() {
        return year;
    }

    public void setSeason(String s) {
        season = s;
    }

    public String getSeason() {
        return season;
    }

    public String toString() {
        return season + " " + year + " Semester";
    }

    @Override
    public int compareTo(Semester o) {
        int thisSeason = 0;
        int oseason = 0;
        switch (this.season) {
            case "Fall":
                thisSeason = 2;
            case "Spring":
                thisSeason = 0;
            case "Summer":
                thisSeason = 1;
        }
        switch (o.season) {
            case "Fall":
                oseason = 2;
            case "Spring":
                oseason = 0;
            case "Summer":
                oseason = 1;
        }
        return (this.year * 1000 - o.year * 1000) + (thisSeason - oseason);
    }

}
package com.it326.Majors;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.it326.Course;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

public class Major implements Serializable {

    String major;
    List<Course> requiredCourses = new ArrayList<Course>();

    // This is the most terrifying spaghetti constructor
    // Reads the data from ITlist.csv and populates a "tree" of courses that are
    // bound by the requiredCourses list.
    public Major(String mjr) {
        try {
            major = mjr;
            String path = getPath(mjr);
            Reader reader = Files.newBufferedReader(Paths.get(path));
            try (CSVReader csvReader = new CSVReader(reader)) {
                List<String[]> dataSet = csvReader.readAll();
                dataSet.remove(0);
                for (String[] data : dataSet) {
                    requiredCourses.add(new Course(data[0].split(" ")[0].replace("\"", ""),
                            Integer.parseInt(data[0].split(" ")[1].replace("\"", "")), data[1].replace("\"", ""),
                            Integer.parseInt(data[2].replace("\"", "")), data[3].replace("\"", ""),
                            data[4].replace("\"", "")));

                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (CsvException e) {
                e.printStackTrace();
            }
            for (Course c : requiredCourses) {
                for (String id : c.getTempPreReqs()) {
                    for (Course c2 : requiredCourses) {
                        if (c2.toString() == id)
                            c.addPreReq(c2);
                    }
                }
            }

            addParents(requiredCourses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath(String mjr){
        switch(mjr){
            case "Computer Science": return "src/main/java/com/it326/Majors/ITlist.csv";
            case "Cyber Security": return "src/main/java/com/it326/Majors/CClist.csv";
            default: return null;
        }
    }


    // Very inefficiently calculates all of the prereqs based on the strings grabbed
    // from the csv
    public void addParents(List<Course> nodes) {
        for (Course node : nodes) {
            for (Course child : nodes) {
                for (String name : child.getTempPreReqs()) {
                    if ((node.getDepartment() + node.getcourseNumber()).equals(name)) {
                        child.addPreReq(node);
                    }
                }
            }
        }
    }

    public List<Course> getRequiredCourse() {
        return requiredCourses;
    }

    public int getTotalMajorCredits() {
        int sum = 0;
        for (Course c : requiredCourses) {
            sum += c.getCredits();
        }

        return sum;
    }

    public String getMajorName(){
        return major;
    }

    public String toString(){
        return major;
    }
}

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

public class Minor implements Serializable {

    String minor;
    ArrayList<String> minorNames = new ArrayList<String>();
    List<Course> requiredCourses = new ArrayList<Course>();

    // This is the most terrifying spaghetti constructor
    // Reads the data from ITlist.csv and populates a "tree" of courses that are
    // bound by the requiredCourses list.
    public Minor(String mnr) {

        try {
            minor = mnr;
            String path = getPath(mnr);
            if(path=="None"){
                return;
            }
            Reader reader = Files.newBufferedReader(Paths.get(path));
            try (CSVReader csvReader = new CSVReader(reader)) {
                List<String[]> dataSet = csvReader.readAll();
                dataSet.remove(0);
                for (String[] data : dataSet) {
                    if(isNumeric(data[0].split(" ")[1].replace("\"", ""))){
                        requiredCourses.add(new Course(data[0].split(" ")[0].replace("\"", ""),
                        Integer.parseInt(data[0].split(" ")[1].replace("\"", "")),
                        data[1].replace("\"", ""),
                        Integer.parseInt(data[2].replace("\"", "")),
                        data[3].replace("\"", ""),
                        data[4].replace("\"", "")));
                    }
                    else{
                        requiredCourses.add(new Course(data[0].replace("\"", ""),
                        999,
                        data[1].replace("\"", ""),
                        Integer.parseInt(data[2].replace("\"", "")),
                        data[3].replace("\"", ""),
                        data[4].replace("\"", "")));
                    }

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
        for(Course c : requiredCourses){
            System.out.println(c.getPreReqs());
        }
    }

    public String getPath(String mjr){
        switch(mjr){
            case "Technology": return "src/main/java/com/it326/Majors/techminorlist.csv";
            case "Mathematics": return "src/main/java/com/it326/Majors/mathminorlist.csv";
            case "None": return "None";
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

    public ArrayList<String> getMinorNames(){
        return minorNames;
    }

    public String toString(){
        return minor;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

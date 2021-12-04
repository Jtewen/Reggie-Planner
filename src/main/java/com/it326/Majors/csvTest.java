package com.it326.Majors;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.it326.Course;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;



public class csvTest {


    public static void main(String[] args){
        List<Course> requiredCourses = new ArrayList<Course>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/com/it326/Majors/CSlist.csv"));
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
                        000,
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
        System.out.println(requiredCourses);

        System.out.println(requiredCourses.get(5).getPreReqs());
    }

    public static void addParents(List<Course> nodes) {
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

    public static boolean isNumeric(String strNum) {
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

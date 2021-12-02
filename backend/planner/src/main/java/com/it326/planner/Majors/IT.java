package com.it326.planner.Majors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.it326.planner.Course;
import com.opencsv.CSVReader;

import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;

public class IT implements Major{

    List<Course> requiredCourses = new ArrayList<Course>();
    public IT(){
        try {
            Reader reader = Files.newBufferedReader(Paths.get("backend/planner/src/main/java/com/it326/planner/Majors/ITlist.csv"));
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> dataSet = csvReader.readAll();
            dataSet.remove(0);
            for(String[] data : dataSet){
                requiredCourses.add(new Course(data[0].split(" ")[0].replace("\"", ""), Integer.parseInt(data[0].split(" ")[1].replace("\"", "")), data[1].replace("\"", ""), Integer.parseInt(data[2].replace("\"", "")), data[3].replace("\"", ""), data[4].replace("\"", "")));

            }
            for(Course c : requiredCourses){
                for(String id : c.getTempPreReqs()){
                    for(Course c2 : requiredCourses){
                        if(c2.getDepartment()+c2.getcourseNumber() == id)
                            c.addPreReq(c2);
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> getRequiredCourse() {
        return requiredCourses;
    }

    @Override
    public int getTotalMajorCredits() {
        // TODO Auto-generated method stub
        return 0;
    }

    
}

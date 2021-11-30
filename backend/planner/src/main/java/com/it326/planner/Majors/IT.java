package com.it326.planner.Majors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.it326.planner.Course;

public class IT implements Major{

    List<Course> requiredCourses;
    public IT(){
        try {
            Scanner sc = new Scanner(new File("ITlist.csv"));
            sc.useDelimiter(",");
            while(sc.hasNext()){
                String[] temp = sc.next().split(" ");
                requiredCourses.add(new Course(temp[0], Integer.parseInt(temp[1]),sc.next(), Integer.parseInt(sc.next()), sc.next(), sc.next()));
            }
        } catch (FileNotFoundException e) {
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

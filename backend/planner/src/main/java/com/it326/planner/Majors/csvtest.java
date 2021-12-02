package com.it326.planner.Majors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.it326.planner.Course;
import com.opencsv.CSVReader;

public class csvtest {
    public static void main(String[] args){
        Major test = new IT();
        List<Course> courseList = test.getRequiredCourse();

        System.out.println(courseList.get(6).getPreReqs().get(0).getcourseNumber());
    }
}

package com.it326.Majors;

import java.util.List;
import com.it326.Course;

public class csvtest {
    public static void main(String[] args){
        Major test = new IT();
        List<Course> courseList = test.getRequiredCourse();

        System.out.println(courseList.get(6).getPreReqs().get(0).getcourseNumber());
    }
}

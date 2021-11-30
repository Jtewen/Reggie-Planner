package com.it326.planner;

import java.util.List;

public class CS implements Major{

    List<Course> requiredCourses;

    

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

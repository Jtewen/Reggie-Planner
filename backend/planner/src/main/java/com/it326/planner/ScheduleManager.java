package com.it326.planner;

import java.util.Date;
import java.util.List;
import com.it326.planner.*;

public class ScheduleManager{
    List<Schedule> schedules;
    List<Course> unassignedCourses;

    public ScheduleManager(){
        
    }

    public void addSchedule(){

        schedules.add(new Schedule());
    }

    public void calculateSchedule(int index, int ver){

        
    }

    public void calculateSchedule(Schedule sched, int ver){

    }

    public void filterClass(){

    }

    public void getMajorProgress(){

    }

    public void getMinorProgress(){

    }

    public void downloadPlan(){

    }


    public Schedule getSchedule(int index){
        return schedules.get(index);
    }

}
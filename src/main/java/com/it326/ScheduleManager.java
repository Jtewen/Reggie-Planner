package com.it326;

import java.util.*;

public class ScheduleManager{
    List<Schedule> schedules = new ArrayList<Schedule>();

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
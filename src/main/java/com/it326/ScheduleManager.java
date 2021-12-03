package com.it326;

import java.util.*;

public class ScheduleManager{
    List<Schedule> schedules = new ArrayList<Schedule>();
    String notes;

    public ScheduleManager(){
        
    }

    public void addSchedule(String n){

        schedules.add(new Schedule(n));
    }

    public void calculateSchedule(Schedule s){
        boolean flag = true;
        boolean readyToAdd = true;
        List<Course> temp = new ArrayList<Course>();
        for(Course c : s.getUnassignedCourses()){
            temp.add(c);
        }
        while(!temp.isEmpty() && flag){
            for(Course c : s.getUnassignedCourses()){
                for(Semester sem : s.getSemesters()){
                    for(Course pre : c.getPreReqs()){
                        //if all prereqs are assigned
                        if(s.getUnassignedCourses().contains(pre)){
                            readyToAdd = false;
                        }
                    }
                    System.out.println(c);
                    System.out.println(sem);
                    if(readyToAdd && sem.addCourse(c)){
                        temp.remove(c);
                        break;
                    }

                }
                if(temp.contains(c)){
                    System.out.println("Not enough semesters to fit");
                    flag = false;
                    break;
                }
            }
        }
        s.setUnassignedCourses(temp);
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


    public List<Schedule> getSchedules(){
        return schedules;
    }

    public void saveNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return this.notes;
	}

}
package com.it326;

import java.util.*;

public class ScheduleManager{
    Schedule sched;
    String notes;

    public ScheduleManager(){
        sched = new Schedule();
    }

    public void calculateSchedule(Schedule s){
        List<Course> temp = new ArrayList<Course>();
        for(Course c : s.getUnassignedCourses()){
            temp.add(c);
        }
        while(!temp.isEmpty()){
            for(Course c : s.getUnassignedCourses()){
                for(Semester sem : s.getSemesters()){
                    boolean readyToAdd = true;
                    for(Course pre : c.getPreReqs()){
                        //if all prereqs are assigned
                        if(temp.contains(pre)){
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
                    Semester tempSem = sched.addSemester();
                    tempSem.addCourse(c);
                    temp.remove(c);
                }
            }
            s.setUnassignedCourses(temp);
        }
        
    }

    public void clearPlanning()
    {
        for(Semester s : sched.getSemesters()){
            for(Course c : s.getCourses()){
                if(!c.getCmpleted())
                    s.removeCourse(c);
            }
        }
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


    public Schedule getSchedule(){
        return sched;
    }

    public void saveNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return this.notes;
	}

}
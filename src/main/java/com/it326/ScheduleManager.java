package com.it326;

import java.io.Serializable;
import java.util.*;

public class ScheduleManager implements Serializable {
    Schedule sched;
    String notes;

    public ScheduleManager() {
        sched = new Schedule();
    }

    //public void calculateAllSchedule(Schedule s) {
    //    clearPlanning();
    //    List<Course> temp = new ArrayList<Course>();
    //    for (Course c : s.getUnassignedCourses()) {
    //        temp.add(c);
    //    }
    //    while (!temp.isEmpty()) {
    //        if(s.getSemesters().size() < 1)
    //        {
    //            sched.addSemester();
    //        }
    //        //for (Course c : s.getUnassignedCourses()) {
    //        for (int i = 0; i< s.getUnassignedCourses().size(); i++) {
    //            boolean readyToAdd = true;
    //            for (Course pre : s.getUnassignedCourses().get(i).getPreReqs()) {
    //                // if all prereqs are assigned
    //                if (temp.contains(pre)) {
    //                    readyToAdd = false;
    //                }
    //            }
    //            if(readyToAdd){
    //                for (Semester sem : s.getSemesters()) {
    //                    readyToAdd = true;
    //                    for (Course pre : s.getUnassignedCourses().get(i).getPreReqs()) {
    //                        // if all prereqs are assigned
    //                        if(sem.getCourses().contains(pre)){
    //                            readyToAdd = false;
    //                            break;
    //                        }
    //                    }
    //                    if (readyToAdd && sem.addCourse(s.getUnassignedCourses().get(i))) {
    //                        temp.remove(s.getUnassignedCourses().get(i));
    //                        readyToAdd = false;
    //                        break;
    //                    }
    //                }
    //            }
    //            if(readyToAdd)
    //            {
    //                Semester tempSem = sched.addSemester();
    //                tempSem.addCourse(s.getUnassignedCourses().get(i));
    //                temp.remove(s.getUnassignedCourses().get(i));
    //            }
    //        }
    //        s.setUnassignedCourses(temp);
    //    }
    //    System.out.println(s.getUnassignedCourses());
//
    //}

    public void calculateAllSchedule(Schedule s){
        clearPlanning();
        List<Course> temp = new ArrayList<Course>();
        for (Course c : s.getUnassignedCourses()) {
            temp.add(c);
        }
        while(! s.getUnassignedCourses().isEmpty()){
            for(Course c : temp){
                for(int i = 0; i<10; i++){
                    if(s.getSemesters().size()<=i){
                        s.addSemester();
                    }
                    if(s.verifyCourse(s.getSemesters().get(i), c)){
                        if(s.addCourse(s.getSemesters().get(i), c))
                            break;
                    }
                }
            }
        }
    }

    public void calculateCurrentSchedule(Schedule s){
        clearPlanning();
        List<Course> temp = new ArrayList<Course>();
        for (Course c : s.getUnassignedCourses()) {
            temp.add(c);
        }
        for(Course c : temp){
            for(int j = 0; j<10; j++){
                for(int i = 0; i<s.getSemesters().size(); i++){
                    if(s.verifyCourse(s.getSemesters().get(i), c)){
                        if(s.addCourse(s.getSemesters().get(i), c))
                            break;
                    }
                }
            }
        }
    }



    //public void calculateCurrentSchedule(Schedule s) {
    //    clearPlanning();
    //    List<Course> temp = new ArrayList<Course>();
    //    for (Course c : s.getUnassignedCourses()) {
    //        temp.add(c);
    //    }
    //    while (!temp.isEmpty()) {
    //        for (Course c : s.getUnassignedCourses()) {
    //            for (Semester sem : s.getSemesters()) {
    //                boolean readyToAdd = true;
    //                for (Course pre : c.getPreReqs()) {
    //                    // if all prereqs are assigned
    //                    if (temp.contains(pre)) {
    //                        readyToAdd = false;
    //                    }
    //                }
    //                for (Course pre : sem.getCourses()) {
    //                    // if all prereqs are assigned
    //                    if (temp.contains(pre)) {
    //                        readyToAdd = false;
    //                    }
    //                }
    //                if (readyToAdd && sem.addCourse(c)) {
    //                    temp.remove(c);
    //                    break;
    //                }
    //                else if(readyToAdd)
    //                {
    //                    temp.remove(c);
    //                }
    //            }
    //        }
    //        s.setUnassignedCourses(temp);
    //    }
    //}

    public void clearPlanning() {
        for (Semester s : sched.getSemesters()) {
            for (int i = s.getCourses().size() - 1; i > -1; i--) {
                if (!s.getCourses().get(i).getCmpleted())
                    sched.removeCourse(s, s.getCourses().get(i));
            }
        }
    }

    public void filterClass() {

    }

    public void getMajorProgress() {

    }

    public void getMinorProgress() {

    }

    public void downloadPlan() {

    }

    public Schedule getSchedule() {
        return sched;
    }

    public void saveNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return this.notes;
    }

}
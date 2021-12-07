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

    public void calculateAllSchedule(Schedule s, boolean summer){
        boolean flag = false;
        clearPlanning();
        List<Course> temp = new ArrayList<Course>();
        for (Course c : s.getUnassignedCourses()) {
            temp.add(c);
        }
        while(!s.getUnassignedCourses().isEmpty()){
            for(Course c : temp){
                for(int i = 0; i<=s.getSemesters().size(); i++){
                    if(s.getSemesters().size()<=i){
                        s.addSemester(summer);
                        i = 0;
                    }
                    else if(!(s.getSemesters().size()<=i) && isSemesterGap(i, summer)){
                        s.addSemester(summer, i);
                        i = 0;
                    }
                    if(s.verifyCourse(s.getSemesters().get(i), c)){
                        if(s.addCourse(s.getSemesters().get(i), c))
                            flag = true;
                    }
                    if(flag)
                        break;
                    if(!s.verifyCourse(s.getSemesters().get(i), c) && s.getSemesters().get(i).getCourses().isEmpty())
                        break;
                }
                flag = false;
            }
            temp = new ArrayList<Course>();
            for (Course c : s.getUnassignedCourses()) {
                temp.add(c);
            }
        }
        cleanSchedule(s);
    }

    public boolean isSemesterGap(int semIndex, boolean summer){
        try{
            switch(sched.getSemesters().get(semIndex).getSeason()){
                case "Fall": return(sched.getSemesters().get(semIndex+1).getSeason() != "Spring");
                case "Spring": if(summer){return (sched.getSemesters().get(semIndex+1).getSeason() != "Summer");}else{return (sched.getSemesters().get(semIndex+1).getSeason() != "Fall");}
                case "Summer": return(sched.getSemesters().get(semIndex+1).getSeason() != "Fall");
                default: return true;
            }
        }catch(Exception e){return false;}
    }
    
    public void calculateCurrentSchedule(Schedule s){
        clearPlanning();
        boolean flag = false;
        List<Course> temp = new ArrayList<Course>();
        for (Course c : s.getUnassignedCourses()) {
            temp.add(c);
        }
        for(Course c : temp){
            for(int i = 0; i<s.getSemesters().size(); i++){
                if(s.verifyCourse(s.getSemesters().get(i), c)){
                    if(s.addCourse(s.getSemesters().get(i), c))
                        flag = true;
                }
                if(flag)
                    break;
            }
            flag = false;
        }
        cleanSchedule(s);
    }

    public void cleanSchedule(Schedule s){
        for (int i = sched.getSemesters().size()-1; i>-1; i--) {
            if(sched.getSemesters().get(i).getCourses().isEmpty())
                sched.removeSemester(sched.getSemesters().get(i));
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
        //for (int i = sched.getSemesters().size()-1; i>-1; i--) {
        //    if(sched.getSemesters().get(i).getCourses().isEmpty())
        //        sched.removeSemester(sched.getSemesters().get(i));
        //}
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
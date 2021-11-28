package com.it326.planner;

import com.it326.planner.*;

public class PlannerDriver {
    public static void main(String[] args){
        Account testAcc = new Account();
        testAcc.setFirstName("Jake");
        testAcc.setLastName("Ewen");
        //click the add schedule option
        testAcc.getManager().addSchedule();
        //chlick the add semester option
        testAcc.getManager().getSchedule(0).addSemester();
        //click the add course option
        Course crs1 = new Course("IT", "Rishi", "some it class", 326);
        Course crs2 = new Course("IT", "Rishi", "some other it class", 314);
        Course crs3 = new Course("IT", "Rishi", "some other other it class", 391);
        testAcc.getManager().getSchedule(0).getSemester(0).addCourse(crs1);
        testAcc.getManager().getSchedule(0).getSemester(0).addCourse(crs2);
        testAcc.getManager().getSchedule(0).getSemester(0).addCourse(crs3);
    }
}

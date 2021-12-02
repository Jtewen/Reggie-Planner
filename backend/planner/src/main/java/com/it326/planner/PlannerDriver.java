package com.it326.planner;

import java.util.List;

import com.it326.planner.*;
import com.it326.planner.Majors.*;

public class PlannerDriver {
    public static void main(String[] args){
        Account testAcc = new Account("Jake", "Ewen", "jewen", "p@ssw0rd");
        //click the add schedule option
        testAcc.getManager().addSchedule();
        //chlick the add semester option
        Schedule mainSched = testAcc.getManager().getSchedule(0);
        mainSched.setMajor(new IT());
        


    }
}

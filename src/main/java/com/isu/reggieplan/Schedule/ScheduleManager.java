package com.isu.reggieplan.Schedule;
import com.isu.reggieplan.Schedule.majors.*;


//This manages the schedule and is used to interact with a schedule.

public class ScheduleManager {

    private Schedule schedule;

    public ScheduleManager(Major major){
        schedule = new Schedule(major);
    }

    public ScheduleManager(Schedule schedule){
        this.schedule = schedule;
    }
    
}

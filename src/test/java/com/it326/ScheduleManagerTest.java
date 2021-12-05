package com.it326;
import com.it326.Majors.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ScheduleManagerTest {
    
    @Test
    public void testScheduleManagerCon()
    {
        ScheduleManager t = new ScheduleManager();
    }

    @Test
    public void testCalculateAllSchedule()
    {
        ScheduleManager t = new ScheduleManager();
        Major m = new Major("Computer Science");
        t.getSchedule().setMajor(m);
        t.calculateAllSchedule(t.getSchedule());
        assertEquals(8, t.getSchedule().getSemesters().size());
    }

    @Test
    public void testCalculateCurrentSchedule()
    {
        ScheduleManager t = new ScheduleManager();
        Major m = new Major("Computer Science");
        t.getSchedule().setMajor(m);
        t.getSchedule().addSemester();
        t.calculateCurrentSchedule(t.getSchedule());
        assertEquals(5, t.getSchedule().getSemesters().get(0).getCourses().size());
    }

    @Test
    public void testClearPlanning()
    {
        ScheduleManager t = new ScheduleManager();
        Major m = new Major("Computer Science");
        t.getSchedule().setMajor(m);
        t.calculateAllSchedule(t.getSchedule());
        t.clearPlanning();
        assertEquals(0, t.getSchedule().getSemesters().get(0).getCourses().size());
    }
}

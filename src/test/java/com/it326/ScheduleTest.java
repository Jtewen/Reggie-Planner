package com.it326;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ScheduleTest {
    @Test()
    public void testScheduleConEmpty()
    {
        Schedule t = new Schedule();
    }

    @Test()
    public void testScheduleCon()
    {
        Schedule t = new Schedule("TestName");
        assertEquals("TestName", t.getName());
    }

    @Test()
    public void testAddSemesterEmpty()
    {
        Schedule t = new Schedule();
        Semester testSem = t.addSemester();
    }

    @Test()
    public void testAddSemester()
    {
        Schedule t = new Schedule();
        Semester testSem = t.addSemester("Fall", 2021);
        assertEquals("Fall", testSem.getSeason());
        assertEquals(2021, testSem.getYear());
    }

    @Test()
    public void testAddSemester2()
    {
        Schedule t = new Schedule();
        Semester testSem = new Semester("Fall", 2021);
        t.addSemester(testSem);
    }

    @Test()
    public void testRemoveSemester()
    {
        Schedule t = new Schedule();
        Semester testSem = t.addSemester("Fall", 2021);
        t.removeSemester(testSem);
        assertEquals(0, t.getSemesters().size());
    }

    @Test()
    public void testAddExternalCreds()
    {
        Schedule t = new Schedule();
        t.addExternalCreds(5);
        assertEquals(5, t.getExternalCreds());
    }




}

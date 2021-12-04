package com.it326;
import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class SemesterTest {
    @Test
    public void testSemesterConEmpty()
    {
        
    }
    @Test 
    public void testSemesterCon()
    {

    }
    @Test
    public void testAddCourse()
    {
        Account acc = new Account("Joseph", "test");
        Schedule sch = acc.getManager().getSchedule();
        Semester sem = sch.addSemester();
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "IT 261", "This is a Decriptions");
        assertEquals(true, sem.addCourse(c));
    }
    @Test
    public void testRemoveCourse()
    {
        
    }
    @Test
    public void testCompareTo()
    {
        
    }
}

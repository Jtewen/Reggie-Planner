package com.it326;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class SemesterTest {
    @Test
    public void testSemesterConEmpty()
    {
        Semester s = new Semester();
        assertEquals(15, s.getMaxCreds());
    }
    @Test 
    public void testSemesterCon()
    {
        Semester s = new Semester("Fall", 2021);
        assertEquals(15, s.getMaxCreds());
        assertEquals("Fall", s.getSeason());
        assertEquals(2021, s.getYear());
    }
    @Test
    public void testAddCourse()
    {
        Account acc = new Account("Test", "test");
        Schedule sch = acc.getManager().getSchedule();
        Semester sem = sch.addSemester();
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "IT 261", "This is a Decriptions");
        assertEquals(true, sem.addCourse(c));
    }
    @Test
    public void testRemoveCourse()
    {
        Account acc = new Account("Joseph", "test");
        Schedule sch = acc.getManager().getSchedule();
        Semester sem = sch.addSemester();
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "IT 261", "This is a Decriptions");
        Course c2 = new Course("IT", 327, "Concepts of Computer Programming", 3, "IT 279", "This is a Decription");
        sem.addCourse(c);
        sem.addCourse(c2);
        sem.removeCourse(c2);
        assertEquals(1, sem.getCourses().size());
    
    }
    @Test
    public void testCompareTo()
    {
        Account acc = new Account("Joseph", "test");
        Schedule sch = acc.getManager().getSchedule();
        Semester sem = sch.addSemester("Fall", 2021);
        Semester sem2 = sch.addSemester("Spring", 2022);

        assertEquals(-1000, sem.compareTo(sem2));
    }
}

package com.it326;
import com.it326.Majors.*;
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
        assertEquals(1, t.getSemesters().size());
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
        assertEquals(1, t.getSemesters().size());
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

    @Test()
    public void testSetMajor()
    {
        Schedule t = new Schedule();
        Major m = new Major("Web Dev");
        t.setMajor(m);
        assertEquals(m, t.getMajor());
    }

    @Test()
    public void testSetMinor()
    {
        Schedule t = new Schedule();
        Minor m = new Minor("Technology");
        t.setMinor(m);
        assertEquals(m, t.getMinor());
    }

    @Test()
    public void testAddCourse()
    {
        Schedule t = new Schedule();
        Semester testSem = t.addSemester("Fall", 2021);
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "IT 261", "This is a Decriptions");
        assertEquals(true, t.addCourse(testSem, c));
    }

    @Test()
    public void testAddCourseExplicit()
    {
        Schedule t = new Schedule();
        Semester testSem = t.addSemester("Fall", 2021);
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "IT 261", "This is a Decriptions");
        assertEquals(true, t.addCourse(testSem, c));
    }

    @Test()
    public void testRemoveCourse()
    {
        Schedule t = new Schedule();
        Semester testSem = t.addSemester("Fall", 2021);
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "IT 261", "This is a Decriptions");
        t.addCourse(testSem, c);
        t.removeCourse(testSem, c);
        assertEquals(0, testSem.getCourses().size());
    }

    @Test()
    public void testVerifyCourse()
    {
        Schedule t = new Schedule();
        Semester testSem = t.addSemester("Fall", 2021);
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "", "This is a Decriptions");
        assertEquals(true, t.verifyCourse(testSem, c));
    }
}

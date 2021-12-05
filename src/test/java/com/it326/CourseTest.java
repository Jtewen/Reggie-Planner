package com.it326;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CourseTest {
    

    @Test
    public void testCourseConEmpty()
    {
        Course c = new Course();
        assertEquals("", c.getDepartment());
        assertEquals(0, c.getcourseNumber());
        assertEquals("", c.getDescription());
    }
    @Test 
    public void testCourseCon()
    {
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "IT 261", "This is a Description");
        assertEquals("IT", c.getDepartment());
        assertEquals(326, c.getcourseNumber());
        assertEquals(3, c.getCredits());
        assertEquals("This is a Description", c.getDescription());
        

    }
    @Test
    public void testCompareTo()
    {
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "IT 261", "This is a Description");
        Course c2 = new Course("IT", 327, "Concepts of Computer Programming", 3, "IT 279", "This is a Description");
        assertEquals(-1, c.compareTo(c2));
    }
}

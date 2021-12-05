package com.it326;
import com.it326.Majors.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
public class MajorTest {
    @Test(expected = NullPointerException.class)
    public void testMajorCon()
    {
        Major m = new Major("");
    }
    @Test
    public void testGetPath()
    {
        Major m = new Major("Web Dev");
        assertEquals(null, m.getPath("NotAMajor"));
    }
    @Test
    public void testGetPath2()
    {
        Major m = new Major("Computer Science");
        assertEquals("src/main/java/com/it326/Majors/CSlist.csv", m.getPath("Computer Science"));
    }
    @Test
    public void testGetPath3()
    {
        Major m = new Major("Web Dev");
        assertEquals("src/main/java/com/it326/Majors/WDlist.csv", m.getPath("Web Dev"));
    }
    @Test
    public void testGetPath4()
    {
        Major m = new Major("Cybersecurity");
        assertEquals("src/main/java/com/it326/Majors/Cyberlist.csv", m.getPath("Cybersecurity"));
    }
    @Test
    public void testAddParents()
    {
        Major m = new Major("Cybersecurity");
        List<Course> list = new ArrayList<Course>();
        Course c = new Course("IT", 326, "Principles of Software Engineering", 3, "IT 261", "This is a Decriptions");
        Course c2 = new Course("IT", 327, "Concepts of Computer Programming", 3, "IT 279", "This is a Decriptions");
        list.add(c);
        list.add(c2);
        m.addParents(list);
    
    }
    @Test
    public void testIsNumeric()
    {
        Major m = new Major("Computer Science");
        assertEquals(true, m.isNumeric("12"));
    }
    @Test
    public void testIsNumeric2()
    {
        Major m = new Major("Computer Science");
        assertEquals(false, m.isNumeric("I am not a number"));
    }
}

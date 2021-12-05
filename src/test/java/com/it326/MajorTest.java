package com.it326;
import com.it326.Majors.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class MajorTest {
    @Test
    public void testMajorCon() throws NullPointerException
    {
        Major m = new Major("");
    }
    @Test
    public void testGetPath()
    {
    }
    @Test
    public void testAddParents()
    {
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

package com.it326;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class DatabaseHandlerTest {
    @Test
    public void testLoadAccount()
    {
    }
    @Test
    public void testSaveAccountEmpty()
    {
    }
    public void testSaveAccountOne()
    {
    }
    public void testVerfyAccount()
    {
    }
    public  void testRegisterAccount()
    {
        DatabaseHandler handle = new DatabaseHandler();
        Account acc = new Account("Test1", "test1");
        // Account acc2 = acc.r
        assertEquals(null, handle.registerAccount("Test1", "test1"));
    }
}

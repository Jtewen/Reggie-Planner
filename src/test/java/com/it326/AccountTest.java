package com.it326;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class AccountTest {

    @Test
    public void testAccountCon()
    {
        Account acc = new Account("TestUsername", "TestPassword");
        assertEquals("TestUsername", acc.getUsername());
        assertEquals("TestPassword", acc.getPassword());
    }
    

    
}

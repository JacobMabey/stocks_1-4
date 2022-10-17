package edu.neumont.csc180.mabey.jacob;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testAccountConstructor() {
        Account account = new Account(73, "Jacob", "Mabey", "xxx-xx-xxxx", "test@testing.com", "123-456-7890", 457.32);
        assertEquals(73, account.GetAccountNumber());
        assertEquals("Jacob Mabey", account.GetFullName());
        assertEquals("xxx-xx-xxxx", account.GetSSN());
        assertEquals("test@testing.com", account.GetEmailAddress());
        assertEquals("123-456-7890", account.GetPhoneNumber());
        assertEquals(457.32, account.GetBalance());
        assertEquals(0, account.GetShareCount());

        StockMain.InitializeList();
        Account account2 = StockMain.GetAccount(164);
        assertEquals(164, account2.GetAccountNumber());
        assertEquals("Candida Kensy", account2.GetFullName());
        assertEquals("225-58-1772", account2.GetSSN());
        assertEquals("ckensy4j@theguardian.com", account2.GetEmailAddress());
        assertEquals("276-953-7876", account2.GetPhoneNumber());
        assertEquals(7444345.60, account2.GetBalance());
        assertEquals(-23847, account2.GetShareCount());
    }
    

    @Test
    void testAccountAddTransaction() {
        Account account = new Account(73, "Jacob", "Mabey", "xxx-xx-xxxx", "test@testing.com", "123-456-7890", 457.32);
        account.AddTransaction(new Transaction("Buy", "WXYZ", 32.58, 3));
        account.AddTransaction(new Transaction("Buy", "ABC", 23.14, 6));
        account.AddTransaction(new Transaction("Sell", "THC", 362.12, 2));
        
        //457.32 - 32.58*3 - 23.14*6 + 362.12*2 = (944.98)
        assertEquals(944.98, account.GetBalance());
        //0 + 3 + 6 - 2 = (7)
        assertEquals(7, account.GetShareCount());

        //Second Test
        Account account2 = new Account(42, "Alex", "Mabey", "xxx-xx-xxxx", "test@testing.com", "123-456-7890", 523.47);
        account2.AddTransaction(new Transaction("Buy", "WXYZ", 41.24, 1));
        account2.AddTransaction(new Transaction("Sell", "ABC", 45.12, 7));
        account2.AddTransaction(new Transaction("Buy", "THC", 164.32, 3));
        account2.AddTransaction(new Transaction("Sell", "DFG", 32.02, 3));
        
        //523.47 - 41.24*1 + 45.12*7 - 164.32*3 + 32.02*3 = (401.17)
        assertEquals(401.17, account2.GetBalance());
        //0 + 1 - 7 + 3 + 3 = (7)
        assertEquals(-6, account2.GetShareCount());
    }
}
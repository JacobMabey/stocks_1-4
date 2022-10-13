package edu.neumont.csc180.mabey.jacob;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;


class AppTest {
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
    }


    @Test
    void testGetAccount() {
        StockMain.InitializeList();
        Account account = StockMain.GetAccount(196);
        assertEquals("Alisa Oneill", account.GetFullName());
        assertEquals(196, account.GetAccountNumber());
        assertEquals(19, account.GetTransactionsCount());
        assertEquals(9138103.12, account.GetBalance());
        assertEquals(6994, account.GetShareCount());
    }


    @Test
    void testHtmlFilesExist() {
        StockMain.InitializeList();

        //Write ALL accounts to there own html files
        for (int i = 1; i <= StockMain.GetJSONArraySize(); i++) {
            Account account = StockMain.GetAccount(i);
            StockMain.WriteToHTML(account);

            assertTrue(new File("accountStatements/"+account.GetHtmlFileName()).exists());
        }
    }
}

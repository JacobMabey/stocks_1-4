package edu.neumont.csc180.mabey.jacob;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;


class StockMainTest {

    @Test
    void testGetAccount() {
        StockMain.InitializeList();
        Account account = StockMain.GetAccount(196);
        assertEquals("Alisa Oneill", account.GetFullName());
        assertEquals(196, account.GetAccountNumber());
        assertEquals(19, account.GetTransactionsCount());
        assertEquals(9138103.12, account.GetBalance());
        assertEquals(6994, account.GetShareCount());

        //Test out of bounds search
        Account account2 = StockMain.GetAccount(352);
        assertNull(account2);
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

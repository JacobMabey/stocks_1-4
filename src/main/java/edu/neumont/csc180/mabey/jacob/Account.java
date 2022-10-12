package edu.neumont.csc180.mabey.jacob;

import java.util.ArrayList;

public class Account {
    private int accountNum;
    private String firstname, lastname;
    private String ssn, email, phoneNum;
    private double cash;
    private int stockHoldings;

    private ArrayList<Transaction> transactions;

    public Account(int accountNum, String firstname, String lastname, String ssn, String email, String phoneNum) {
        this.accountNum = accountNum;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNum = phoneNum;
        this.cash = 0.00;
        this.stockHoldings = 0;

        transactions = new ArrayList<Transaction>();
    }
    public Account(int accountNum, String firstname, String lastname, String ssn, String email, String phoneNum, double cash) {
        this.accountNum = accountNum;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNum = phoneNum;
        this.cash = cash;
        this.stockHoldings = 0;

        transactions = new ArrayList<Transaction>();
    }
    
    public int GetAccountNumber() { return accountNum; }
    public String GetFirstName() { return firstname; }
    public String GetLastName() { return lastname; }
    public String GetFullName() { return firstname+" "+lastname; }
    public String GetSSN() { return ssn; }
    public String GetEmailAddress() { return email; }
    public String GetPhoneNumber() { return phoneNum; }
    public double GetBalance() { return cash; }
    public int GetShareCount() { return stockHoldings; }

    public ArrayList<Transaction> GetTransactions() { return transactions; }
    public int GetTransactionsCount() { return transactions.size(); }

    public void AddTransaction(Transaction transaction) {
        transactions.add(transaction);
        switch(transaction.GetType()) {
            case "Buy":
                cash -= transaction.GetTotal();
                stockHoldings += transaction.GetAmountOfShares();
                break;
            case "Sell":
                cash += transaction.GetTotal();
                stockHoldings -= transaction.GetAmountOfShares();
                break;
        }
        cash = (double)((int)(cash * 100.0 + 0.5) / 100.0);
    }

    @Override
    public String toString() {
        String result = "";
        result += "Acount #: " + accountNum + "\n";
        result += GetFullName() + "\t" + ssn + "\t" + email + "\t" + phoneNum + "\n";
        for (Transaction t : transactions) {
            result += t + "\n";
        }
        result += "Balance: $" + cash + "\tTotal Shares: " + stockHoldings;

        return result;
    }
}

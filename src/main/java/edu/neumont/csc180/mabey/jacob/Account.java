package edu.neumont.csc180.mabey.jacob;

import java.util.ArrayList;

public class Account {
    private int accountNum;
    private String firstname, lastname;
    private String ssn, email, phoneNum;
    private String cash;

    private ArrayList<Transaction> transactions;

    public Account(int accountNum, String firstname, String lastname, String ssn, String email, String phoneNum) {
        this.accountNum = accountNum;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNum = phoneNum;
        this.cash = "$0.00";

        transactions = new ArrayList<Transaction>();
    }
    public Account(int accountNum, String firstname, String lastname, String ssn, String email, String phoneNum, String cash) {
        this.accountNum = accountNum;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNum = phoneNum;
        this.cash = cash;

        transactions = new ArrayList<Transaction>();
    }
    
}

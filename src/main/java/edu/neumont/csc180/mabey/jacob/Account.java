package edu.neumont.csc180.mabey.jacob;

public class Account {
    private int accountNum;
    private String firstname, lastname;
    private String ssn, email, phoneNum;

    public Account(int accountNum, String firstname, String lastname, String ssn, String email, String phoneNum) {
        this.accountNum = accountNum;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.email = email;
        this.phoneNum = phoneNum;
    }
    
}

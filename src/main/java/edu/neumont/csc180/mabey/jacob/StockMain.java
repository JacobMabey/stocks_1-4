package edu.neumont.csc180.mabey.jacob;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 */
public final class StockMain {
    private static JSONParser parser = new JSONParser();
    private static JSONArray jsonArray;

    private StockMain() {
    }
    public static void main(String[] args) {
        InitializeList();

        Account account = GetAccount(42);
        WriteToHTML(account);
        System.out.println(account);
        /*Scanner input = new Scanner(System.in);
        String inputText = "";
        Account account = null;
        int accountNum;
        
        while (true) {
            account = null;
            while (account == null) {
                System.out.print("\n(enter 'q' to quit)\nEnter Account Number: ");
                inputText = input.nextLine();

                //If user enters "Q" or "q", stop the program
                if (inputText.toLowerCase().equals("q")) {
                    input.close();
                    return;
                }
                //If the user does not enter a number, ask again
                try { accountNum = Integer.parseInt(inputText); }
                catch (NumberFormatException e) { continue; }
                account = GetAccount(accountNum);
            }
            //Print date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
            System.out.println("\n"+dtf.format(LocalDateTime.now()));
            //Print account
            System.out.println(account + "\n");
        }*/
    }



    /**
     * Gets an account, its information, and its list of transactions from a JSON file
     * @param accountNum
     * @return
     */
    public static Account GetAccount(int accountNum) {
        if (accountNum < 1 || accountNum > jsonArray.size()) {
            System.out.println("account number does not exist");
            return null;
        }
        //Add all acount info
        int account_number = Integer.parseInt(GetJSONValue(accountNum, "account_number"));
        String first_name = GetJSONValue(accountNum, "first_name");
        String last_name = GetJSONValue(accountNum, "last_name");
        String ssn = GetJSONValue(accountNum, "ssn");
        String email = GetJSONValue(accountNum, "email");
        String phoneNum = GetJSONValue(accountNum, "phone");
        double cash = Double.parseDouble(GetJSONValue(accountNum, "beginning_balance").substring(1));
        cash = (double)((int)(cash * 100.0 + 0.5) / 100.0);

        //Define the account
        Account account = new Account(account_number, first_name, last_name, ssn, email, phoneNum, cash);

        //Add all transactions
        JSONArray transactionsList = (JSONArray)((JSONObject)jsonArray.get(accountNum - 1)).get("stock_trades");
        for (Object transaction : transactionsList) {
            String type = ((JSONObject)transaction).get("type").toString();
            String symbol = ((JSONObject)transaction).get("stock_symbol").toString();
            double sharePrice = Double.parseDouble(((JSONObject)transaction).get("price_per_share").toString().substring(1));
            int shareCount = Integer.parseInt(((JSONObject)transaction).get("count_shares").toString());

            Transaction t = new Transaction(type, symbol, sharePrice, shareCount);
            account.AddTransaction(t);
        }
        return account;
    }


    /**
     * Writes an accounts info and transactions onto an html file
     * @param account
     */
    public static void WriteToHTML(Account account) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String date = dtf.format(LocalDateTime.now());
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("template.html"));

            //Write title, date, account info
            bw.write("<html>");
            bw.write("<head><title>"+account.GetFullName()+"</title></head>");
            bw.write("<body>");
            bw.write("<h4>"+date+"</h4>");
            bw.write("<h2>Account #:&#9;"+account.GetAccountNumber()+"</h2>");
            bw.write("<h2>"+account.GetFullName()+"&#9;&#9;&#9;"+account.GetSSN()+"&#9;&#9;&#9;"+account.GetEmailAddress()+"&#9;&#9;&#9;"+account.GetPhoneNumber()+"</h2>");
            
            //Write transactions
            bw.write("<table>");
            bw.write("<tr><th>Type</th><th>Symbol</th><th>Price</th><th>Shares</th><th>Total</th></tr>");
            for (Transaction transaction : account.GetTransactions()) {
                bw.write("<tr>");
                bw.write("<td>"+transaction.GetType()+"</td>");
                bw.write("<td>"+transaction.GetSymbol()+"</td>");
                bw.write("<td>"+transaction.GetSharePrice()+"</td>");
                bw.write("<td>"+transaction.GetAmountOfShares()+"</td>");
                bw.write("<td>"+transaction.GetTotal()+"</td>");
                bw.write("</tr>");
            }
            bw.write("</table>");

            //Write balance and share count
            

            //close html body
            bw.write("</body>");
            bw.write("</html>");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("template.html file could not be found.");
        }
    }



    /**
     * Gets a specific value from a key in a JSON file
     * @param accountNum
     * @param key
     * @return
     */
    private static String GetJSONValue(int accountNum, String key) {
        String value = "";
        int arrayLength = jsonArray.size();
        if (accountNum < 1 || accountNum > arrayLength) {
            System.out.println("account number does not exist");
            return value;
        }
        value = (String)((JSONObject)jsonArray.get(accountNum - 1)).get(key).toString();
        return value;
    }


    /**
     * Initializes the JSON list of data
     */
    public static void InitializeList() {
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader("stock_transations-3.by.account.holder.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("The file could not be found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading or writing from file");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("The file could not be parsed correctly");
        }
    }
}

package edu.neumont.csc180.mabey.jacob;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

        System.out.println(GetAccount(1));
    }


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
        String cash = GetJSONValue(accountNum, "beginning_balance");

        //Define the account
        Account account = new Account(account_number, first_name, last_name, ssn, email, phoneNum, cash);

        //Add all transactions
        JSONArray transactionsList = (JSONArray)((JSONObject)jsonArray.get(accountNum - 1)).get("stock_trades");
        for (Object transaction : transactionsList) {
            String type = ((JSONObject)transaction).get("type").toString();
            String symbol = ((JSONObject)transaction).get("stock_symbol").toString();
            String sharePrice = ((JSONObject)transaction).get("price_per_share").toString();
            int shareCount = Integer.parseInt(((JSONObject)transaction).get("count_shares").toString());

            Transaction t = new Transaction(type, symbol, sharePrice, shareCount);
            account.AddTransaction(t);
        }
        return account;
    }


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

    private static void InitializeList() {
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

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
    private StockMain() {
    }
    public static void main(String[] args) {
        System.out.println(GetJSONValue(300, "account_number"));
    }


    public static String GetJSONValue(int accountNum, String key) {
        String value = "";
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("stock_transations-3.by.account.holder.json"));
            value = (String)((JSONObject)jsonArray.get(accountNum - 1)).get(key).toString();
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
        return value;
    }
}

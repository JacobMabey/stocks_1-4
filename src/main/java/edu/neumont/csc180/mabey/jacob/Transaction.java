package edu.neumont.csc180.mabey.jacob;


public class Transaction {
    private String type, stockSymbol;
    private int sharePrice, shareCount, total;

    public Transaction(String type, String stockSymbol, int sharePrice, int shareCount) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.sharePrice = sharePrice;
        this.shareCount = shareCount;
    }
}
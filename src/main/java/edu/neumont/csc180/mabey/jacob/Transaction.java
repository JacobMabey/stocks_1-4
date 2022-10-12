package edu.neumont.csc180.mabey.jacob;


public class Transaction {
    private String type, stockSymbol;
    private int shareCount;
    private double sharePrice;

    public Transaction(String type, String stockSymbol, double sharePrice, int shareCount) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.sharePrice = sharePrice;
        this.shareCount = shareCount;
    }

    public String GetType() { return type; }
    public String GetSymbol() { return stockSymbol; }
    public double GetSharePrice() { return sharePrice; }
    public int GetAmountOfShares() { return shareCount; }
    public double GetTotal() { return (double)((int)(((double)shareCount * sharePrice) * 100.0 + 0.5) / 100.0); }


    @Override
    public String toString() {
        return type + "\t" + stockSymbol + "\t$" + sharePrice + "\t" + shareCount + "\t$" + GetTotal();
    }
}
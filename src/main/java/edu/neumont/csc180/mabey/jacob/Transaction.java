package edu.neumont.csc180.mabey.jacob;


public class Transaction {
    private String type, stockSymbol, sharePrice;
    private int shareCount;

    public Transaction(String type, String stockSymbol, String sharePrice, int shareCount) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.sharePrice = sharePrice;
        this.shareCount = shareCount;
    }

    public String GetType() { return type; }
    public String GetSymbol() { return stockSymbol; }
    public String GetSharePrice() { return sharePrice; }
    public int GetAmountOfShares() { return shareCount; }
    public double GetTotal() { return (double)((int)(((double)shareCount * (Double.parseDouble(sharePrice.substring(1)))) * 100.0 + 0.5) / 100.0); }


    @Override
    public String toString() {
        return type + "\t" + stockSymbol + "\t" + sharePrice + "\t" + shareCount + "\t$" + GetTotal();
    }
}
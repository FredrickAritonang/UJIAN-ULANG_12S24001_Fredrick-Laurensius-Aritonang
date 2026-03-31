package smartwallet.model;

public class CashbackTransaction extends Transaction {
    public CashbackTransaction(String u, double a, String t, String d) {
        super(u, a, t, d);
        fee = 0;
        netAmount = a;
    }

    public String getType() { return "cashback"; }
}
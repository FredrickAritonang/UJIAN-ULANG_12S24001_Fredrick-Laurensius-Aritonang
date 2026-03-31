package smartwallet.model;

public class SubscriptionTransaction extends Transaction {
    public SubscriptionTransaction(String u, double a, String t, String d) {
        super(u, a, t, d);
        fee = 0;
        netAmount = -a;
    }

    public String getType() { return "subscription"; }
}
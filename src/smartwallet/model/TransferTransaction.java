package smartwallet.model;

public class TransferTransaction extends Transaction {
    private String receiver;

    public TransferTransaction(String s, String r, double a, String t, String d) {
        super(s, a, t, d);
        this.receiver = r;
        fee = a * 0.02;
        netAmount = -(a + fee);
    }

    public String getReceiver() { return receiver; }
    public String getType() { return "transfer"; }
}
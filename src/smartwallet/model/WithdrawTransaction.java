package smartwallet.model;

public class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(String u, double a, String t, String d) {
        super(u, a, t, d);
        fee = a * 0.05;
        netAmount = -(a - fee); // ✅ sesuai soal
    }

    public String getType() { return "withdraw"; }
}
package smartwallet.driver;

import java.util.*;
import smartwallet.model.*;

public class Driver4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accs = new HashMap<>();
        String in;

        while (!(in = sc.nextLine()).equals("---")) {
            String[] p = in.split("#");
            Account a;

            switch (p[0]) {
                case "create-account":
                    accs.put(p[2], new Account(p[1], p[2], p[3]));
                    break;

                case "deposit":
                    a = accs.get(p[1]);
                    if (a != null) {
                        var t = new DepositTransaction(p[1], Double.parseDouble(p[2]), p[3], p[4]);
                        a.addTransaction(t);
                        a.updateBalance(t.getNetAmount());
                    }
                    break;

                case "withdraw":
                    a = accs.get(p[1]);
                    if (a != null) {
                        var t = new WithdrawTransaction(p[1], Double.parseDouble(p[2]), p[3], p[4]);
                        if (a.getBalance() + t.getNetAmount() >= 0) {
                            a.addTransaction(t);
                            a.updateBalance(t.getNetAmount());
                        }
                    }
                    break;

                case "transfer":
                    Account s = accs.get(p[1]), r = accs.get(p[2]);
                    if (s != null && r != null) {
                        double amt = Double.parseDouble(p[3]);
                        var t = new TransferTransaction(p[1], p[2], amt, p[4], p[5]);
                        if (s.getBalance() + t.getNetAmount() >= 0) {
                            s.addTransaction(t);
                            s.updateBalance(t.getNetAmount());
                            r.updateBalance(amt);

                            if (s.getAccountType().equals("PREMIUM")) {
                                var c = new CashbackTransaction(p[1], amt * 0.02, p[4], "Cashback");
                                s.addTransaction(c);
                                s.updateBalance(c.getNetAmount());
                            }
                        }
                    }
                    break;

                case "subscribe":
                    a = accs.get(p[1]);
                    if (a != null) {
                        double fee = Double.parseDouble(p[2]);
                        try {
                            if (a.getBalance() < fee) throw new InsufficientBalanceException("");
                            var t = new SubscriptionTransaction(p[1], fee, p[3], p[4]);
                            a.addTransaction(t);
                            a.updateBalance(t.getNetAmount());
                        } catch (Exception e) {}
                    }
                    break;
            }
        }

        accs.values().forEach(a ->
            System.out.println(a.getUsername() + "|" + a.getName() + "|" +
                               a.getAccountType() + "|" + a.getBalance())
        );
    }
}
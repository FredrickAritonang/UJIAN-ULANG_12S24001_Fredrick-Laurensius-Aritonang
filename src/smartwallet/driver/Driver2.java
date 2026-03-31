package smartwallet.driver;

import java.util.*;
import smartwallet.model.*;

public class Driver2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accs = new HashMap<>();
        String in;

        while (!(in = sc.nextLine()).equals("---")) {
            String[] p = in.split("#");

            switch (p[0]) {
                case "create-account":
                    accs.put(p[2], new Account(p[1], p[2], p[3]));
                    break;

                case "deposit":
                    Account a = accs.get(p[1]);
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
            }
        }

        accs.values().forEach(a ->
            System.out.println(a.getUsername() + "|" + a.getName() + "|" +
                    a.getAccountType() + "|" + a.getBalance())
        );
    }
}
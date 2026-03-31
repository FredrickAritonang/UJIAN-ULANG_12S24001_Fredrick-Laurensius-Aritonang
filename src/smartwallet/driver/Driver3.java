package smartwallet.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import smartwallet.model.*;

public class Driver3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();
        String line;
        while (!(line = sc.nextLine()).equals("---")) {
            String[] parts = line.split("#");
            if (parts[0].equals("create-account")) {
                String name = parts[1];
                String username = parts[2];
                String type = parts[3];
                accounts.put(username, new Account(name, username, type));
            } else if (parts[0].equals("deposit")) {
                String username = parts[1];
                double amount = Double.parseDouble(parts[2]);
                String timestamp = parts[3];
                String description = parts[4];
                if (accounts.containsKey(username)) {
                    DepositTransaction dt = new DepositTransaction(username, amount, timestamp, description);
                    accounts.get(username).addTransaction(dt);
                    accounts.get(username).updateBalance(dt.getNetAmount());
                }
            } else if (parts[0].equals("withdraw")) {
                String username = parts[1];
                double amount = Double.parseDouble(parts[2]);
                String timestamp = parts[3];
                String description = parts[4];
                if (accounts.containsKey(username)) {
                    WithdrawTransaction wt = new WithdrawTransaction(username, amount, timestamp, description);
                    if (accounts.get(username).getBalance() + wt.getNetAmount() >= 0) {
                        accounts.get(username).addTransaction(wt);
                        accounts.get(username).updateBalance(wt.getNetAmount());
                    }
                }
            } else if (parts[0].equals("transfer")) {
                String sender = parts[1];
                String receiver = parts[2];
                double amount = Double.parseDouble(parts[3]);
                String timestamp = parts[4];
                String description = parts[5];
                if (accounts.containsKey(sender) && accounts.containsKey(receiver)) {
                    TransferTransaction tt = new TransferTransaction(sender, receiver, amount, timestamp, description);
                    if (accounts.get(sender).getBalance() + tt.getNetAmount() >= 0) {
                        accounts.get(sender).addTransaction(tt);
                        accounts.get(sender).updateBalance(tt.getNetAmount());
                        accounts.get(receiver).updateBalance(amount);
                        if (accounts.get(sender).getAccountType().equals("PREMIUM")) {
                            double cashback = amount * 0.02;
                            CashbackTransaction ct = new CashbackTransaction(sender, cashback, timestamp, "Cashback for transfer");
                            accounts.get(sender).addTransaction(ct);
                            accounts.get(sender).updateBalance(ct.getNetAmount());
                        }
                    }
                }
            }
        }
        for (Account acc : accounts.values()) {
            System.out.println(acc.getUsername() + "|" + acc.getName() + "|" + acc.getAccountType() + "|" + acc.getBalance());
        }
    }
}
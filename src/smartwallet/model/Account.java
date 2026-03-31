package smartwallet.model;

import java.util.*;

public class Account {
    private String name, username, type;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String name, String username, String type) {
        this.name = name;
        this.username = username;
        this.type = type;
        this.balance = 0.0;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void updateBalance(double amount) {
        balance += amount;
    }

    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getAccountType() { return type; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }
}
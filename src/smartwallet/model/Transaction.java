package smartwallet.model;

public abstract class Transaction {
    protected static int counter = 0;
    protected int id;
    protected String username;
    protected double amount, fee, netAmount;
    protected String timestamp, description;

    public Transaction(String username, double amount, String timestamp, String description) {
        this.id = ++counter;
        this.username = username;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    public int getId() { return id; }
    public double getNetAmount() { return netAmount; }
    public double getFee() { return fee; }
    public double getAmount() { return amount; }
    public String getTimestamp() { return timestamp; }
    public String getDescription() { return description; }

    public abstract String getType();
}
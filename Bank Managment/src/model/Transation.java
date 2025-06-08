package model;

public class Transation {
    private int accountid;
    private String operation;
    private int amount;

    public Transation(String operation, int amount, int accountid) {
        this.operation = operation;
        this.amount = amount;
        this.accountid = accountid;
    }

    public int getAccountid() { return accountid; }
    public String getOperation() { return operation; }
    public int getAmount() { return amount; }
}

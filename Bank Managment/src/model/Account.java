package model;

public class Account {
    private int cusid;
    private int balance;
    private String accounttype;

    public Account(int cusid, String accounttype, int balance) {
        this.cusid = cusid;
        this.accounttype = accounttype;
        this.balance = balance;
    }

    public int getBalance() { return balance; }
    public String getAccounttype() { return accounttype; }
    public int getCusid() { return cusid; }
}

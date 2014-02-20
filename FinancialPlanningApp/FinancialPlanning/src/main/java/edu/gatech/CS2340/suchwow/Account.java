package edu.gatech.CS2340.suchwow;

/**
 * Created by Gustavo on 2/19/14.
 */
public class Account {

    private float balance, interestRate;
    private String name, displayName, accountNumber;


    public Account(float bal, String name) {
        balance = bal;
        this.name = name;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public float getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", interestRate=" + interestRate +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

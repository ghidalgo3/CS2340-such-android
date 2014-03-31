package edu.gatech.CS2340.suchwow.Domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.gatech.CS2340.suchwow.Persistence.SQLiteHandler;

/**
 * Created by Gustavo on 2/19/14.
 */
public class Account {
    private static Account currentAccount;

    private float balance, interestRate;
    private String name, displayName, accountNumber;
    private ArrayList<Transaction> transactions;
    private Context context;

    public Account(float bal, String name) {
        balance = bal;
        this.name = name;
        transactions = new ArrayList<Transaction>();
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

    public static void setCurrentAccount(Account current) {
        currentAccount = current;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public void addTransaction(Transaction transaction) {
        SQLiteHandler handler = new SQLiteHandler(context);
        transactions.add(transaction);
        if (transaction.isDeposit())
            balance += transaction.getAmount();
        else
            balance -= transaction.getAmount();
        Collections.sort(transactions);
        handler.addTransaction(User.getCurrentUser(), this, transaction);
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
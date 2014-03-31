package edu.gatech.CS2340.suchwow.Domain;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.gatech.CS2340.suchwow.Persistence.SQLiteHandler;

/**
 * Represents a financial Account. This is a Singleton class.
 * @author Gustavo
 * @version 1.0
 */
public class Account {
    /**
     * Reference to the current account.
     */
    private static Account currentAccount;

    /**
     * Financial variables
     */
    private float balance, interestRate;
    /**
     * Account name and number.
     */
    private String name, displayName, accountNumber;
    /**
     * List of account Transactions
     */
    private ArrayList<Transaction> transactions;
    /**
     * Current context.
     */
    private Context context;

    /**
     * Constructor initializes with a balance and a name.
     * @param bal
     * @param name
     */
    public Account(float bal, String name) {
        balance = bal;
        this.name = name;
        transactions = new ArrayList<Transaction>();
    }

    /**
     * Sets account balance.
     * @param balance new account balance
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    /**
     * Sets account interest rate.
     * @param interestRate new interest rate
     */
    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Sets account name.
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the account balance.
     * @return account balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * Getter for the interest rate.
     * @return account interest rate
     */
    public float getInterestRate() {
        return interestRate;
    }

    @Override
    /**
     * Creates a String representation of this object.
     * @return String rep of this object
     */
    public String toString() {
        return "Account{" +
               "balance=" + balance +
               ", interestRate=" + interestRate +
               ", name='" + name + '\'' +
               ", displayName='" + displayName + '\'' +
               ", accountNumber='" + accountNumber + '\'' +
               '}';
    }

    /**
     * Getter for the account name.
     * @return account name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the display name.
     * @return account display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Getter for the account number.
     * @return account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Setter for the account display name.
     * @param displayName account display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Setter for the account number.
     * @param accountNumber new account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Statically set the singleton account for the user.
     * @param current new current account
     */
    public static void setCurrentAccount(Account current) {
        currentAccount = current;
    }

    /**
     * Gets the current active account from the class.
     * @return current account
     */
    public static Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Adds a transaction to an account and updates the database.
     * @param transaction new transaction
     */
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

    /**
     * Getter for a list of transactions .
     * @return Account transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Sets the list of Transactions for this account.
     * @param transactions new trnasaction list
     */
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Gets the context for this account.
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets the context for this account.
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }
}
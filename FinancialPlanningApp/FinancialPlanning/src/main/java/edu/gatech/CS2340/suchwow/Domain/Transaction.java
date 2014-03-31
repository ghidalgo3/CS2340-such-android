package edu.gatech.CS2340.suchwow.Domain;

import android.content.Context;

import java.util.GregorianCalendar;

/**
 * Transaction is the domain object for a transaction. (name, ammount, etc)
 */
public class Transaction implements Comparable<Transaction> {
    private String name;
    private float amount;
    private boolean isDeposit;
    private long id;
    private String category;
    private GregorianCalendar userTimeStamp, systemTimeStamp;
    private Context context;

    /**
     * Construct a transaction
     * @param name The name of the transaction
     * @param amount The amount of the transaction
     * @param isDeposit Is the transaction a deposit
     * @param category The category of the deposit
     * @param userTimeStamp The time the user put in for the transaction
     * @param systemTimeStamp The time the transaction was actually made
     */
    public Transaction(String name, float amount, boolean isDeposit, String category, GregorianCalendar userTimeStamp, GregorianCalendar systemTimeStamp) {
        this.name = name;
        this.amount = amount;
        this.isDeposit = isDeposit;
        this.userTimeStamp = userTimeStamp;
        this.systemTimeStamp = systemTimeStamp;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public float getAmount() {
        return amount;
    }
    public String getCategory() { return category; }
    public boolean isDeposit() {
        return isDeposit;
    }
    public long getID() { return id; }
    public void setID(long idIn) { id = idIn; }
    public GregorianCalendar getUserTimeStamp() { return userTimeStamp; }
    public GregorianCalendar getSystemTimeStamp() { return systemTimeStamp; }

    /**
     * The hashCode be equal to another object's if they are equal.
     * @return The hash code
     */
    public int hashCode() {
        return userTimeStamp.hashCode() + systemTimeStamp.hashCode();
    }

    /**
     * If two objects are equal, their hash codes should be equal and a.compareTo(b) should be 0.
     * @param other The object we are comparing to
     * @return True if equal
     */
    public boolean equals(Object other) {
        if (!(other instanceof Transaction)) {
            return false;
        }
        Transaction otherTransaction = (Transaction) other;
        return userTimeStamp.equals(otherTransaction.getUserTimeStamp()) && systemTimeStamp.equals(otherTransaction.getSystemTimeStamp());
    }

    /**
     * Use the user time for comparison unless they're the same, then use system time
     * @param other The transaction we're comparing to
     * @return The comparison integer.
     */
    public int compareTo(Transaction other) {
        int dateComparison = userTimeStamp.compareTo(other.getUserTimeStamp());
        int sysComparison = systemTimeStamp.compareTo(other.getSystemTimeStamp());
        return (dateComparison != 0) ? dateComparison : sysComparison;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

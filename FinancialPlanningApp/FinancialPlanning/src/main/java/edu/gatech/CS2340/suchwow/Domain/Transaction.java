package edu.gatech.CS2340.suchwow.Domain;

import android.content.Context;

import java.util.GregorianCalendar;

/**
 * Transaction is the domain object for a transaction. (name, amount, etc).
 */
public class Transaction implements Comparable<Transaction> {
    /**
     * The transaction's name.
     */
    private String name;
    /**
     * The transaction's dollar amount.
     */
    private float amount;
    /**
     * If the transaction is a deposit, This is true.
     */
    private boolean isDeposit;
    /**
     * The id. This will be used later for deleting transactions.
     */
    private long id;
    /**
     * A string of the category to which this category belongs.
     */
    private String category;
    /**
     * The date the user chooses, and the date the transaction was created.
     */
    private GregorianCalendar userTimeStamp, systemTimeStamp;
    /**
     * Used only for get and set context, not used internally.
     */
    private Context context;

    /**
     * Construct a transaction.
     * @param nameIn The name of the transaction
     * @param amountIn The amount of the transaction
     * @param isDepositIn Is the transaction a deposit
     * @param categoryIn The category of the deposit
     * @param userTimeStampIn The time the user put in for the transaction
     * @param systemTimeStampIn The time the transaction was actually made
     */
    public Transaction(String nameIn, float amountIn, boolean isDepositIn, String categoryIn, GregorianCalendar userTimeStampIn, GregorianCalendar systemTimeStampIn) {
        name = nameIn;
        amount = amountIn;
        isDeposit = isDepositIn;
        userTimeStamp = userTimeStampIn;
        systemTimeStamp = systemTimeStampIn;
        category = categoryIn;
    }

    /**
     * Getter for name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for amount.
     * @return the amount
     */
    public float getAmount() { return amount; }

    /**
     * Getter for category.
     * @return the category
     */
    public String getCategory() { return category; }

    /**
     * Getter for deposit boolean.
     * @return Whether this is a deposit or not
     */
    public boolean isDeposit() { return isDeposit; }

    /**
     * Getter for id.
     * @return the id
     */
    public long getID() { return id; }

    /**
     * Setter for id.
     * @param idIn The id to set
     */
    public void setID(long idIn) { id = idIn; }

    /**
     * Getter for UserTimeStamp.
     * @return the UserTimeStamp
     */
    public GregorianCalendar getUserTimeStamp() { return userTimeStamp; }

    /**
     * Getter for SystemTimeStamp.
     * @return The SystemTimeStamp
     */
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
     * Use the user time for comparison unless they're the same, then use system time.
     * @param other The transaction we're comparing to
     * @return The comparison integer.
     */
    public int compareTo(Transaction other) {
        int dateComparison = userTimeStamp.compareTo(other.getUserTimeStamp());
        int sysComparison = systemTimeStamp.compareTo(other.getSystemTimeStamp());
        return (dateComparison != 0) ? dateComparison : sysComparison;
    }

    /**
     * Getter for context.
     * @return the context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Setter for context.
     * @param contextIn Context to set
     */
    public void setContext(Context contextIn) {
        context = contextIn;
    }
}

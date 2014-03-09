package edu.gatech.CS2340.suchwow;

import java.util.GregorianCalendar;

/**
 * Created by nathan on 2/25/14.
 */
public class Transaction implements Comparable<Transaction> {
    private String name;
    private float amount;
    private boolean isDeposit;
    private long id;
    private String category;
    private GregorianCalendar userTimeStamp, systemTimeStamp;
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
    protected void setID(long id) {
        this.id = id;
    }
    public GregorianCalendar getUserTimeStamp() { return userTimeStamp; }
    public GregorianCalendar getSystemTimeStamp() { return systemTimeStamp; }
    public int compareTo(Transaction other) {
        int dateComparison = userTimeStamp.compareTo(other.getUserTimeStamp());
        int sysComparison = systemTimeStamp.compareTo(other.getSystemTimeStamp());
        return (dateComparison != 0) ? dateComparison : sysComparison;
    }
}

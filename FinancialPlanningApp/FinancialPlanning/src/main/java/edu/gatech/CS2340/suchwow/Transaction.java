package edu.gatech.CS2340.suchwow;

import java.util.GregorianCalendar;

/**
 * Created by nathan on 2/25/14.
 */
public class Transaction {
    private String name;
    private float ammount;
    private boolean isDeposit;
    private GregorianCalendar userTimeStamp, systemTimeStamp;
    public Transaction(String name, float ammount, boolean isDeposit, GregorianCalendar userTimeStamp, GregorianCalendar systemTimeStamp) {
        this.name = name;
        this.ammount = ammount;
        this.isDeposit = isDeposit;
        this.userTimeStamp = userTimeStamp;
        this.systemTimeStamp = systemTimeStamp;
    }
    public String getName() {
        return name;
    }
    public float getAmmount() {
        return ammount;
    }
    public boolean isDeposit() {
        return isDeposit;
    }
    public GregorianCalendar getUserTimeStamp() { return userTimeStamp; }
    public GregorianCalendar getSystemTimeStamp() { return systemTimeStamp; }

}

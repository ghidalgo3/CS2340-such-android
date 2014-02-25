package edu.gatech.CS2340.suchwow;

/**
 * Created by nathan on 2/25/14.
 */
public class Transaction {
    private String name;
    private float ammount;
    private boolean isDeposit;
    public Transaction(String name, float ammount, boolean isDeposit) {
        this.name = name;
        this.ammount = ammount;
        this.isDeposit = isDeposit;
    }
    public String getName() {
        return name;
    }
    public float getAmmount() {
        return ammount;
    }
    public boolean isDeposit() { return isDeposit; }

}

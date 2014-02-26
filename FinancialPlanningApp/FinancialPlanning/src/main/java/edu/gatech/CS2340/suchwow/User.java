package edu.gatech.CS2340.suchwow;

import java.util.ArrayList;

/**
 * Created by nathan on 2/4/14.
 */
public class User {

    protected static User currentUser;
    private ArrayList<Account> accounts;
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        accounts = new ArrayList<>();
        /*
        //There needs to be some database code here to
        //initialize the accounts from the database. I don't know how
        //to do that though.
         */
    }

    public static void setCurrentUser(User newUser) {
        currentUser = newUser;
    }

    public static User getCurrentUser() {
        if (currentUser == null) {
            return new User("admin", "pass123");
        }
        return currentUser;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    protected void setAccounts(ArrayList<Account> newAccounts) {
        accounts = newAccounts;
    }

    public void addAccount(Account newAccount) {
        accounts.add(newAccount);
    }
}

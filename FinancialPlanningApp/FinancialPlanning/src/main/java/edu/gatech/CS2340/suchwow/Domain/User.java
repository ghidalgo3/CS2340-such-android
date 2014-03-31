package edu.gatech.CS2340.suchwow.Domain;

import android.content.Context;

//import org.junit.Test;

import java.util.ArrayList;

import edu.gatech.CS2340.suchwow.Persistence.SQLiteHandler;

/**
 * User class represents the current single user using the application.
 * This class is a singleton.
 * @author Nathan
 * @version 1.0
 */
public class User {

    private static User currentUser;
    private ArrayList<Account> accounts;
    private String name;
    private String password;
    private Context context;

    /**
     * Constructor that initializes a User with a name and a password.
     * @param name
     * @param password
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        accounts = new ArrayList<>();
    }

    /**
     * Set the singleton User.
     * @param newUser New user
     */
    public static void setCurrentUser(User newUser) {
        currentUser = newUser;
    }

    /**
     * Get the current singleton User.
     * @return current User
     */
    public static User getCurrentUser() {
        if (currentUser == null) {
            return new User("admin", "pass123");
        }
        return currentUser;
    }

    /**
     * Gets the User name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the User password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the accounts this user holds in an ArrayList.
     * @return accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * Sets the User's accounts to an arraylist of accounts.
     * @param newAccounts
     */
    public void setAccounts(ArrayList<Account> newAccounts) {
        accounts = newAccounts;
    }

    /**
     * Adds an account to the user and updates the database.
     * @param newAccount new account
     * @throws SQLiteHandler.InvalidAccountException
     */
    public void addAccount(Account newAccount) throws SQLiteHandler.InvalidAccountException {
        SQLiteHandler handler = new SQLiteHandler(context);
        try {
            handler.addAccount(this, newAccount);
            accounts.add(newAccount);
        }
        catch (SQLiteHandler.InvalidAccountException ex) {
            throw ex;
        }
    }

    /**
     * Get the current Context.
     * @return context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets the current Context.
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }
}

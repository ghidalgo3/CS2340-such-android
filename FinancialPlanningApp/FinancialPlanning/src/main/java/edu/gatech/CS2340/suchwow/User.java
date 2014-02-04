package edu.gatech.CS2340.suchwow;

/**
 * Created by nathan on 2/4/14.
 */
public class User {
    private String name;
    private String password;
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
}

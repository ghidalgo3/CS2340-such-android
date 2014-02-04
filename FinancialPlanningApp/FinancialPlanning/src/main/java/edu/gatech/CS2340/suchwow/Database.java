package edu.gatech.CS2340.suchwow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nathan on 2/4/14.
 */
public class Database {
    //For now, we'll trivially implement a database with a map
    private Map<String, User> userMap;
    public static Database globalDatabase;
    //This should probabally be changed as to not be global, but at this first simple level, it works.

    public Database() {
        userMap = new HashMap<String, User>();
        User adminUser = new User("admin", "pass123");
        userMap.put("admin", adminUser);
        //This should probabally be changed as to not be global, but at this first simple level, it works.
        globalDatabase = this;
    }
    //Get the user. If the password does not match, the function will return null.
    public User getUser(String name, String password) {
        User user = userMap.get(name);
        if (user == null || !user.getPassword().equals(password))
            return null;
        return user;
    }
}

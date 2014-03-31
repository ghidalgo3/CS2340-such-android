package edu.gatech.CS2340.suchwow.Domain;

import junit.framework.Assert;

//import org.junit.Test;

/**
 * Created by Gustavo on 3/27/14.
 */
public class UserTest {
    //@Test
    public void testSetCurrentUser() throws Exception {
        Assert.assertNotNull(User.getCurrentUser());
        User whatever = new User("User","Password");
        User.setCurrentUser(whatever);
        Assert.assertNotNull(User.getCurrentUser());
    }

    //@Test
    public void testGetCurrentUser() throws Exception {

    }
}

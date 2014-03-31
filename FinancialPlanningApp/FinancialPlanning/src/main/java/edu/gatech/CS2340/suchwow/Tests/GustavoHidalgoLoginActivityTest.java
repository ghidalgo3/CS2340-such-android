package edu.gatech.CS2340.suchwow.Tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.CheckBox;
import android.widget.EditText;

import edu.gatech.CS2340.suchwow.Activities.LoginActivity;
import edu.gatech.CS2340.suchwow.Activities.NewloginActivity;
import edu.gatech.CS2340.suchwow.R;

/**
 * Created by Gustavo on 3/31/14.
 */
public class GustavoHidalgoLoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    LoginActivity loginActivity;
    private EditText mUsernameView;
    private EditText mPasswordView;

    /**
     * Set up the ActivityUnitTestCase with the class we're testing
     */
    public GustavoHidalgoLoginActivityTest() {
        super(LoginActivity.class);
    }

    /**
     * Get all the fields we'll be using and start the activity
     * @throws Exception super.setUp throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        loginActivity = getActivity();
        mUsernameView = (EditText) loginActivity.findViewById(R.id.email);
        mPasswordView = (EditText) loginActivity.findViewById(R.id.password);
    }

    /**
     * Just call super.tearDown()
     * @throws Exception super.tearDown() throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * The actual function that tests our validateInput() function.
     */
    public void testValidateInput() {
        // Tests
        Runnable doValidate = new Runnable() {
            @Override
            public void run() {
                loginActivity.attemptLogin();
            }
        };

        //Account name
        android.os.SystemClock.sleep(1000);
        loginActivity.runOnUiThread(doValidate);
        android.os.SystemClock.sleep(1000);
        assertEquals("Account name missing", nameView.getError());
        android.os.SystemClock.sleep(1000);
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                nameView.setText("Bob's Account");
            }
        });
        android.os.SystemClock.sleep(1000);
        loginActivity.runOnUiThread(doValidate);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, nameView.getError());

    }

}
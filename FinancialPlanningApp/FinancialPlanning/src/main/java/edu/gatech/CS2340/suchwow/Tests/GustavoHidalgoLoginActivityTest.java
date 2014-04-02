package edu.gatech.CS2340.suchwow.Tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import edu.gatech.CS2340.suchwow.Activities.LoginActivity;
import edu.gatech.CS2340.suchwow.R;

/**
 * Created by Gustavo on 3/31/14.
 */
public class GustavoHidalgoLoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    LoginActivity loginActivity;
    private EditText mUsernameView;
    private EditText mPasswordView;

    /**
     * Set up the ActivityUnitTestCase with the class we're testing.
     */
    public GustavoHidalgoLoginActivityTest() {
        super(LoginActivity.class);
    }

    /**
     * Get all the fields we'll be using and start the activity.
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
     * Just call super.tearDown().
     * @throws Exception super.tearDown() throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * The actual function that tests login input validation.
     */
    public void testValidateInput() {
        // Tests
        Runnable attemptLogin = new Runnable() {
            @Override
            public void run() {
                loginActivity.attemptLogin();
            }
        };

        //Account name
        android.os.SystemClock.sleep(500);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(500);
        assertEquals(loginActivity.getString(R.string.error_field_required), mUsernameView.getError());
        android.os.SystemClock.sleep(500);
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUsernameView.setText("Gary Oak");
            }
        });
        android.os.SystemClock.sleep(500);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(500);
        assertEquals(null, mUsernameView.getError());
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setText("");
            }
        });
        android.os.SystemClock.sleep(500);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(500);
        assertEquals(loginActivity.getString(R.string.error_field_required), mPasswordView.getError());
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setText("pas");
            }
        });
        android.os.SystemClock.sleep(500);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(500);
        assertEquals(loginActivity.getString(R.string.error_invalid_password), mPasswordView.getError());
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setText("password");
            }
        });
        android.os.SystemClock.sleep(500);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, mPasswordView.getError());

    }

}
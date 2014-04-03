package edu.gatech.CS2340.suchwow.Tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import edu.gatech.CS2340.suchwow.Activities.LoginActivity;
import edu.gatech.CS2340.suchwow.Activities.RegisterActivity;
import edu.gatech.CS2340.suchwow.R;

/**
 * Created by Gustavo on 3/31/14.
 */
public class EvanMoscosoRegisterActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity> {
    RegisterActivity registerActivity;
    private EditText mUsernameView;
    private EditText mPasswordView;

    /**
     * Set up the ActivityUnitTestCase with the class we're testing.
     */
    public EvanMoscosoRegisterActivityTest() {
        super(RegisterActivity.class);
    }

    /**
     * Get all the fields we'll be using and start the activity.
     * @throws Exception super.setUp throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        registerActivity = getActivity();
        mUsernameView = (EditText) registerActivity.findViewById(R.id.email);
        mPasswordView = (EditText) registerActivity.findViewById(R.id.password);
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
     * The actual function that tests register input validation.
     */
    public void testValidateInput() {
        // Tests
        Runnable attemptRegister = new Runnable() {
            @Override
            public void run() {
                registerActivity.attemptRegister();
            }
        };
            @Override
            public void run() {
                registerActivity.attemptRegister();
            }
        };

        //Account name
        android.os.SystemClock.sleep(1000);
        registerActivity.runOnUiThread(attemptRegister);
        android.os.SystemClock.sleep(1000);
        assertEquals(registerActivity.getString(R.string.error_field_required), mUsernameView.getError());
        android.os.SystemClock.sleep(1000);
        registerActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUsernameView.setText("Al Pachino");
            }
        });
        android.os.SystemClock.sleep(1000);
        registerActivity.runOnUiThread(attemptRegister);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, mUsernameView.getError());
        registerActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setText("");
            }
        });
        android.os.SystemClock.sleep(1000);
        registerActivity.runOnUiThread(attemptRegister);
        android.os.SystemClock.sleep(1000);
        assertEquals(registerActivity.getString(R.string.error_field_required), mPasswordView.getError());
        registerActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setText("pas");
            }
        });
        android.os.SystemClock.sleep(1000);
        registerActivity.runOnUiThread(attemptRegister);
        android.os.SystemClock.sleep(1000);
        assertEquals(registerActivity.getString(R.string.error_invalid_password), mPasswordView.getError());
        registerActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setText("password");
            }
        });
        android.os.SystemClock.sleep(1000);
        registerActivity.runOnUiThread(attemptRegister);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, mPasswordView.getError());

    }

}
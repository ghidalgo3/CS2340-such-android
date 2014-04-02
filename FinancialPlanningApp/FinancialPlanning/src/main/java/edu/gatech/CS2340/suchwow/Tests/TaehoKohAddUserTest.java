package edu.gatech.CS2340.suchwow.Tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import edu.gatech.CS2340.suchwow.Activities.RegisterActivity;
import edu.gatech.CS2340.suchwow.R;
/**
 * Created by John Taeho Koh on 3/31/14.
 */
public class TaehoKohAddUserTest extends ActivityInstrumentationTestCase2<RegisterActivity>{
    TaehoKohAddUserTest transactionActivity;
    private EditText mUsernameView;
    private EditText mPasswordView;

    /**
     * Set up the ActivityUnitTestCase with the class we're testing.
     */
    public TaehoKohAddUserTest() {
        super(TaehoKohAddUserTest.class);
    }

    /**
     * Get all the fields we'll be using and start the activity.
     * @throws Exception super.setUp throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        transactionActivity = getActivity();
        mUsernameView = (EditText) transactionActivity.findViewById(R.id.email);
        mPasswordView = (EditText) transactionActivity.findViewById(R.id.password);
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
        android.os.SystemClock.sleep(1000);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(1000);
        assertEquals(loginActivity.getString(R.string.error_field_required), mUsernameView.getError());
        android.os.SystemClock.sleep(1000);
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUsernameView.setText("Gary Oak");
            }
        });
        android.os.SystemClock.sleep(1000);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, mUsernameView.getError());
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setText("");
            }
        });
        android.os.SystemClock.sleep(1000);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(1000);
        assertEquals(loginActivity.getString(R.string.error_field_required), mPasswordView.getError());
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setText("pas");
            }
        });
        android.os.SystemClock.sleep(1000);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(1000);
        assertEquals(loginActivity.getString(R.string.error_invalid_password), mPasswordView.getError());
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setText("password");
            }
        });
        android.os.SystemClock.sleep(1000);
        loginActivity.runOnUiThread(attemptLogin);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, mPasswordView.getError());

    }
}

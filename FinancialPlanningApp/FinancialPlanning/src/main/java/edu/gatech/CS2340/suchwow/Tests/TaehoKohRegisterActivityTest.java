package edu.gatech.CS2340.suchwow.Tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import edu.gatech.CS2340.suchwow.Activities.RegisterActivity;
import edu.gatech.CS2340.suchwow.R;
/**
 * Created by John Taeho Koh on 3/31/14.
 */
public class TaehoKohRegisterActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity>{
    RegisterActivity registerActivity;
    private EditText mUsernameView;
    private EditText mPasswordView;

    /**
     * Set up the ActivityUnitTestCase with the class we're testing.
     */
    public TaehoKohRegisterActivityTest() {
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
     * The actual function that tests login input validation.
     */
    public void testValidateInput() {
        // Tests
        Runnable attemptRegister = new Runnable() {
            @Override
            public void run() {
                registerActivity.attemptRegister();
            }
        };

        //Checks for
        android.os.SystemClock.sleep(1000);
        registerActivity.runOnUiThread(attemptRegister);
        android.os.SystemClock.sleep(1000);
        assertEquals(registerActivity.getString(R.string.error_field_required), mUsernameView.getError());
        android.os.SystemClock.sleep(1000);
        registerActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mUsernameView.setText("admin");
            }
        });
        android.os.SystemClock.sleep(1000);
        registerActivity.runOnUiThread(attemptRegister);
        android.os.SystemClock.sleep(1000);
        assertEquals(registerActivity.getString(R.string.error_field_required), mUsernameView.getError());

    }
}

package edu.gatech.CS2340.suchwow.Tests;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.CheckBox;
import android.widget.EditText;

import edu.gatech.CS2340.suchwow.Activities.NewAccountActivity;
import edu.gatech.CS2340.suchwow.R;

/**
 * Test the validateInput function from NewAccountActivity
 */
public class NathanBraswellNewAccountActivityTest extends ActivityInstrumentationTestCase2<NewAccountActivity> {
    NewAccountActivity accountActivity;
    EditText nameView, displayNameView, accountNumberView, accountBalanceView,
            interestRateView;
    CheckBox hasInterestView;

    /**
     * Set up the ActivityUnitTestCase with the class we're testing
     */
    public NathanBraswellNewAccountActivityTest() {
        super(NewAccountActivity.class);
    }

    /**
     * Get all the fields we'll be using and start the activity
     * @throws Exception super.setUp throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        accountActivity = getActivity();
        nameView = (EditText) accountActivity.findViewById(R.id.accountName);
        displayNameView = (EditText) accountActivity.findViewById(R.id.accountDisplayName);
        accountNumberView = (EditText) accountActivity.findViewById(R.id.accountNumber);
        accountBalanceView = (EditText) accountActivity.findViewById(R.id.accountBalance);
        hasInterestView = (CheckBox) accountActivity.findViewById(R.id.hasInterest);
        interestRateView = (EditText) accountActivity.findViewById(R.id.interestRate);
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
                accountActivity.validateInput();
            }
        };

        //Account name
        android.os.SystemClock.sleep(1000);
        accountActivity.runOnUiThread(doValidate);
        android.os.SystemClock.sleep(1000);
        assertEquals("Account name missing", nameView.getError());
        android.os.SystemClock.sleep(1000);
        accountActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                nameView.setText("Bob's Account");
            }
        });
        android.os.SystemClock.sleep(1000);
        accountActivity.runOnUiThread(doValidate);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, nameView.getError());


        //Short name
        android.os.SystemClock.sleep(1000);
        assertEquals("Short name missing", displayNameView.getError());
        accountActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                displayNameView.setText("For mah cookies");
            }
        });
        android.os.SystemClock.sleep(1000);
        accountActivity.runOnUiThread(doValidate);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, displayNameView.getError());

        //Account number
        android.os.SystemClock.sleep(1000);
        assertEquals("Account number missing", accountNumberView.getError());
        accountActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                accountNumberView.setText("1337");
            }
        });
        android.os.SystemClock.sleep(1000);
        accountActivity.runOnUiThread(doValidate);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, accountNumberView.getError());


        //Account balance
        android.os.SystemClock.sleep(1000);
        assertEquals("Account balance missing", accountBalanceView.getError());
        accountActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                accountBalanceView.setText("5237");
            }
        });
        android.os.SystemClock.sleep(1000);
        accountActivity.runOnUiThread(doValidate);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, accountBalanceView.getError());


        //Interest rate
        android.os.SystemClock.sleep(1000);
        accountActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hasInterestView.setChecked(true);
            }
        });
        android.os.SystemClock.sleep(1000);
        accountActivity.runOnUiThread(doValidate);
        android.os.SystemClock.sleep(1000);
        assertEquals("Interest checked, but no value entered", interestRateView.getError());
        accountActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                interestRateView.setText("19.14159");
            }
        });
        android.os.SystemClock.sleep(1000);
        accountActivity.runOnUiThread(doValidate);
        android.os.SystemClock.sleep(1000);
        assertEquals(null, interestRateView.getError());
    }

}

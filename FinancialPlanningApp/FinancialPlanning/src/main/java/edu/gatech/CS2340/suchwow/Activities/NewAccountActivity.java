package edu.gatech.CS2340.suchwow.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import edu.gatech.CS2340.suchwow.Domain.Account;
import edu.gatech.CS2340.suchwow.R;
import edu.gatech.CS2340.suchwow.Persistence.SQLiteHandler;
import edu.gatech.CS2340.suchwow.Domain.User;

public class NewAccountActivity extends ActionBarActivity {


    EditText nameView, displayNameView, accountNumberView, accountBalanceView,
             interestRateView;
    CheckBox hasInterestView;
    String name, displayName, accountNumber;
    float accountBalance, interestRate;
    boolean hasInterest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        nameView = (EditText) this.findViewById(R.id.accountName);
        displayNameView = (EditText) this.findViewById(R.id.accountDisplayName);
        accountNumberView = (EditText) this.findViewById(R.id.accountName);
        accountBalanceView = (EditText) this.findViewById(R.id.accountBalance);
        hasInterestView = (CheckBox) this.findViewById(R.id.hasInterest);
        interestRateView = (EditText) this.findViewById(R.id.interestRate);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void donePressed(View view) {
        Log.i("Check", "Made it to done pressed!!");
        nameView.setError(null);
        displayNameView.setError(null);
        accountNumberView.setError(null);
        accountBalanceView.setError(null);
        interestRateView.setError(null);
        boolean valid = true;
        if (nameView.getText().length() == 0) {
            nameView.setError("Account name missing");
            nameView.requestFocus();
            valid = false;
        } else if (displayNameView.getText().length() == 0) {
            displayNameView.setError("Short name missing");
            displayNameView.requestFocus();
            valid = false;
        } else if (accountNumberView.getText().length() == 0) {
            displayNameView.setError("Display name name missing");
            displayNameView.requestFocus();
            valid = false;
        } else if (accountBalanceView.getText().length() == 0) {
            accountBalanceView.setError("Account balance missing");
            accountBalanceView.requestFocus();
            valid = false;
        } else if (hasInterestView.isChecked()
                   && interestRateView.getText().length() == 0) {
            interestRateView.setError("Intrest checked, but no value entered");
            interestRateView.requestFocus();
            valid = false;
        }
        if (valid) {
            name = nameView.getText().toString();
            displayName = displayNameView.getText().toString();
            accountNumber = accountNumberView.getText().toString();
            accountBalance = Float.parseFloat(accountBalanceView.getText().toString());
            hasInterest = hasInterestView.isChecked();
            Account newAccount = new Account(accountBalance, name);
            newAccount.setDisplayName(displayName);
            newAccount.setAccountNumber(accountNumber);
            if (hasInterest) {
                interestRate = Float.parseFloat(interestRateView.getText().toString());
                newAccount.setInterestRate(interestRate);
            } else {
                newAccount.setInterestRate(-1.0f);
            }
            //LOOK AT US USING THAT SINGLETON DESIGN PATTERN, EXTRA CREDIT POINTS PLS
            User currentUser = User.getCurrentUser();
            try {
                currentUser.setContext(this);
                currentUser.addAccount(newAccount);
                Intent goBack = new Intent(this, AccountsActivity.class);
                startActivity(goBack);
            } catch (SQLiteHandler.InvalidAccountException ex) {
                nameView.setError(ex.getMessage());
                nameView.requestFocus();
            }
        }
    }
}
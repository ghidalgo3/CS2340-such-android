package edu.gatech.CS2340.suchwow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountsActivity extends ActionBarActivity {

    User currentUser;
    TextView welcomeMessage;
    LinearLayout screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        screen = (LinearLayout)this.findViewById(R.id.accountsVerticalLayout);
        welcomeMessage = (TextView)this.findViewById(R.id.welcomeMessage);
        currentUser = User.getCurrentUser();
        welcomeMessage.setText("Welcome "+ currentUser.getName());
        ArrayList<Account> accounts = currentUser.getAccounts();
        if (accounts.isEmpty()) {
            TextView noAccounts = new TextView(this);
            noAccounts.setText("No accounts to display");
            screen.addView(noAccounts);
        } else {
            for (Account account : accounts) {

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //DO NOT DELETE
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accounts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.add_account) {
            Intent newAccount = new Intent(this, NewAccountActivity.class);
            startActivity(newAccount);
        }
        return super.onOptionsItemSelected(item);
    }


}

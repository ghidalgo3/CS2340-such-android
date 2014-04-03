package edu.gatech.CS2340.suchwow.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.gatech.CS2340.suchwow.Adapters.AccountArrayAdapter;
import edu.gatech.CS2340.suchwow.Domain.Account;
import edu.gatech.CS2340.suchwow.Domain.User;
import edu.gatech.CS2340.suchwow.R;

/**
 * Activity displays a list of current accounts, each account is clickable.
 *
 * @author Gustavo Hidalgo
 * @version 1.0
 */
public class AccountsActivity extends Activity {

    /**
     * Singleton user.
     */
    User currentUser;
    /**
     * TextView that displays a welcome message.
     */
    TextView welcomeMessage;
    /**
     * List of accounts.
     */
    ListView screen;

    @Override
    /**
     * Lifecycle method. Creates views and sets action listener.
     * @param savedInstanceState activity state
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        screen = (ListView) this.findViewById(R.id.accountsListView);
        screen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Account.setCurrentAccount(User.getCurrentUser().getAccounts().get(i));
                startActivity(new Intent(AccountsActivity.this,
                        IndividualAccountActivity.class));
            }
        });
        //        welcomeMessage = (TextView)this.findViewById(R.id.welcomeMessage);
        currentUser = User.getCurrentUser();
        //        welcomeMessage.setText("Welcome "+ currentUser.getName());
        List<Account> accounts = currentUser.getAccounts();
        if (!accounts.isEmpty()) {
            screen.setAdapter(new AccountArrayAdapter(this, R.layout.account_display,
                    accounts));
        }
    }


    @Override
    /**
     * Inflates the Menu in this activity.
     * @return always return true
     */
    public boolean onCreateOptionsMenu(Menu menu) { //DO NOT DELETE
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accounts, menu);
        return true;
    }

    @Override
    /**
     * Lifecycle method for selecting a menu button.
     * @return boolean of option selection
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.add_account) {
            Intent newAccount = new Intent(this, NewAccountActivity.class);
            startActivity(newAccount);
        } else if (id == R.id.generate_spending_report) {
            Intent newReport = new Intent(this, GenerateReportActivity.class);
            startActivity(newReport);
        }
        return super.onOptionsItemSelected(item);
    }
}
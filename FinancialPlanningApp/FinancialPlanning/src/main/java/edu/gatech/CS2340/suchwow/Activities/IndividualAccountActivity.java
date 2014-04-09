package edu.gatech.CS2340.suchwow.Activities;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import edu.gatech.CS2340.suchwow.Adapters.TransactionArrayAdapter;
import edu.gatech.CS2340.suchwow.Domain.Account;
import edu.gatech.CS2340.suchwow.Domain.Transaction;
import edu.gatech.CS2340.suchwow.R;

/**
 * The activity that handles a single account.
 */
public class IndividualAccountActivity extends ActionBarActivity {
    /**
     * The account we're working with.
     */
    Account currentAccount;
    /**
     * The ListView that we setup with a TransactionArrayAdapter.
     */
    ListView screen;

    /**
     * This function gets the currentAccount and screen, and sets up the adapter to display
     * transactions.
     *
     * @param savedInstanceState What android passes in. We don't deal with it except for the super
     *                           call.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_account);
        screen = (ListView) this.findViewById(R.id.transactionsListView);
        //Singleton pattern
        currentAccount = Account.getCurrentAccount();
        List<Transaction> transactions = currentAccount.getTransactions();
        if (!transactions.isEmpty()) {
            screen.setAdapter(new TransactionArrayAdapter(this,
                    R.layout.transaction_display, transactions));
        }
        this.setTitle(currentAccount.getName());
    }

    /**
     * Sets up the options menu.
     *
     * @param menu Used to inflate the menu (action bar stuff)
     * @return Sucess. Always sucess!
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.individual_account, menu);
        return true;
    }

    /**
     * If someone clicks on add transaction, we fire up and start a NewTransactionActivity activity.
     *
     * @param item The item clicked on
     * @return The result of our super call of onOptionsItemSelected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.add_transaction) {
            Intent newTransaction = new Intent(this, NewTransactionActivity.class);
            startActivity(newTransaction);
        }
        return super.onOptionsItemSelected(item);
    }

}


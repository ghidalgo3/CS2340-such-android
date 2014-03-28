package edu.gatech.CS2340.suchwow.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.gatech.CS2340.suchwow.Domain.Account;
import edu.gatech.CS2340.suchwow.R;
import edu.gatech.CS2340.suchwow.Domain.Transaction;
import edu.gatech.CS2340.suchwow.Adapters.TransactionArrayAdapter;

public class IndividualAccountActivity extends ActionBarActivity {

    Account currentAccount;
    TextView welcomeMessage;
    ListView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_account);
        screen = (ListView)this.findViewById(R.id.transactionsListView);
        //Singleton pattern
        currentAccount = Account.getCurrentAccount();
        List<Transaction> transactions = currentAccount.getTransactions();
        if (!transactions.isEmpty()) {
            screen.setAdapter(new TransactionArrayAdapter(this,
                              R.layout.transaction_display, transactions));
        }
    }

    private void addTransactionView(Transaction transaction) {
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.individual_account, menu);
        return true;
    }

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


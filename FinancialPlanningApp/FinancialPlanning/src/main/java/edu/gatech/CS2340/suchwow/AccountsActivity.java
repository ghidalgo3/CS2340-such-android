package edu.gatech.CS2340.suchwow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AccountsActivity extends ActionBarActivity {

    User currentUser;
    TextView welcomeMessage;
    ListView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        screen = (ListView)this.findViewById(R.id.accountsListView);
        screen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Account.setCurrentAccount(User.getCurrentUser().getAccounts().get(i));
                startActivity(new Intent(AccountsActivity.this, IndividualAccountActivity.class));
            }
        });
//        welcomeMessage = (TextView)this.findViewById(R.id.welcomeMessage);
        currentUser = User.getCurrentUser();
//        welcomeMessage.setText("Welcome "+ currentUser.getName());
        List<Account> accounts = currentUser.getAccounts();
        if(accounts.isEmpty()) {
            //display some message later
        } else {
            screen.setAdapter(new AccountArrayAdapter(this, R.layout.account_display, accounts));
        }
    }

    private void addAccountView(Account account) {
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

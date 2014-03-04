package edu.gatech.CS2340.suchwow;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.GregorianCalendar;

public class NewTransactionActivity extends ActionBarActivity {
    EditText nameView, amountView;
    CheckBox depositView;
    DatePicker userDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
        nameView = (EditText)this.findViewById(R.id.transactionName);
        amountView = (EditText)this.findViewById(R.id.transactionAmount);
        depositView = (CheckBox)this.findViewById(R.id.depositCheckBox);
        userDate = (DatePicker)this.findViewById(R.id.transactionDatePicker);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_transaction, menu);
        return true;
    }

    public void donePressed(View view) {
        boolean valid = true;
        if (nameView.getText().length() == 0) {
            nameView.setError("Transaction name missing");
            valid = false;
        }
        if (amountView.getText().length() == 0) {
            amountView.setError("Transaction amount missing");
            valid = false;
        }
        if (valid) {
            String transactionName = nameView.getText().toString();
            float transactionAmount = new Float(amountView.getText().toString());
            Transaction transaction = new Transaction(transactionName, transactionAmount,
                    depositView.isChecked(), "",
                    new GregorianCalendar(userDate.getYear(), userDate.getMonth(), userDate.getDayOfMonth()),
                    new GregorianCalendar());
            Account currentAccount = Account.getCurrentAccount();
            SQLiteHandler handler = new SQLiteHandler(this);
            try {
                //SQLite code
                currentAccount.addTransaction(transaction);
                handler.addTransaction(User.getCurrentUser(), currentAccount, transaction);
                startActivity(new Intent(this, IndividualAccountActivity.class));
            } catch (/*Some SQL exception*/ Exception ex) {
                nameView.setError(ex.getMessage());
                nameView.requestFocus();
            }
        }
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

}

package edu.gatech.CS2340.suchwow.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.GregorianCalendar;

import edu.gatech.CS2340.suchwow.Domain.Account;
import edu.gatech.CS2340.suchwow.R;
import edu.gatech.CS2340.suchwow.Domain.Transaction;

public class NewTransactionActivity extends Activity {
    EditText nameView, amountView;
    DatePicker userDate;
    RadioGroup radioButtons;
    Spinner categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
        nameView = (EditText) this.findViewById(R.id.transactionNameTextBox);
        amountView = (EditText) this.findViewById(R.id.transactionAmountTextBox);
        userDate = (DatePicker) this.findViewById(R.id.transactionDatePicker);
        radioButtons = (RadioGroup) this.findViewById(R.id.radioButtons);
        categories = (Spinner) this.findViewById(R.id.category_spinner);
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
            int selectedRadio = radioButtons.getCheckedRadioButtonId();
            boolean isDeposit = selectedRadio == R.id.radio_deposit;
            String category = (String)categories.getSelectedItem();
            Transaction transaction = new Transaction(transactionName, transactionAmount,
                    isDeposit, category,
                    new GregorianCalendar(userDate.getYear(), userDate.getMonth(), userDate.getDayOfMonth()),
                    new GregorianCalendar());
            Account currentAccount = Account.getCurrentAccount();

            currentAccount.setContext(this);
            currentAccount.addTransaction(transaction);
            startActivity(new Intent(this, IndividualAccountActivity.class));

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

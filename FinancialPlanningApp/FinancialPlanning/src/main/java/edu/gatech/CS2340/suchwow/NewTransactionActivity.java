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
    EditText nameView, ammountView;
    CheckBox depositView;
    DatePicker userDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
        nameView = (EditText)this.findViewById(R.id.transactionName);
        ammountView = (EditText)this.findViewById(R.id.transactionAmmount);
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
        if (ammountView.getText().length() == 0) {
            ammountView.setError("Transaction ammount missing");
            valid = false;
        }
        if (valid) {
            String transactionName = nameView.getText().toString();
            float transactionAmmount = new Float(ammountView.getText().toString());
            Transaction transaction = new Transaction(transactionName, transactionAmmount,
                    depositView.isChecked(),
                    new GregorianCalendar(userDate.getYear(), userDate.getMonth(), userDate.getDayOfMonth()),
                    new GregorianCalendar());
            Account currentAccount = Account.getCurrentAccount();
            SQLiteHandler handler = new SQLiteHandler(this);
            try {
                //SQLite code
                currentAccount.addTransaction(transaction);
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

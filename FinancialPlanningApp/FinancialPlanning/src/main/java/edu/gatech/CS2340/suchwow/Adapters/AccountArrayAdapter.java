package edu.gatech.CS2340.suchwow.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import edu.gatech.CS2340.suchwow.Domain.Account;
import edu.gatech.CS2340.suchwow.R;

/**
 * An interfacer between the domain and the SQLite database that
 * handles as SQLite queries.
 */
public class AccountArrayAdapter extends ArrayAdapter<Account> {

    private int layoutResourceId;
    private Context context;
    private List<Account> data;
    /**
     * Constructor
     * @param context The current context
     * @param layoutResourceId The resource ID for a layout file containing a TextView to use when instantiating views.
     * @param data The Accounts to be displayed
     */
    public AccountArrayAdapter(Context context, int layoutResourceId,
                               List<Account> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }
    /**
     * Modify and get the view for this position.
     * @param position The position of the view
     * @param convertView The convert View
     * @param parent The ViewGroup parent
     * @return the view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AccountHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new AccountHolder();
            holder.accountName = (TextView) row.findViewById(R.id.accountNameView);
            holder.balance = (TextView) row.findViewById(R.id.accountBalanceView);
            row.setTag(holder);
        } else {
            holder = (AccountHolder) row.getTag();
        }
        String item1 = data.get(position).getName();
        float item2 = data.get(position).getBalance();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.accountName.setText(item1);
        holder.balance.setText(formatter.format(item2));
        return row;
    }
    /**
     * Simple class that holds the account holder's name and balance
     */
    static class AccountHolder {
        TextView accountName;
        TextView balance;
    }
}
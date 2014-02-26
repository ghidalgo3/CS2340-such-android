package edu.gatech.CS2340.suchwow;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

//gustavo
public class AccountArrayAdapter extends ArrayAdapter<Account> {

    private int layoutResourceId;
    private Context context;
    private List<Account> data;

    public AccountArrayAdapter(Context context, int layoutResourceId,
                               List<Account> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

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

    static class AccountHolder {
        TextView accountName;
        TextView balance;
    }
}
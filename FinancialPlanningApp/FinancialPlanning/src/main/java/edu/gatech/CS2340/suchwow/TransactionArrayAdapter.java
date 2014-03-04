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
/**
 * Created by nathan on 2/25/14.
 */


//gustavo
public class TransactionArrayAdapter extends ArrayAdapter<Transaction> {

    private int layoutResourceId;
    private Context context;
    private List<Transaction> data;

    public TransactionArrayAdapter(Context context, int layoutResourceId,
                                   List<Transaction> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TransactionHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new TransactionHolder();
            holder.transactionName = (TextView) row.findViewById(R.id.transactionNameView);
            holder.transactionAmount = (TextView) row.findViewById(
                                            R.id.transactionAmountView);
            row.setTag(holder);
        } else {
            holder = (TransactionHolder) row.getTag();
        }
        String item1 = data.get(position).getName();
        float item2 = data.get(position).getAmount();
        if (!data.get(position).isDeposit()) {
            item2 = -item2;
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.transactionName.setText(item1);
        holder.transactionAmount.setText((data.get(position).isDeposit() ? "+" : "-") +
                                          formatter.format(item2));
        return row;
    }

    static class TransactionHolder {
        TextView transactionName;
        TextView transactionAmount;
    }
}
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

import edu.gatech.CS2340.suchwow.Domain.Transaction;
import edu.gatech.CS2340.suchwow.R;

/**
 * TansactionArrayAdapter sets up the list of Transactions
 */

public class TransactionArrayAdapter extends ArrayAdapter<Transaction> {

    private int layoutResourceId;
    private Context context;
    private List<Transaction> data;

    /**
     * Set up our context, id, and data
     * @param contextIn The context we're using
     * @param layoutResourceIdIn It's id
     * @param dataIn The list of transactions
     */
    public TransactionArrayAdapter(Context contextIn, int layoutResourceIdIn,
                                   List<Transaction> dataIn) {
        super(contextIn, layoutResourceIdIn, dataIn);
        this.context = contextIn;
        this.layoutResourceId = layoutResourceIdIn;
        this.data = dataIn;
    }

    /**
     * Modify and get the view for this position.
     * @param position The position of the item
     * @param convertView The row
     * @param parent Used for the inflator
     * @return The view we modifieds
     */
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

    /**
     * Simple class that holds a transaction's name and ammount
     */
    static class TransactionHolder {
        TextView transactionName;
        TextView transactionAmount;
    }
}
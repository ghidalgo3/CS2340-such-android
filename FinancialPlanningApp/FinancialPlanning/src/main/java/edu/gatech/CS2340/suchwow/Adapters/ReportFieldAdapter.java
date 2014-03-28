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

import edu.gatech.CS2340.suchwow.Domain.Report;
import edu.gatech.CS2340.suchwow.R;

/**
 * An adapter for displaying ReportField in a ListView.
 */
public class ReportFieldAdapter extends ArrayAdapter<Report.ReportField> {

    private int layoutResourceId;
    private Context context;
    private List<Report.ReportField> data;

    public ReportFieldAdapter(Context contextIn, int layoutResourceIdIn,
                               List<Report.ReportField> dataIn) {
        super(contextIn, layoutResourceIdIn, dataIn);
        this.context = contextIn;
        this.layoutResourceId = layoutResourceIdIn;
        this.data = dataIn;
    }

    /**
     * Creates a View from the ReportField using report_field_display.
     * @param position The position of the view
     * @param convertView The convert View
     * @param parent The ViewGroup parent
     * @return the view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ReportFieldHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ReportFieldHolder();
            holder.fieldName = (TextView) row.findViewById(R.id.reportFieldNameView);
            holder.fieldAmount = (TextView) row.findViewById(R.id.reportFieldValueView);
            row.setTag(holder);
        } else {
            holder = (ReportFieldHolder) row.getTag();
        }
        String item1 = data.get(position).getFieldName();
        float item2 = data.get(position).getFieldValue();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.fieldName.setText(item1);
        holder.fieldAmount.setText(formatter.format(item2));
        return row;
    }

    static class ReportFieldHolder {
        TextView fieldName;
        TextView fieldAmount;
    }
}

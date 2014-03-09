package edu.gatech.CS2340.suchwow;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class DisplayReportActivity extends ActionBarActivity {
    private TextView reportName;
    private TextView reportRange;
    private ListView reportFields;
    private SimpleDateFormat dateFormatter;
    Report report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_report);

        reportFields = (ListView)this.findViewById(R.id.reportListView);
        reportName = (TextView)this.findViewById(R.id.reportNameLabel);
        reportRange = (TextView)this.findViewById(R.id.dateRangeLabel);

        List<Transaction> transactions = Account.getCurrentAccount().getTransactions();
        Bundle b = getIntent().getExtras();
        int startYear = b.getInt("startYear");
        int startMonth = b.getInt("startMonth");
        int startDay = b.getInt("startDay");
        int endYear = b.getInt("endYear");
        int endMonth = b.getInt("endMonth");
        int endDay = b.getInt("endDay");
        GregorianCalendar startDate = new GregorianCalendar(startYear, startMonth, startDay);
        GregorianCalendar endDate = new GregorianCalendar(endYear, endMonth, endDay);
        Log.v("ReportCreation", String.format("%d %d %d %d %d %d", startYear, startMonth,
                startDay, endYear, endMonth, endDay));
        dateFormatter = new SimpleDateFormat("MMM F, yyyy");
        switch (b.getInt("radioButton")) {
            case R.id.spendingCatRadioButton:
                reportName.setText(R.string.spending_category_report);
                reportRange.setText(dateFormatter.format(startDate.getTime()) +
                        " to " + dateFormatter.format(endDate.getTime()));
                report = new SpendingCategoryReport(startDate, endDate, transactions);
                break;
        }

        if (report == null) {
            //Some message
        } else {
            reportFields.setAdapter(new ReportFieldAdapter(this,
                    R.layout.report_field_display, report.getReportFields()));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_report, menu);
        return true;
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

package edu.gatech.CS2340.suchwow.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import edu.gatech.CS2340.suchwow.Adapters.ReportFieldAdapter;
import edu.gatech.CS2340.suchwow.Domain.Account;
import edu.gatech.CS2340.suchwow.Domain.Report;
import edu.gatech.CS2340.suchwow.Domain.SpendingCategoryReport;
import edu.gatech.CS2340.suchwow.Domain.Transaction;
import edu.gatech.CS2340.suchwow.Domain.User;
import edu.gatech.CS2340.suchwow.R;

//import android.support.v7.app.ActionBarActivity;
//import android.util.Log;

/**
 * An Activity that processes the parameters from GenerateReportActivity.
 */

public class DisplayReportActivity extends Activity {
    /**
     * Name of report in text view.
     */
    private TextView reportName;
    /**
     * Report range in text view.
     */
    private TextView reportRange;
    /**
     * Report fields in list view.
     */
    private ListView reportFields;
    /**
     * An Activity that processes the parameters from GenerateReportActivity.
     */
    private SimpleDateFormat dateFormatter;
    /**
     * Report reference that is going to be generated.
     */
    private Report report;

    /**
     * This function creates the menu that the user sees before generating the report.
     *
     * @param savedInstanceState What android passes in. We don't deal with it except for the super
     *                           call.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_report);

        reportFields = (ListView) this.findViewById(R.id.reportListView);
        reportName = (TextView) this.findViewById(R.id.reportNameLabel);
        reportRange = (TextView) this.findViewById(R.id.dateRangeLabel);

        List<Transaction> transactions = new ArrayList<Transaction>();
        for (Account acc : User.getCurrentUser().getAccounts()) {
            transactions.addAll(acc.getTransactions());
        }
        Bundle b = getIntent().getExtras();
        int startYear = b.getInt("startYear");
        int startMonth = b.getInt("startMonth");
        int startDay = b.getInt("startDay");
        int endYear = b.getInt("endYear");
        int endMonth = b.getInt("endMonth");
        int endDay = b.getInt("endDay");
        GregorianCalendar startDate = new GregorianCalendar(startYear, startMonth, startDay);
        GregorianCalendar endDate = new GregorianCalendar(endYear, endMonth, endDay);
//        Log.v("ReportCreation", String.format("%d %d %d %d %d %d", startYear, startMonth,
//                startDay, endYear, endMonth, endDay));
        dateFormatter = new SimpleDateFormat("MMM d, yyyy");
        switch (b.getInt("radioButton")) {
            case R.id.spendingCatRadioButton:
                reportName.setText(R.string.spending_category_report);
                reportRange.setText(dateFormatter.format(startDate.getTime())
                        + " to " + dateFormatter.format(endDate.getTime()));
                report = new SpendingCategoryReport(startDate, endDate, transactions);
                break;
            //Other buttons
            default:
                reportName.setText(R.string.invalid_report);
                break;

        }

        if (report != null) {
            reportFields.setAdapter(new ReportFieldAdapter(this,
                    R.layout.report_field_display, report.getReportFields()));
        }
    }

    /**
     * Sets up the options menu.
     *
     * @param menu Used to inflate the menu.
     * @return True, successful.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_report, menu);
        return true;
    }

    /**
     * If someone clicks Display Report, then it will show the appropriate report.
     *
     * @param item The item clicked on
     * @return The result of our super call of onOptionsItemSelected.
     */
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

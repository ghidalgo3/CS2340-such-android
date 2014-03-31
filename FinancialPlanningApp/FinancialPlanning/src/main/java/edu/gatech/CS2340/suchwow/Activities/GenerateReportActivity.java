package edu.gatech.CS2340.suchwow.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import edu.gatech.CS2340.suchwow.R;
/**
 * An Activity that generates a report that has been accumulated by the user.
 */
public class GenerateReportActivity extends ActionBarActivity {
    DatePicker startPicker;
    DatePicker endPicker;
    RadioGroup reportChoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        startPicker = (DatePicker)this.findViewById(R.id.startDatePicker);
        endPicker = (DatePicker)this.findViewById(R.id.endDatePicker);
        reportChoices = (RadioGroup)this.findViewById(R.id.reportChoiceRadioGroup);
    }

    /**
     * Boolean method that creates the menu
     * @return boolean true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.generate_report, menu);
        return true;
    }
    /**
     * Creates the buttons in the menu
     */
    public void generatePress(View view) {
        Intent reportDisplayIntent = new Intent(this, DisplayReportActivity.class);
        Bundle b = new Bundle();
        b.putInt("startMonth", startPicker.getMonth());
        b.putInt("startDay", startPicker.getDayOfMonth());
        b.putInt("startYear", startPicker.getYear());
        b.putInt("endMonth", endPicker.getMonth());
        b.putInt("endDay", endPicker.getDayOfMonth());
        b.putInt("endYear", endPicker.getYear());
        b.putInt("radioButton", reportChoices.getCheckedRadioButtonId());
        reportDisplayIntent.putExtras(b);
        startActivity(reportDisplayIntent);
    }
    /**
     * Determines the pressed button and carries out the necessary action
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

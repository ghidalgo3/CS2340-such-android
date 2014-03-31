package edu.gatech.CS2340.suchwow.Tests;

import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.ContextThemeWrapper;
import android.widget.TextView;

import edu.gatech.CS2340.suchwow.Activities.DisplayReportActivity;
import edu.gatech.CS2340.suchwow.R;

/**
 * Unit testing for the DisplayReportActivity class
 */
public class DisplayReportTest extends ActivityUnitTestCase<DisplayReportActivity> {

    /**
     * Constructor
     */
    public DisplayReportTest() {
        super(DisplayReportActivity.class);
    }

    /**
     * Setups up testing framework
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Tears down testing framework
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests if activity correctly recognizes an invalid report request
     */
    @SmallTest
    public void testInvalidReport(){
        // Set up a bundle and intent
        Intent activityIntent = new Intent(getInstrumentation().getTargetContext(), DisplayReportActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("startMonth", 0);
        bundle.putInt("startDay", 1);
        bundle.putInt("startYear", 2002);
        bundle.putInt("endMonth", 3);
        bundle.putInt("endDay", 4);
        bundle.putInt("endYear", 2005);
        bundle.putInt("radioButton", -1);
        activityIntent.putExtras(bundle);

        // start up the display report activity
        startActivity(activityIntent, null, null);

        // Getting a reference to the MainActivity of the target application
        DisplayReportActivity activity = (DisplayReportActivity)getActivity();

        // The expected text to be displayed in the textview
        String expected = "Invalid Report";
        TextView reportName = (TextView)activity.findViewById(R.id.reportNameLabel);
        String actual = reportName.getText().toString();

        // Check whether both are equal, otherwise test fails
        assertEquals(expected, actual );
    }

    /**
     * Tests if activity correctly recognizes a spending category report request
     */
    @SmallTest
    public void testSpendingCategoryRecognition(){
        // Set up a bundle and intent
        Intent activityIntent = new Intent(getInstrumentation().getTargetContext(), DisplayReportActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("startMonth", 0);
        bundle.putInt("startDay", 1);
        bundle.putInt("startYear", 2002);
        bundle.putInt("endMonth", 3);
        bundle.putInt("endDay", 4);
        bundle.putInt("endYear", 2005);
        bundle.putInt("radioButton", R.id.spendingCatRadioButton);
        activityIntent.putExtras(bundle);

        // start up the display report activity
        startActivity(activityIntent, null, null);

        // Getting a reference to the MainActivity of the target application
        DisplayReportActivity activity = (DisplayReportActivity)getActivity();

        // The expected text to be displayed in the textview
        String expected = "Spending Category Report";
        TextView reportName = (TextView)activity.findViewById(R.id.reportNameLabel);
        String actual = reportName.getText().toString();

        // Check whether both are equal, otherwise test fails
        assertEquals(expected, actual );
    }

    /**
     * Tests that the date range is correctly parsed and displayed
     */
    @SmallTest
    public void testDateRange(){
        // Set up a bundle and intent
        Intent activityIntent = new Intent(getInstrumentation().getTargetContext(), DisplayReportActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("startMonth", 0);
        bundle.putInt("startDay", 1);
        bundle.putInt("startYear", 2002);
        bundle.putInt("endMonth", 3);
        bundle.putInt("endDay", 4);
        bundle.putInt("endYear", 2005);
        bundle.putInt("radioButton", R.id.spendingCatRadioButton);
        activityIntent.putExtras(bundle);

        // start up the display report activity
        startActivity(activityIntent, null, null);

        // Getting a reference to the MainActivity of the target application
        DisplayReportActivity activity = (DisplayReportActivity)getActivity();

        // The expected text to be displayed in the textview
        String expected = "Jan 1, 2002 to Apr 4, 2005";
        TextView reportRange = (TextView)activity.findViewById(R.id.dateRangeLabel);
        String actual = reportRange.getText().toString();

        // Check whether both are equal, otherwise test fails
        assertEquals(expected, actual );
    }
}

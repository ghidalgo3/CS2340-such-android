package edu.gatech.CS2340.suchwow.Domain;

import java.util.GregorianCalendar;
import java.util.List;

import edu.gatech.CS2340.suchwow.R;

/**
 * Factory for generating concrete Report instances
 *
 * Created by Wayne on 4/16/2014.
 */
public class ReportFactory {

    /**
     * Returns concrete Report instance based on id
     *
     * @param id ID of the desired report
     * @param start start date of the report
     * @param end end date of the report
     * @return The concrete Report instance
     */
    public static Report createReport(int id, GregorianCalendar start, GregorianCalendar end) {
        switch (id) {
            case R.id.spendingCatRadioButton:
                return new SpendingCategoryReport(start, end);
            case R.id.DepositsRadioButton:
                return new DepositsReport(start, end);
            case R.id.AccountsCashFlowRadioButton:
                return new AccountsCashFlowReport(start, end);
            default:
                return null;
        }
    }
}

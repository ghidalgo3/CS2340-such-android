package edu.gatech.CS2340.suchwow.Domain;

import java.util.GregorianCalendar;

/**
 * Created by Wayne on 4/23/2014.
 */
public class AccountsCashFlowReport extends Report {

    public AccountsCashFlowReport(GregorianCalendar start, GregorianCalendar end) {
        super(start, end);
        name = "Accounts Cash Flow Report";
        generateReport();
    }

    protected void generateReport() {
        for (Account acc : User.getCurrentUser().getAccounts()) {
            float netFlow = 0;
            for (Transaction t : acc.getTransactions()) {
                if (t.getUserTimeStamp().compareTo(endDate) <= 0
                        && t.getUserTimeStamp().compareTo(startDate) >= 0) {
                    if (t.isDeposit()) {
                        netFlow += t.getAmount();
                    }
                    else {
                        netFlow -= t.getAmount();
                    }
                }
            }
            reportFields.add(new ReportField(acc.getName(), netFlow));
        }
    }
}

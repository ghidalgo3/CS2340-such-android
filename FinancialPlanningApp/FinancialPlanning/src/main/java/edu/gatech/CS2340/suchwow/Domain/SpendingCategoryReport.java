package edu.gatech.CS2340.suchwow.Domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.CS2340.suchwow.Domain.Report;
import edu.gatech.CS2340.suchwow.Domain.Transaction;

/**
 * Created by Wayne on 3/8/14.
 */
public class SpendingCategoryReport extends Report {

    public SpendingCategoryReport(Calendar start, Calendar end, List<Transaction> transactions) {
        super(start, end, transactions);
        generateReport();
    }

    @Override
    protected void generateReport() {
        float total = 0;
        Map<String, Float> fields = new HashMap<String, Float>();
        for (Transaction t : transactions) {
            if (!t.isDeposit() && t.getUserTimeStamp().compareTo(endDate) <= 0
                    && t.getUserTimeStamp().compareTo(startDate) >= 0) {
                Float amount = fields.get(t.getCategory());
                if (amount == null) {
                    fields.put(t.getCategory(), t.getAmount());
                }
                else {
                    fields.put(t.getCategory(), amount + t.getAmount());
                }
                total += t.getAmount();
            }
        }
        for(String key : fields.keySet()) {
            reportFields.add(new ReportField(key, fields.get(key)));
        }
        reportFields.add(new ReportField("Total", total));
    }
}
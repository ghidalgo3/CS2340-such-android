package edu.gatech.CS2340.suchwow.Domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gustavo on 4/23/14.
 */
public class DepositsReport extends Report{
    /**
     * Constructor.
     * @param start The start date of the report
     * @param end The end date of the report
     * @param transactions The transactions to be processed
     */
    public DepositsReport(Calendar start, Calendar end, List<Transaction> transactions) {
        super(start, end, transactions);
        generateReport();
    }

    /**
     * Iterates through transactions and aggregates transaction data by category.
     */
    @Override
    protected void generateReport() {
        float total = 0;
        Map<String, Float> fields = new HashMap<String, Float>();
        for (Transaction t : transactions) {
            if (t.isDeposit() && t.getUserTimeStamp().compareTo(endDate) <= 0
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
        for (Map.Entry<String, Float> entry : fields.entrySet()) {
            reportFields.add(new ReportField(entry.getKey(), entry.getValue()));
        }
        reportFields.add(new ReportField("Total", total));
    }
}

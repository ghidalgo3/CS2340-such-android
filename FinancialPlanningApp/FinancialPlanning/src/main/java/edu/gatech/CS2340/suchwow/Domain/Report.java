package edu.gatech.CS2340.suchwow.Domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Abstract class to represent a report which can be generated from a given list of transactions
 */
public abstract  class Report {
    protected Calendar startDate;
    protected Calendar endDate;
    protected List<Transaction> transactions;
    protected ArrayList<ReportField> reportFields;

    public Report(Calendar start, Calendar end, List<Transaction> transactions) {
        startDate = start;
        endDate = end;
        this.transactions = transactions;
        reportFields = new ArrayList<ReportField>();
    }

    /**
     * Uses the transactions to populate the report fields, which
     * are then displayed in a ListView
     */
    protected abstract void generateReport();

    public ArrayList<ReportField> getReportFields() {
        return reportFields;
    }

    public void setReportFields(ArrayList<ReportField> reportFields) {
        this.reportFields = reportFields;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * A single row of the report which contains a string to label the row and
     * a float value for the monetary amount associated with the row
     */
    public static class ReportField {
        private String fieldName;
        private float fieldValue;

        public ReportField(String name, float value) {
            fieldName = name;
            fieldValue = value;
        }

        public float getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(float fieldValue) {
            this.fieldValue = fieldValue;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }
    }
}

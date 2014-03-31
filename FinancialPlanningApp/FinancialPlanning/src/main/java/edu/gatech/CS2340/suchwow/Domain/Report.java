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

    /**
     * Constructor
     * @param start Start date of the report
     * @param end End date of the report
     * @param transactions Transactions to be processed by the report
     */
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

    /**
     * Returns the fields of the report
     * @return The fields of the report
     */
    public ArrayList<ReportField> getReportFields() {
        return reportFields;
    }

    /**
     * Sets the fields of the report
     * @param reportFields The fields of the report
     */
    public void setReportFields(ArrayList<ReportField> reportFields) {
        this.reportFields = reportFields;
    }

    /**
     * Returns the report's start date
     * @return The report's start date
     */
    public Calendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the report's start date
     * @param startDate The report's new start date
     */
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the report's end date
     * @return The report's end date
     */
    public Calendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the report's end date
     * @param endDate The report's new end date
     */
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the transactions processed by the report
     * @return The transactions processed by the report
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Sets the transactions processed by the report
     * @param transactions The transactions processed by the report
     */
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

        /**
         * Constructor
         * @param name The name of the report field
         * @param value The value to be displayed with the field
         */
        public ReportField(String name, float value) {
            fieldName = name;
            fieldValue = value;
        }

        /**
         * Returns the value of the report field
         * @return The value of the report field
         */
        public float getFieldValue() {
            return fieldValue;
        }

        /**
         * Sets the value of the report field
         * @param fieldValue The value of the report field
         */
        public void setFieldValue(float fieldValue) {
            this.fieldValue = fieldValue;
        }

        /**
         * Returns the name of the report field
         * @return The name of the report field
         */
        public String getFieldName() {
            return fieldName;
        }

        /**
         * Sets the name of the report field
         * @param fieldName The name of the report field
         */
        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }
    }
}

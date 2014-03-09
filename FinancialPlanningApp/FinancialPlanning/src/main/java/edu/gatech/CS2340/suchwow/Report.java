package edu.gatech.CS2340.suchwow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by Wayne on 3/8/14.
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

    public class ReportField {
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

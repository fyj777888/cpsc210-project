package model;

public class FinanceTracker {
    private double values;
    private double loans;

    public FinanceTracker() {
        values = 0;
        loans = 0;
    }

    public double getValues() {
        return values;
    }

    public double getLoans() {
        return loans;
    }

    public void addValues(double values) {
        this.values = this.values + values;
    }

    public void decreaseValues(double values) {
        this.values = this.values - values;
    }

    public void borrowMoney(double values) {
        loans = loans + values;
        this.addValues(values);
    }

    public void repayMoney(double values) {
        loans = loans - values;
        this.decreaseValues(values);
    }

}

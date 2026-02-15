package model;

public class FinanceTracker {
    private int values;
    private int loans;

    public FinanceTracker() {
        values = 0;
        loans = 0;
    }

    public int getValues() {
        return values;
    }

    public int getLoans() {
        return loans;
    }

    public void addValues(int values) {
        this.values = this.values + values;
    }

    public void decreaseValues(int values) {
        this.values = this.values - values;
    }

    public void borrowMoney(int values) {
        loans = loans + values;
        this.addValues(values);
    }

    public void repayMoney(int values) {
        loans = loans - values;
        this.decreaseValues(values);
    }

}

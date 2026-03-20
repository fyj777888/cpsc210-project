package model;

public class FinanceTracker {
    private double values;
    private double loans;

    public FinanceTracker() {
        values = 0;
        loans = 0;
    }

    // EFFECTS: returns the current amount of money available
    public double getValues() {
        return values;
    }

    // MODIFIES: this
    // EFFECTS: sets the current amount of money borrowed to the given amount
    public void setLoans(double loans) {
        this.loans = loans;
    }

    // EFFECTS: returns the current amount of money borrowed
    public double getLoans() {
        return loans;
    }

    // MODIFIES: this
    // EFFECTS: sets the current amount of money available to the given amount
    public void setValues(double values) {
        this.values = values;
    }

    // MODIFIES: this
    // EFFECTS: adds the given amount of money to the current amount of money
    // available
    public void addValues(double values) {
        this.values = this.values + values;
    }

    // MODIFIES: this
    // EFFECTS: decreases the current amount of money available by the given amount
    public void decreaseValues(double values) {
        this.values = this.values - values;
    }

    // MODIFIES: this
    // EFFECTS: increases the current amount of money borrowed by the given amount
    // and adds it
    // to the current amount of money available
    public void borrowMoney(double values) {
        loans = loans + values;
        this.addValues(values);
    }

    // MODIFIES: this
    // EFFECTS: decreases the current amount of money borrowed by the given amount
    // and decreases it
    // from the current amount of money available
    public void repayMoney(double values) {
        loans = loans - values;
        this.decreaseValues(values);
    }

}

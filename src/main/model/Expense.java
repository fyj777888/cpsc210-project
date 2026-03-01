package model;

public class Expense {
    private double values;
    private String purpose;
    private String category;

    public Expense(double values, String purpose, String category) {
        this.values = values;
        this.purpose = purpose;
        this.category = category;
    }

    public double getExpenses() {
        return values;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getCategory() {
        return category;
    }

}

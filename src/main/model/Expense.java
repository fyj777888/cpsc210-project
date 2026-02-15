package model;

public class Expense {
    private int values;
    private String purpose;
    private String category;

    public Expense(int values, String purpose, String category) {
        this.values = values;
        this.purpose = purpose;
        this.category = category;
    }

    public int getExpenses() {
        return values;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getCategory() {
        return category;
    }

}

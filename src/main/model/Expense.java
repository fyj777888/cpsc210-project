package model;

public class Expense {
    private double values;
    private String purpose;
    private String category;
    private EventLog eventLog;

    public Expense(double values, String purpose, String category) {
        this.values = values;
        this.purpose = purpose;
        this.category = category;
        eventLog = EventLog.getInstance();
        eventLog.logEvent(new Event("Expense: $" + values));
    }

    // EFFECTS: returns the amount of money spent for this expense
    public double getExpenses() {
        return values;
    }

    // EFFECTS: returns the purpose of this expense
    public String getPurpose() {
        return purpose;
    }

    // EFFECTS: returns the category of this expense
    public String getCategory() {
        return category;
    }

}

package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

public class FinanceApp implements Writable {
    private ExpenseRecorder e;
    private FinanceTracker f;

    public FinanceApp() {
        e = new ExpenseRecorder();
        f = new FinanceTracker();
    }

    // MODIFIES: this
    // EFFECTS: sets the given expense recorder and finance tracker
    // to the FinanceApp
    public void setFinanceApp(ExpenseRecorder e, FinanceTracker f) {
        this.e = e;
        this.f = f;
    }
    // MODIFIES: this
    // EFFECTS: adds an expense to the expense recorder and updates the finance tracker
    public void addExpense(Expense expense) {
        e.addExpense(expense);
        f.decreaseValues(expense.getExpenses());
    }
    // EFFECTS: returns the expense recorder of this FinanceApp
    public ExpenseRecorder getExpenseRecorder() {
        return e;
    }
    // EFFECTS: returns the finance tracker of this FinanceApp
    public FinanceTracker getFinanceTracker() {
        return f;
    }

    @Override
    // EFFECTS: returns this FinanceApp as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        JSONObject trackerJson = new JSONObject();
        trackerJson.put("values", f.getValues());
        trackerJson.put("loans", f.getLoans());
        json.put("tracker", trackerJson);

        JSONArray expensesArray = new JSONArray();
        for (Expense expense : e.getExpenses()) {
            JSONObject expenseJson = new JSONObject();
            expenseJson.put("values", expense.getExpenses());
            expenseJson.put("purpose", expense.getPurpose());
            expenseJson.put("category", expense.getCategory());
            expensesArray.put(expenseJson);
        }
        json.put("expenses", expensesArray);

        return json;
    }
}
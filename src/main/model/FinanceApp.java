package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

public class FinanceApp implements Writable {
    private ExpenseRecorder expenseRecorder;
    private FinanceTracker financeTracker;

    public FinanceApp() {
        expenseRecorder = new ExpenseRecorder();
        financeTracker = new FinanceTracker();
    }

    // MODIFIES: this
    // EFFECTS: sets the given expense recorder and finance tracker
    // to the FinanceApp
    public void setFinanceApp(ExpenseRecorder e, FinanceTracker f) {
        this.expenseRecorder = e;
        this.financeTracker = f;
    }

    // MODIFIES: this
    // EFFECTS: adds an expense to the expense recorder and updates the finance
    // tracker
    public void addExpense(Expense expense) {

        expenseRecorder.addExpense(expense);
        financeTracker.decreaseValues(expense.getExpenses());
    }
    // EFFECTS: returns the expense recorder of this FinanceApp

    public ExpenseRecorder getExpenseRecorder() {
        return expenseRecorder;
    }
    // EFFECTS: returns the finance tracker of this FinanceApp

    public FinanceTracker getFinanceTracker() {
        return financeTracker;
    }

    @Override
    // EFFECTS: returns this FinanceApp as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        JSONObject trackerJson = new JSONObject();
        trackerJson.put("values", financeTracker.getValues());
        trackerJson.put("loans", financeTracker.getLoans());
        json.put("tracker", trackerJson);

        JSONArray expensesArray = new JSONArray();
        for (Expense expense : expenseRecorder.getExpenses()) {
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
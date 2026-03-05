package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

public class FinanceApp implements Writable {
    private ExpenseRecorder e;
    private FinanceTracker f;

    public FinanceApp(ExpenseRecorder e, FinanceTracker f) {
        this.e = e;
        this.f = f;
    }

    public void addExpense(Expense expense) {
        e.addExpense(expense);
        f.decreaseValues(expense.getExpenses());
    }

    public ExpenseRecorder getExpenseRecorder() {
        return e;
    }

    public FinanceTracker getFinanceTracker() {
        return f;
    }

    @Override
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
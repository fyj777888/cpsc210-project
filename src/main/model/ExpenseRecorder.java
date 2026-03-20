package model;

import java.util.ArrayList;

public class ExpenseRecorder {
    private ArrayList<Expense> expenseRecorder;

    public ExpenseRecorder() {
        expenseRecorder = new ArrayList<Expense>();
    }

    // MODIFIES: this
    // EFFECTS: adds an expense to the expense recorder

    public void addExpense(Expense e) {
        expenseRecorder.add(e);
    }

    // EFFECTS: returns the list of expenses in the expense recorder

    public ArrayList<Expense> getExpenses() {
        return expenseRecorder;
    }

}

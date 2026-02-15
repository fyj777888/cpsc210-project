package model;

import java.util.ArrayList;

public class ExpenseRecorder {
    private ArrayList<Expense> expenseRecorder;

    public ExpenseRecorder() {
        expenseRecorder = new ArrayList<Expense>();
    }

    public void addExpense(Expense e) {
        expenseRecorder.add(e);
    }

    public ArrayList<Expense> getExpenses() {
        return expenseRecorder;
    }

}

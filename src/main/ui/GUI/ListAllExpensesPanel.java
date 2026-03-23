package ui.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Expense;
import model.FinanceApp;

public class ListAllExpensesPanel extends JPanel {
    private FinanceApp financeApp;
    private JTextArea textArea;

    public ListAllExpensesPanel(FinanceApp financeApp) {
        this.financeApp = financeApp;
        setLayout(null);
        JLabel titleLabel = new JLabel("List all expenses");
        titleLabel.setBounds(50, 150, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 200, 600, 1050);
        add(scrollPane);
        listExpenses();
    }

    private void listExpenses() {
        StringBuilder sb = new StringBuilder();
        for (Expense e : financeApp.getExpenseRecorder().getExpenses()) {
            sb.append("Values: ").append(e.getExpenses()).append("\n");
            sb.append("Purpose: ").append(e.getPurpose()).append("\n");
            sb.append("Category: ").append(e.getCategory()).append("\n");
            sb.append("-------------------------\n");
        }
        if (sb.length() == 0) {
            sb.append("No expenses yet.");
        }
        textArea.setText(sb.toString());
    }

}

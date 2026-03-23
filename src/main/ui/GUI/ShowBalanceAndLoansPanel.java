package ui.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.FinanceApp;

public class ShowBalanceAndLoansPanel extends JPanel {
    private FinanceApp financeApp;
    private JTextArea textArea;

    public ShowBalanceAndLoansPanel(FinanceApp financeApp) {
        this.financeApp = financeApp;
        setLayout(null);
        JLabel titleLabel = new JLabel("Show balance and loan balance");
        titleLabel.setBounds(0, 150, 800, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);
        textArea = new JTextArea();
        textArea.setBounds(0, 200, 400, 400);
        textArea.setEditable(false);
        add(textArea);
        StringBuilder sb = new StringBuilder();
        sb.append("Balance: ").append(financeApp.getFinanceTracker().getValues()).append("\n");
        sb.append("Loans: ").append(financeApp.getFinanceTracker().getLoans()).append("\n");
        textArea.setText(sb.toString());
    }

}

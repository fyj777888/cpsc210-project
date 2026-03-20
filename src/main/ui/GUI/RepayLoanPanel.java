package ui.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.FinanceApp;

public class RepayLoanPanel extends JPanel implements ActionListener{
    private FinanceApp financeApp;

    private JLabel titleLabel;
    private JLabel amountLabel;
    private JLabel messageLabel;

    private JTextField amountField;

    private JButton repayButton;

    public RepayLoanPanel(FinanceApp financeApp) {
        this.financeApp = financeApp;
        setLayout(null);

        initializeLabels();
        initializeFields();
        initializeButtons();

        add(titleLabel);
        add(amountLabel);
        add(messageLabel);
        add(amountField);
        add(repayButton);
    }

    private void initializeLabels() {
        titleLabel = new JLabel("Repay a Loan");
        titleLabel.setBounds(50, 200, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        amountLabel = new JLabel("How much you want to repay:");
        amountLabel.setBounds(50, 300, 250, 20);

        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 400, 500, 30);

        if (financeApp.getFinanceTracker().getLoans() == 0) {
            messageLabel.setText("You have no loans to repay.");
        }
    }

    private void initializeFields() {
        amountField = new JTextField();
        amountField.setBounds(50, 250, 250, 35);
    }

    private void initializeButtons() {
        repayButton = new JButton("Repay");
        repayButton.setBounds(50, 350, 120, 35);
        repayButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == repayButton) {
            repayLoan();
        }
    }

    private void repayLoan() {
        double repays;
        double loans = financeApp.getFinanceTracker().getLoans();

        if (loans == 0) {
            messageLabel.setText("You have no loans to repay.");
            return;
        }

        try {
            repays = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Please input a number");
            return;
        }

        if (repays <= 0 || repays > loans) {
            messageLabel.setText("Please input a number between 1 and current loans");
            return;
        }

        financeApp.getFinanceTracker().repayMoney(repays);
        messageLabel.setText("Loan repaid successfully!");
        amountField.setText("");
    }

}

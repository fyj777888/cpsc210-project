package ui.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.FinanceApp;

public class BorrowLoanPanel extends JPanel implements ActionListener {
    private FinanceApp financeApp;

    private JLabel titleLabel;
    private JLabel amountLabel;
    private JLabel messageLabel;

    private JTextField amountField;

    private JButton borrowButton;

    public BorrowLoanPanel(FinanceApp financeApp) {
        this.financeApp = financeApp;
        setLayout(null);
        initializeLabels();
        initializeFields();
        initializeButtons();
        add(titleLabel);
        add(amountLabel);
        add(messageLabel);
        add(amountField);
        add(borrowButton);
    }

    private void initializeLabels() {
        titleLabel = new JLabel("Borrow a Loan");
        titleLabel.setBounds(50, 200, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        amountLabel = new JLabel("How much you want to borrow:");
        amountLabel.setBounds(50, 300, 250, 20);
        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 400, 400, 30);
    }

    private void initializeFields() {
        amountField = new JTextField();
        amountField.setBounds(50, 250, 250, 35);
    }

    private void initializeButtons() {
        borrowButton = new JButton("Borrow");
        borrowButton.setBounds(50, 330, 120, 35);
        borrowButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == borrowButton) {
            borrowLoan();
        }
    }

    private void borrowLoan() {
        double loans;

        try {
            loans = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Please input a number");
            return;
        }

        if (loans <= 0 || loans > 1000000) {
            messageLabel.setText("Please input a number between 1 and 1000000");
            return;
        }

        financeApp.getFinanceTracker().borrowMoney(loans);
        messageLabel.setText("Loan borrowed successfully!");
        amountField.setText("");
    }

}

package ui.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Expense;
import model.FinanceApp;

public class AddExpensePanel extends JPanel implements ActionListener {

    private FinanceApp financeApp;

    private JLabel titleLabel;
    private JLabel amountLabel;
    private JLabel purposeLabel;
    private JLabel categoryLabel;
    private JLabel messageLabel;

    private JTextField amountField;
    private JTextField purposeField;

    private JComboBox<String> categoryBox;

    private JButton saveButton;

    public AddExpensePanel(FinanceApp financeApp) {
        this.financeApp = financeApp;
        setLayout(null);
        setBounds(0, 150, 1600, 1050);
        initializeFields();
        initializeLabels();
        initializeButtons();
        add(titleLabel);
        add(amountLabel);
        add(purposeLabel);
        add(categoryLabel);
        add(messageLabel);
        add(amountField);
        add(purposeField);
        add(categoryBox);
        add(saveButton);
        setVisible(true);
    }

    private void initializeFields() {
        amountField = new JTextField();
        amountField.setBounds(50, 250, 250, 35);
        purposeField = new JTextField();
        purposeField.setBounds(50, 350, 250, 35);
        categoryBox = new JComboBox<>();
        categoryBox.setBounds(50, 450, 250, 35);
        categoryBox.addItem("Food");
        categoryBox.addItem("Rental");
        categoryBox.addItem("Entertainment");
        categoryBox.addItem("Others");
    }

    private void initializeLabels() {
        titleLabel = new JLabel("Add an Expense");
        titleLabel.setBounds(50, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        amountLabel = new JLabel("How much you spent:");
        amountLabel.setBounds(50, 230, 200, 20);
        purposeLabel = new JLabel("What's your purpose:");
        purposeLabel.setBounds(50, 330, 200, 20);
        categoryLabel = new JLabel("Choose a category:");
        categoryLabel.setBounds(50, 430, 200, 20);
        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 520, 400, 30);
    }

    private void initializeButtons() {
        saveButton = new JButton("Save");
        saveButton.setBounds(50, 550, 120, 35);
        saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveExpense();
        }
    }

    private void saveExpense() {
        double values;
        String purpose;
        String category;
        try {
            values = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Please input correct values");
            return;
        }
        purpose = purposeField.getText();
        category = (String) categoryBox.getSelectedItem();
        financeApp.addExpense(new Expense(values, purpose, category));
        messageLabel.setText("Expense saved!");
        amountField.setText("");
        purposeField.setText("");
        categoryBox.setSelectedIndex(0);
    }
}
package ui.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Expense;
import model.FinanceApp;

public class ListSpentPanel extends JPanel implements ActionListener {
    private JComboBox<String> categoryBox;
    private JComboBox<String> subCategoryBox;
    private JButton saveButton;
    private JTextArea textArea;
    private FinanceApp financeApp;

    public ListSpentPanel(FinanceApp financeApp) {
        this.financeApp = financeApp;
        setLayout(null);
        textArea = new JTextArea();
        textArea.setBounds(50, 250, 250, 35);
        textArea.setEditable(false);
        categoryBox = new JComboBox<>();
        categoryBox.setBounds(50, 450, 250, 35);
        categoryBox.addItem("All");
        categoryBox.addItem("Category");
        categoryBox.addActionListener(this);
        add(textArea);
        add(categoryBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double expense = 0;
        if (e.getSource() == categoryBox) {
            String selected = (String) categoryBox.getSelectedItem();

            if (subCategoryBox != null) {
                remove(subCategoryBox);
                subCategoryBox = null;
            }

            if (selected.equals("All")) {
                for (Expense f : financeApp.getExpenseRecorder().getExpenses()) {
                    expense = expense + f.getExpenses();
                }
                textArea.setText("Show all spent:" + " " + expense);
            } else if (selected.equals("Category")) {
                subCategoryBox = new JComboBox<>();
                subCategoryBox.setBounds(320, 450, 250, 35);
                subCategoryBox.addItem("Food");
                subCategoryBox.addItem("Rental");
                subCategoryBox.addItem("Entertainment");
                subCategoryBox.addItem("Others");
                subCategoryBox.addActionListener(this);
                add(subCategoryBox);
            }
        } else if (e.getSource() == subCategoryBox) {
            String subSelected = (String) subCategoryBox.getSelectedItem();
            for (Expense f : financeApp.getExpenseRecorder().getExpenses()) {
                if (f.getCategory().equals(subSelected)){
                expense = expense + f.getExpenses();
                }
            }
            textArea.setText("Show spent for" + " " + subSelected + ": " + expense);
        }
        revalidate();
        repaint();
    }
}

package ui.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.FinanceApp;
import ui.DataRecorder;

public class MainMenuPanel extends JPanel implements ActionListener {
    private JButton addExpenseButton;
    private JButton listAllExpensesButton;
    private JButton showBalanceAndLoansButton;
    private JButton borrowLoanButton;
    private JButton repayLoanButton;
    private JButton listSpentButton;
    private JButton saveDataButton;
    private JButton loadDataButton;
    private FinanceApp financeApp;
    private MyFrame frame;
    private DataRecorder dataRecorder;

    public MainMenuPanel(MyFrame frame) {
        super();
        this.frame = frame;
        this.setPreferredSize(new Dimension(1600, 150));
        this.setLayout(null);
        constructButtons();
        financeApp = new FinanceApp();
        dataRecorder = new DataRecorder();
        this.add(addExpenseButton);
        this.add(listAllExpensesButton);
        this.add(showBalanceAndLoansButton);
        this.add(borrowLoanButton);
        this.add(repayLoanButton);
        this.add(listSpentButton);
        this.add(saveDataButton);
        this.add(loadDataButton);

    }

    private void constructButtons() {
        addExpenseButton = new JButton("Add an expense");
        listAllExpensesButton = new JButton("List all expenses");
        showBalanceAndLoansButton = new JButton("Show balance and loan balance");
        borrowLoanButton = new JButton("Borrow a loan");
        repayLoanButton = new JButton("Repay a loan");
        listSpentButton = new JButton("Show total spent");
        saveDataButton = new JButton("Save financeApp");
        loadDataButton = new JButton("Load financeApp");
        addExpenseButton.setBounds(0, 0, 200, 150);
        listAllExpensesButton.setBounds(200, 0, 200, 150);
        showBalanceAndLoansButton.setBounds(400, 0, 200, 150);
        borrowLoanButton.setBounds(600, 0, 200, 150);
        repayLoanButton.setBounds(800, 0, 200, 150);
        listSpentButton.setBounds(1000, 0, 200, 150);
        saveDataButton.setBounds(1200, 0, 200, 150);
        loadDataButton.setBounds(1400, 0, 200, 150);
        addActionListener();
    }

    private void addActionListener() {
        addExpenseButton.addActionListener(this);
        listAllExpensesButton.addActionListener(this);
        showBalanceAndLoansButton.addActionListener(this);
        borrowLoanButton.addActionListener(this);
        repayLoanButton.addActionListener(this);
        listSpentButton.addActionListener(this);
        saveDataButton.addActionListener(this);
        loadDataButton.addActionListener(this);
    }

    public FinanceApp getFinanceApp() {
        return financeApp;
    }

    public DataRecorder getDataRecorder() {
        return dataRecorder;
    }

    public void setFinanceApp(FinanceApp financeApp) {
        this.financeApp = financeApp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addExpenseButton) {
            AddExpensePanel addExpensePanel = new AddExpensePanel(financeApp);
            frame.showPanel(addExpensePanel);
        } else if (e.getSource() == listAllExpensesButton) {
            ListAllExpensesPanel listAllExpensesPanel = new ListAllExpensesPanel(financeApp);
            frame.showPanel(listAllExpensesPanel);
        } else if (e.getSource() == showBalanceAndLoansButton) {
            ShowBalanceAndLoansPanel showBalanceAndLoansButton = new ShowBalanceAndLoansPanel(financeApp);
            frame.showPanel(showBalanceAndLoansButton);
        } else if (e.getSource() == borrowLoanButton) {
            BorrowLoanPanel borrowLoanPanel = new BorrowLoanPanel(financeApp);
            frame.showPanel(borrowLoanPanel);
        } else if (e.getSource() == repayLoanButton) {
            RepayLoanPanel repayLoanPanel = new RepayLoanPanel(financeApp);
            frame.showPanel(repayLoanPanel);
        } else if (e.getSource() == listSpentButton) {
            ListSpentPanel listSpentPanel = new ListSpentPanel(financeApp);
            frame.showPanel(listSpentPanel);
        } else if (e.getSource() == saveDataButton) {
            SaveDataPanel saveDataPanel = new SaveDataPanel(this);
            frame.showPanel(saveDataPanel);
        } else if (e.getSource() == loadDataButton) {
            LoadDataPanel loadDataPanel = new LoadDataPanel(this);
            frame.showPanel(loadDataPanel);
        }
    }
}

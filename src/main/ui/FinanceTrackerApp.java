package ui;

import java.util.Scanner;

import model.Expense;
import model.ExpenseRecorder;
import model.FinanceApp;
import model.FinanceTracker;

public class FinanceTrackerApp {
    Scanner sc;
    int option;
    String category;
    ExpenseRecorder expenseRecorder = new ExpenseRecorder();
    FinanceTracker financeTracker = new FinanceTracker();
    private DataRecorder dataRecorder;

    public FinanceTrackerApp() {
        sc = new Scanner(System.in);
        dataRecorder = new DataRecorder();
        boolean keepGoing = true;
        while (keepGoing) {
            printOptions();
            if (option == 0) {
                keepGoing = false;
            } else {
                makeOptions(option);
            }
        }
        System.out.println("Goodbye!");
    }

    private void printOptions() {
        while (true) {
            System.out.println();
            System.out.println("1) Add an expense");
            System.out.println("2) List all expenses");
            System.out.println("3) Show balance and loan balance");
            System.out.println("4) Borrow a loan");
            System.out.println("5) Repay a loan");
            System.out.println("6) Show total spent (all / by category)");
            System.out.println("7) Save financeApp");
            System.out.println("8) Load financeApp");
            System.out.println("0) Quit");
            if (sc.hasNextInt()) {
                option = sc.nextInt();
                if (option >= 0 && option <= 8) {
                    break;
                } else {
                    System.out.println("Option must be 0-8.");
                }
            } else {
                System.out.println("Please input correct choice");
                sc.nextLine();
            }
        }

    }

    private void makeOptions(int option) {
        switch (option) {
            case 1:
                addExpense();
                return;
            case 2:
                listAllExpenses();
                return;
            case 3:
                showBalanceAndLoans();
                return;
            case 4:
                borrowLoan();
                return;
            case 5:
                repayLoan();
                return;
            case 6:
                listSpent();
                return;
            case 7:
                saveData();
                return;
            case 8:
                loadData();
                return;
            default:
                return;
        }
    }

    public void addExpense() {
        double values;
        String purpose;
        while (true) {
            System.out.println("How much you spent");
            if (sc.hasNextDouble()) {
                values = sc.nextDouble();
                financeTracker.decreaseValues(values);
                break;
            } else {
                System.out.println("Please input correct values");
                sc.nextLine();
            }
        }
        System.out.println("What's your purpose");
        purpose = sc.next();
        System.out.println("Choose one of the category:1) food 2) rental 3) entertainment 4) others");
        addCategory();
        expenseRecorder.addExpense(new Expense(values, purpose, category));
    }

    public void listAllExpenses() {
        for (Expense e : expenseRecorder.getExpenses()) {
            System.out.println("Values:" + e.getExpenses());
            System.out.println("Purpose:" + e.getPurpose());
            System.out.println("Category:" + e.getCategory());
        }
    }

    public void showBalanceAndLoans() {
        System.out.println("Balance:" + financeTracker.getValues());
        System.out.println("Loans:" + financeTracker.getLoans());
    }

    public void addCategory() {
        while (true) {
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        category = "food";
                        return;
                    case 2:
                        category = "rental";
                        return;
                    case 3:
                        category = "entertainment";
                        return;
                    case 4:
                        category = "others";
                        return;
                    default:
                        System.out.println("Please input correct choice");
                        sc.nextLine();
                        break;
                }
            } else {
                System.out.println("Please input correct choice");
                sc.next();
            }
        }
    }

    public void listSpent() {
        System.out.println("all / category");
        double expense = 0;
        while (true) {
            String type = sc.next();
            if (type.equals("all")) {
                for (Expense e : expenseRecorder.getExpenses()) {
                    expense = expense + e.getExpenses();
                }
                System.out.println(expense);
                break;
            } else if (type.equals("category")) {
                spentCategory();
                for (Expense e : expenseRecorder.getExpenses()) {
                    if (e.getCategory().equals(category)) {
                        expense = expense + e.getExpenses();
                    }
                }
                System.out.println(expense);
                break;
            } else {
                System.out.println("Please input \"all\" or \"category\"");
            }

        }

    }

    public void spentCategory() {
        System.out.println("Choose one of the category:1) food 2) rental 3) entertainment 4) others");
        while (true) {
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        category = "food";
                        return;
                    case 2:
                        category = "rental";
                        return;
                    case 3:
                        category = "entertainment";
                        return;
                    case 4:
                        category = "others";
                        return;
                    default:
                        System.out.println("Please input correct choice");
                        sc.nextLine();
                        return;
                }
            }
        }
    }

    public void repayLoan() {
        if (financeTracker.getLoans() == 0) {
            System.out.println("You have no loans to repay.");
            return;
        }
        System.out.println("How much you want to repay:");
        while (true) {
            if (sc.hasNextDouble()) {
                double repays = sc.nextDouble();
                if (0 < repays && repays <= financeTracker.getLoans()) {
                    financeTracker.repayMoney(repays);
                    break;
                } else {
                    System.out.println("Please input an number between 1 and loans");
                    sc.nextLine();
                }
            } else {
                System.out.println("Please input a number");
                sc.next();
            }

        }
    }

    public void borrowLoan() {
        System.out.println("How much you want to borrow:");
        while (true) {
            if (sc.hasNextDouble()) {
                double loans = sc.nextDouble();
                if (0 < loans && loans <= 1000000) {
                    financeTracker.borrowMoney(loans);
                    break;
                } else {
                    System.out.println("Please input an integer between 1 and 1000000");
                    sc.nextLine();
                }
            } else {
                System.out.println("Please input a number");
                sc.next();
            }
        }
    }

    public void saveData() {
        dataRecorder.saveFinanceApp(expenseRecorder, financeTracker);
    }

    public void loadData() {
        FinanceApp loaded = dataRecorder.loadFinanceApp();
        if (loaded != null) {
            expenseRecorder = loaded.getExpenseRecorder();
            financeTracker = loaded.getFinanceTracker();
        } else {
            System.out.println("Load failed: loaded == null");
        }
    }

}
